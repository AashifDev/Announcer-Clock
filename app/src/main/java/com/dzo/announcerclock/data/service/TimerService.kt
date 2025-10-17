/*
package com.dzo.talktime.data.service

import android.app.*
import android.content.Intent
import android.content.pm.ServiceInfo
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.speech.tts.TextToSpeech
import androidx.core.app.NotificationCompat
import com.dzo.talktime.R
import com.dzo.talktime.data.local_source.AppPreferences
import com.dzo.talktime.domain.timer_usecase.AnnounceTimeUseCase
import com.dzo.talktime.presentation.activity.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*
import javax.inject.Inject
import androidx.core.net.toUri

@AndroidEntryPoint
class TimerService : Service(), TextToSpeech.OnInitListener {

    @Inject
    lateinit var announceTimeUseCase: AnnounceTimeUseCase

    private var tts: TextToSpeech? = null
    private var timerJob: Job? = null
    private var isRunning = false
    private var ttsReady = false

    private val _progressFlow = MutableStateFlow(0)
    val progressFlow = _progressFlow.asStateFlow()

    private val _timeFlow = MutableStateFlow("00:00")
    val timeFlow = _timeFlow.asStateFlow()

    inner class TimerBinder : Binder() {
        fun getService(): TimerService = this@TimerService
    }

    override fun onCreate() {
        super.onCreate()
        tts = TextToSpeech(this, this)
        createForegroundNotificationChannel()
    }

    override fun onBind(intent: Intent?): IBinder = TimerBinder()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForegroundServiceNotification()

        timerJob?.cancel()
        isRunning = false

        val isCustom = intent?.getBooleanExtra("isCustom", false) ?: false

        if (isCustom) {
            val intervalMillis = intent.getLongExtra("intervalMillis", 60_000L)
            val totalRunMillis = intent.getLongExtra("totalRunMillis", 300_000L)
            startCustomTimer(intervalMillis, totalRunMillis)
        } else {
            val totalMillis = intent?.getLongExtra("totalMillis", 60_000L) ?: 60_000L
            startTimer(totalMillis)
        }

        return START_NOT_STICKY
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        stopSelf() // Service ko stop kar do
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
            .setContentTitle("Talking Clock Active")
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

    // ----------------- Timer Logic -----------------
    */
