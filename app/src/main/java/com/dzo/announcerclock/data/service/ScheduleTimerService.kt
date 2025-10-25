package com.dzo.announcerclock.data.service

import android.app.*
import android.content.Intent
import android.content.pm.ServiceInfo
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.speech.tts.TextToSpeech
import android.telephony.TelephonyManager
import androidx.core.app.ActivityCompat.recreate
import androidx.core.app.NotificationCompat
import androidx.core.graphics.toColorInt
import androidx.core.net.toUri
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.dzo.announcerclock.R
import com.dzo.announcerclock.App
import com.dzo.announcerclock.data.local_source.AppPreferences
import com.dzo.announcerclock.domain.timer_usecase.AnnounceTimeUseCase
import com.dzo.announcerclock.presentation.activity.MainActivity
import com.dzo.announcerclock.utils.AudioPlaybackListener
import com.dzo.announcerclock.utils.Constants
import com.dzo.announcerclock.utils.Constants.ACTION_TOGGLE_UPDATE
import com.dzo.announcerclock.utils.Constants.EXTRA_IS_ENABLED
import com.dzo.announcerclock.utils.PhoneCallListener
import com.dzo.announcerclock.utils.PreferenceHelper
import com.dzo.announcerclock.utils.Utils.lighten
import com.dzo.announcerclock.utils.Utils.toast
import com.dzo.announcerclock.utils.extension.showColoredToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class ScheduleTimerService : Service(), TextToSpeech.OnInitListener {
    @Inject
    lateinit var announceTimeUseCase: AnnounceTimeUseCase

    private var tts: TextToSpeech? = null
    private var timerJob: Job? = null
    private var isRunning = false
    private var ttsReady = false
    private var phoneListener: PhoneCallListener? = null
    private var audioListener: AudioPlaybackListener? = null
    private var startTime = 0L
    private val _progressFlow = MutableStateFlow(0)
    val progressFlow = _progressFlow.asStateFlow()

    private val _timeFlow = MutableStateFlow("00:00")
    val timeFlow = _timeFlow.asStateFlow()

    inner class TimerBinder : Binder() {
        fun getService(): ScheduleTimerService = this@ScheduleTimerService
    }

    override fun onCreate() {
        super.onCreate()
        tts = TextToSpeech(this, this)
        createForegroundNotificationChannel()

        phoneListener = PhoneCallListener.register(this) { state ->
            when (state) {
                TelephonyManager.CALL_STATE_RINGING,
                TelephonyManager.CALL_STATE_OFFHOOK -> {
                    if (AppPreferences.isDisableDuringPhoneCalls() == true) {
                        pauseServiceForCall()
                    }
                }

                TelephonyManager.CALL_STATE_IDLE -> {
                    if (AppPreferences.isDisableDuringPhoneCalls() == true) {
                        resumeServiceAfterCall()
                    }
                }
            }
        }

        audioListener?.register()
    }

    private fun pauseServiceForCall() {
        if (isRunning) {
            isRunning = false
            tts?.stop()
            toast(App.appContext(), "paused")
        }
    }

    private fun resumeServiceAfterCall() {
        if (!isRunning) {
            isRunning = true
            toast(App.appContext(), "resumed")
        }
    }

    override fun onBind(intent: Intent?): IBinder = TimerBinder()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForegroundServiceNotification()

        timerJob?.cancel()
        isRunning = false

        val startTimeMillis = intent!!.getLongExtra("startTimeMillis", 300_000L)
        val endTimeMillis = intent.getLongExtra("endTimeMillis", 600_000L)
        val intervalMillis = intent.getLongExtra("intervalMillis", 300_000L)

        startScheduleTimer(startTimeMillis, endTimeMillis, intervalMillis)

        return START_NOT_STICKY
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        stopSelf()
    }

    // ----------------- Foreground Notification -----------------
    private fun createForegroundNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "timer_foreground_channel",
                "Timer Foreground",
                NotificationManager.IMPORTANCE_LOW
            )
            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }
    }

    private fun startForegroundServiceNotification() {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val bigPictureBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_logo)

        val builder = NotificationCompat.Builder(this, "timer_foreground_channel")
            .setContentTitle("Announcer Clock Active")
            .setContentText("Tap to open and see timer progress")
            .setSmallIcon(R.drawable.ic_logo)
            .setLargeIcon(bigPictureBitmap)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)

        val notification = builder.build()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startForeground(1, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK)
        } else {
            startForeground(1, notification)
        }
    }

    private fun startScheduleTimer(
        startTimeMillis: Long,
        endTimeMillis: Long,
        intervalMillis: Long
    ) {
        if (isRunning) return
        isRunning = true

        var nextAnnounceTime = startTimeMillis

        timerJob?.cancel()
        timerJob = CoroutineScope(Dispatchers.Default).launch {
            while (isActive) {
                if (!isRunning) {
                    delay(200)
                    continue
                }

                val now = System.currentTimeMillis()

                // End condition
                if (now >= endTimeMillis) {
                    isRunning = false

                    withContext(Dispatchers.Main) {
                        toast(App.appContext(),"Schedule finished")
                        showCompletionNotification()
                    }

                    AppPreferences.saveCustomToggleState(false)
                    PreferenceHelper.remove(Constants.KEY_SCHEDULE_TIME)

                    val intent = Intent("SCHEDULE_FINISHED")
                    sendBroadcast(intent)

                    stopSelf()
                    break
                }

                // Progress
                val progress =
                    ((now - startTimeMillis).toFloat() / (endTimeMillis - startTimeMillis) * 100)
                        .coerceIn(0f, 100f)
                _progressFlow.value = progress.toInt()

                val remaining = (endTimeMillis - now).coerceAtLeast(0)
                val minutes = (remaining / 1000 / 60).toInt()
                val seconds = ((remaining / 1000) % 60).toInt()
                _timeFlow.value = String.format("%02d:%02d", minutes, seconds)

                // Announce if interval reached
                if (now >= nextAnnounceTime) {
                    nextAnnounceTime += intervalMillis
                    if (AppPreferences.isTimeSpeakingEnabled() == true) announceTime()
                    if (AppPreferences.isNotificationEnabled() == true) showCompletionNotification()
                }

                delay(1000)
            }
        }
    }
    private fun sendToggleBroadcast(isEnabled: Boolean) {
      /*  val intent = Intent(ACTION_TOGGLE_UPDATE).apply {
            putExtra(EXTRA_IS_ENABLED, isEnabled)
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)*/
        val intent = Intent(ACTION_TOGGLE_UPDATE).apply {
            putExtra(EXTRA_IS_ENABLED, isEnabled)
        }
        sendBroadcast(intent)
    }


    private suspend fun announceTime() {
        val settings = AppPreferences.getTtsSettings()
        tts?.setPitch(settings.pitch)
        tts?.setSpeechRate(settings.rate)

        val localeParts = settings.language.split("_")
        val locale = if (localeParts.size == 2) Locale(
            localeParts[0],
            localeParts[1]
        ) else Locale.getDefault()
        tts?.language = locale

        tts?.voices?.firstOrNull { it.name.contains(settings.genderVoice, true) }?.let {
            tts?.voice = it
        }

        if (!ttsReady) {
            repeat(5) {
                delay(500)
                if (ttsReady) return@repeat
            }
        }

        if (ttsReady) {

            val message = announceTimeUseCase()
            withContext(Dispatchers.Main) {
                toast(App.appContext(), "tts speaking")
                tts?.speak(message, TextToSpeech.QUEUE_FLUSH, null, null)
            }
            while (tts?.isSpeaking == true) {
                delay(200)
            }
        }
    }

    private suspend fun showCompletionNotification() = withContext(Dispatchers.Main) {
        val soundOption = AppPreferences.getSoundOption()
        val isSoundEnabled = AppPreferences.isNotificationSoundEnabled() == true
        val soundUri = if (isSoundEnabled && soundOption?.soundResId != null)
            "android.resource://${packageName}/${soundOption.soundResId}".toUri() else null

        val channelId =
            "timer_channel_${if (isSoundEnabled) "on" else "off"}_${soundOption?.soundResId ?: 0}"
        val nm = getSystemService(NotificationManager::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            nm.notificationChannels?.forEach {
                if (it.id.startsWith("timer_channel_") && it.id != channelId) nm.deleteNotificationChannel(
                    it.id
                )
            }

            val channel = NotificationChannel(
                channelId,
                "Timer Completed",
                NotificationManager.IMPORTANCE_HIGH
            )
            if (isSoundEnabled && soundUri != null) {
                val attrs = AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()
                channel.setSound(soundUri, attrs)
            } else {
                channel.setSound(null, null)
            }
            nm.createNotificationChannel(channel)
        }

        val pendingIntent = PendingIntent.getActivity(
            this@ScheduleTimerService, 0,
            Intent(this@ScheduleTimerService, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(this@ScheduleTimerService, channelId)
            .setContentTitle("Timer Completed")
            .setContentText("Your timer has finished.")
            .setSmallIcon(R.drawable.ic_logo)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        if (isSoundEnabled && soundUri != null && Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
            builder.setSound(soundUri)

        nm.notify(2, builder.build())
    }

    override fun onDestroy() {
        isRunning = false
        timerJob?.cancel()
        tts?.stop()
        tts?.shutdown()
        stopSelf()
        audioListener?.unregister()
        phoneListener?.let { PhoneCallListener.unregister(this, it) }
        super.onDestroy()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.language = Locale.getDefault()
            ttsReady = true
        }
    }

    fun stopServiceManually() {
        isRunning = false
        timerJob?.cancel()
        tts?.stop()
        tts?.shutdown()
        stopForeground(true)
        stopSelf()
    }
}