/*private fun startTimer(totalMillis: Long) {
        if (isRunning) return
        isRunning = true

        timerJob?.cancel()
        timerJob = CoroutineScope(Dispatchers.Default).launch {
            var startTime =
                AppPreferences.getStartTime().takeIf { it > 0 } ?: System.currentTimeMillis()
            AppPreferences.saveStartTime(startTime)

            while (isActive && isRunning) {
                val now = System.currentTimeMillis()
                val elapsed = now - startTime
                val remaining = totalMillis - elapsed.coerceAtLeast(0)

                val progress = (elapsed.toFloat() / totalMillis * 100).coerceIn(0f, 100f)
                val minutes = (remaining / 1000 / 60).toInt()
                val seconds = ((remaining / 1000) % 60).toInt()
                val formattedTime = String.format("%02d:%02d", minutes, seconds)

                // Update Flows for fragment
                _progressFlow.value = progress.toInt()
                _timeFlow.value = formattedTime

                if (elapsed >= totalMillis) {
                    // 1️⃣ Announce via TTS
                    announceTime()
                    // 2️⃣ Show notification with session sound
                    showCompletionNotification()

                    startTime = System.currentTimeMillis()
                    AppPreferences.saveStartTime(startTime)
                }

                delay(1000)
            }
        }
    }*//*


    private fun startTimer(totalMillis: Long) {
        if (isRunning) return
        isRunning = true

        timerJob?.cancel()
        timerJob = CoroutineScope(Dispatchers.Default).launch {
            var startTime =
                AppPreferences.getStartTime().takeIf { it > 0 } ?: System.currentTimeMillis()
            AppPreferences.saveStartTime(startTime)

            while (isActive && isRunning) {
                val now = System.currentTimeMillis()
                val elapsed = now - startTime
                val remaining = totalMillis - elapsed.coerceAtLeast(0)

                val progress = (elapsed.toFloat() / totalMillis * 100).coerceIn(0f, 100f)
                val minutes = (remaining / 1000 / 60).toInt()
                val seconds = ((remaining / 1000) % 60).toInt()
                val formattedTime = String.format("%02d:%02d", minutes, seconds)

                // Update Flows for fragment
                _progressFlow.value = progress.toInt()
                _timeFlow.value = formattedTime

                if (elapsed >= totalMillis) {
                    if (AppPreferences.isTimeSpeakingEnabled() == true) {
                        announceTime()
                    }
                    if (AppPreferences.isNotificationEnabled() == true) {
                        showCompletionNotification()
                    }

                    startTime = System.currentTimeMillis()
                    AppPreferences.saveStartTime(startTime)
                }

                delay(1000)
            }
        }
    }
    private fun startCustomTimer(totalRunMillis: Long, howLongMillis: Long) {
        if (isRunning) return
        isRunning = true

        timerJob?.cancel()
        timerJob = CoroutineScope(Dispatchers.Default).launch {
            val startTime =
                AppPreferences.getStartTime().takeIf { it > 0 } ?: System.currentTimeMillis()
            AppPreferences.saveStartTime(startTime)

            val endTime = startTime + howLongMillis // Total run duration
            var lastAnnounceTime = startTime

            while (isActive && isRunning) {
                val now = System.currentTimeMillis()
                val elapsedSinceStart = now - startTime
                val elapsedSinceLastAnnounce = now - lastAnnounceTime

                val remainingTotal = (endTime - now).coerceAtLeast(0)
                val minutes = (remainingTotal / 1000 / 60).toInt()
                val seconds = ((remainingTotal / 1000) % 60).toInt()
                val formattedTime = String.format("%02d:%02d", minutes, seconds)

                val progress =
                    (elapsedSinceStart.toFloat() / howLongMillis * 100).coerceIn(0f, 100f)

                // Update Flows for fragment
                _progressFlow.value = progress.toInt()
                _timeFlow.value = formattedTime

                // 1️⃣ Announce at every totalMillis interval
                if (elapsedSinceLastAnnounce >= totalRunMillis) {
                    lastAnnounceTime = now
                    if (AppPreferences.isTimeSpeakingEnabled() == true) {
                        announceTime()
                    }
                    if (AppPreferences.isNotificationEnabled() == true) {
                        showCompletionNotification()
                    }
                }

                // 2️⃣ Stop service if total duration reached
                if (now >= endTime) {
                    stopSelf()
                    break
                }

                delay(1000)
            }
        }
    }

    // ----------------- TTS -----------------
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
                tts?.speak(message, TextToSpeech.QUEUE_FLUSH, null, null)
            }
            // Wait TTS to finish
            while (tts?.isSpeaking == true) {
                delay(200)
            }
        }
    }

    // ----------------- Completion Notification -----------------
    */
/*private suspend fun showCompletionNotification() = withContext(Dispatchers.Main) {
        val soundOption = AppPreferences.getSoundOption()
        val isSoundEnabled = AppPreferences.isNotificationSoundEnabled() == true
        val soundUri = soundOption?.soundResId?.let {
            "android.resource://${packageName}/$it".toUri()
        }

        val channelId = "timer_channel_${soundOption?.soundResId ?: 0}"


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Timer Completed",
                NotificationManager.IMPORTANCE_HIGH
            )
            if (soundUri != null) {
                val audioAttributes = AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()
                channel.setSound(soundUri, audioAttributes)
            }
            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }

        val notificationIntent = Intent(this@TimerService, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this@TimerService, 0, notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(this@TimerService, channelId)
            .setContentTitle("Timer Completed")
            .setContentText("Your timer has finished.")
            .setSmallIcon(R.drawable.ic_logo)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        if (soundUri != null && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            if (isSoundEnabled){
                builder.setSound(soundUri)
            }
        }

        val nm = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        nm.notify(2, builder.build())
    }*//*

    private suspend fun showCompletionNotification() = withContext(Dispatchers.Main) {
        val soundOption = AppPreferences.getSoundOption()
        val isSoundEnabled = AppPreferences.isNotificationSoundEnabled() == true

        val soundUri = if (isSoundEnabled && soundOption?.soundResId != null) {
            "android.resource://${packageName}/${soundOption.soundResId}".toUri()
        } else null

        // Channel ID includes soundEnabled state to force channel recreation when setting changes
        val channelId = "timer_channel_${if (isSoundEnabled) "on" else "off"}_${soundOption?.soundResId ?: 0}"

        val nm = getSystemService(NotificationManager::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Delete old channels to ensure sound state is applied freshly
            nm.notificationChannels?.forEach { channel ->
                if (channel.id.startsWith("timer_channel_") && channel.id != channelId) {
                    nm.deleteNotificationChannel(channel.id)
                }
            }

            val channel = NotificationChannel(
                channelId,
                "Timer Completed",
                NotificationManager.IMPORTANCE_HIGH
            )

            if (isSoundEnabled && soundUri != null) {
                val audioAttributes = AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()
                channel.setSound(soundUri, audioAttributes)
            } else {
                // Disable sound if user has turned it off
                channel.setSound(null, null)
            }

            nm.createNotificationChannel(channel)
        }

        val notificationIntent = Intent(this@TimerService, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this@TimerService, 0, notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(this@TimerService, channelId)
            .setContentTitle("Timer Completed")
            .setContentText("Your timer has finished.")
            .setSmallIcon(R.drawable.ic_logo)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        // For Android versions below O, manually set sound
        if (isSoundEnabled && soundUri != null && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            builder.setSound(soundUri)
        }

        nm.notify(2, builder.build())
    }


    // ----------------- Lifecycle -----------------
    override fun onDestroy() {
        isRunning = false
        timerJob?.cancel()
        tts?.stop()
        tts?.shutdown()
        stopSelf()
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
*/


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
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import com.dzo.announcerclock.R
import com.dzo.announcerclock.App
import com.dzo.announcerclock.data.local_source.AppPreferences
import com.dzo.announcerclock.domain.timer_usecase.AnnounceTimeUseCase
import com.dzo.announcerclock.presentation.activity.MainActivity
import com.dzo.announcerclock.utils.AudioPlaybackListener
import com.dzo.announcerclock.utils.PhoneCallListener
import com.dzo.announcerclock.utils.Utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class TimerService : Service(), TextToSpeech.OnInitListener {

    @Inject
    lateinit var announceTimeUseCase: AnnounceTimeUseCase

    private var tts: TextToSpeech? = null
    private var timerJob: Job? = null
    private var isRunning = false
    private var ttsReady = false
    private var phoneListener: PhoneCallListener? = null
    private var audioListener: AudioPlaybackListener? = null
    private var wasPausedByAudio = false
    private var elapsedTime = 0L
    private var totalTime = 0L


    private var elapsedTimeCustom = 0L
    private var totalDurationCustom = 0L
    private var lastAnnounceTime = 0L
    private var startTime = 0L
    private val _progressFlow = MutableStateFlow(0)
    val progressFlow = _progressFlow.asStateFlow()

    private val _timeFlow = MutableStateFlow("00:00")
    val timeFlow = _timeFlow.asStateFlow()

    inner class TimerBinder : Binder() {
        fun getService(): TimerService = this@TimerService
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

        /*audioListener = AudioPlaybackListener(this) { isPlaying ->
            if (isPlaying) {
                if (tts?.isSpeaking == true) {
                    pauseTts()
                    wasPausedByAudio = true
                }
            } else if (wasPausedByAudio) {
                wasPausedByAudio = false
                resumeTts()
            }
        }*/

        audioListener?.register()
    }

    private fun pauseServiceForCall() {
        if (isRunning) {
            isRunning = false
            tts?.stop()
            toast(App.appContext(),"paused")
        }
    }

    private fun resumeServiceAfterCall() {
        if (!isRunning) {
            isRunning = true
            toast(App.appContext(),"resumed")
        }
    }


    private fun pauseTts() {
        if (tts?.isSpeaking == true) tts?.stop()
    }

    private fun resumeTts() {
        // Optional: Speak current time again or simply wait for next interval
    }


    override fun onBind(intent: Intent?): IBinder = TimerBinder()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForegroundServiceNotification()

        timerJob?.cancel()
        isRunning = false

        val isCustom = intent?.getBooleanExtra("isCustom", false) ?: false

        if (isCustom) {
            val intervalMillis = intent.getLongExtra("intervalMillis", 60_000L)
            val totalRunMillis = intent.getLongExtra("totalRunMillis", 300_000L)
            startCustomTimer(intervalMillis, totalRunMillis)
        } else {
            val totalMillis = intent?.getLongExtra("totalMillis", 60_000L) ?: 60_000L
            startTimer(totalMillis)
        }

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
            .setContentTitle("Talking Clock Active")
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

    // ----------------- Timer Logic -----------------
    /*private fun startTimer(totalMillis: Long) {
        if (isRunning) return
        isRunning = true

        timerJob?.cancel()
        timerJob = CoroutineScope(Dispatchers.Default).launch {
            var startTime =
                AppPreferences.getStartTime().takeIf { it > 0 } ?: System.currentTimeMillis()
            AppPreferences.saveStartTime(startTime)

            while (isActive && isRunning) {
                val now = System.currentTimeMillis()
                val elapsed = now - startTime
                val remaining = totalMillis - elapsed.coerceAtLeast(0)

                val progress = (elapsed.toFloat() / totalMillis * 100).coerceIn(0f, 100f)
                val minutes = (remaining / 1000 / 60).toInt()
                val seconds = ((remaining / 1000) % 60).toInt()
                val formattedTime = String.format("%02d:%02d", minutes, seconds)

                _progressFlow.value = progress.toInt()
                _timeFlow.value = formattedTime

                if (elapsed >= totalMillis) {
                    if (AppPreferences.isTimeSpeakingEnabled() == true) {
                        announceTime()
                    }
                    if (AppPreferences.isNotificationEnabled() == true) {
                        showCompletionNotification()
                    }

                    startTime = System.currentTimeMillis()
                    AppPreferences.saveStartTime(startTime)
                }

                delay(1000)
            }
        }
    }*/

    private fun startTimer(totalMillis: Long) {
        totalTime = totalMillis
        if (isRunning) return
        isRunning = true

        timerJob?.cancel()
        timerJob = CoroutineScope(Dispatchers.Default).launch {
            var startTime = System.currentTimeMillis() - elapsedTime

            while (isActive) {
                if (!isRunning) {
                    delay(200) // Pause loop, don’t exit
                    continue
                }

                val now = System.currentTimeMillis()
                elapsedTime = now - startTime
                val remaining = totalTime - elapsedTime.coerceAtLeast(0)
                val progress = (elapsedTime.toFloat() / totalTime * 100).coerceIn(0f, 100f)

                val minutes = (remaining / 1000 / 60).toInt()
                val seconds = ((remaining / 1000) % 60).toInt()
                _progressFlow.value = progress.toInt()
                _timeFlow.value = String.format("%02d:%02d", minutes, seconds)

                if (elapsedTime >= totalTime) {
                    if (AppPreferences.isTimeSpeakingEnabled() == true) announceTime()
                    if (AppPreferences.isNotificationEnabled() == true) showCompletionNotification()
                    startTime = System.currentTimeMillis()
                    elapsedTime = 0L
                }
                delay(1000)
            }
        }
    }


    /*private fun startCustomTimer(totalRunMillis: Long, howLongMillis: Long) {
        if (isRunning) return
        isRunning = true

        timerJob?.cancel()
        timerJob = CoroutineScope(Dispatchers.Default).launch {
            val startTime =
                AppPreferences.getStartTime().takeIf { it > 0 } ?: System.currentTimeMillis()
            AppPreferences.saveStartTime(startTime)

            val endTime = startTime + howLongMillis
            var lastAnnounceTime = startTime

            while (isActive && isRunning) {
                val now = System.currentTimeMillis()
                val elapsedSinceStart = now - startTime
                val elapsedSinceLastAnnounce = now - lastAnnounceTime

                val remainingTotal = (endTime - now).coerceAtLeast(0)
                val minutes = (remainingTotal / 1000 / 60).toInt()
                val seconds = ((remainingTotal / 1000) % 60).toInt()
                val formattedTime = String.format("%02d:%02d", minutes, seconds)

                val progress =
                    (elapsedSinceStart.toFloat() / howLongMillis * 100).coerceIn(0f, 100f)

                _progressFlow.value = progress.toInt()
                _timeFlow.value = formattedTime

                if (elapsedSinceLastAnnounce >= totalRunMillis) {
                    lastAnnounceTime = now
                    if (AppPreferences.isTimeSpeakingEnabled() == true) {
                        announceTime()
                    }
                    if (AppPreferences.isNotificationEnabled() == true) {
                        showCompletionNotification()
                    }
                }

                if (now >= endTime) {
                    stopSelf()
                    break
                }

                delay(1000)
            }
        }
    }*/

    private fun startCustomTimer(totalRunMillis: Long, howLongMillis: Long) {
        totalDurationCustom = howLongMillis
        if (isRunning) return
        isRunning = true

        timerJob?.cancel()
        timerJob = CoroutineScope(Dispatchers.Default).launch {
            startTime = System.currentTimeMillis() - elapsedTimeCustom
            lastAnnounceTime = startTime

            while (isActive) {
                if (!isRunning) {
                    delay(200) // just wait when paused
                    continue
                }

                val now = System.currentTimeMillis()
                elapsedTimeCustom = now - startTime

                val remaining = (totalDurationCustom - elapsedTimeCustom).coerceAtLeast(0)
                val minutes = (remaining / 1000 / 60).toInt()
                val seconds = ((remaining / 1000) % 60).toInt()
                val formattedTime = String.format("%02d:%02d", minutes, seconds)
                val progress =
                    (elapsedTimeCustom.toFloat() / totalDurationCustom * 100).coerceIn(0f, 100f)

                _progressFlow.value = progress.toInt()
                _timeFlow.value = formattedTime

                // Announce every totalRunMillis
                if (now - lastAnnounceTime >= totalRunMillis) {
                    lastAnnounceTime = now
                    if (AppPreferences.isTimeSpeakingEnabled() == true) announceTime()
                    if (AppPreferences.isNotificationEnabled() == true) showCompletionNotification()
                }

                // End reached
                if (elapsedTimeCustom >= totalDurationCustom) {
                    stopSelf()
                    break
                }

                delay(1000)
            }
        }
    }


    // ----------------- TTS -----------------
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
                tts?.speak(message, TextToSpeech.QUEUE_FLUSH, null, null)
            }
            while (tts?.isSpeaking == true) {
                delay(200)
            }
        }
    }

    // ----------------- Notification -----------------
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
            this@TimerService, 0,
            Intent(this@TimerService, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(this@TimerService, channelId)
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

    // ----------------- Lifecycle -----------------
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
