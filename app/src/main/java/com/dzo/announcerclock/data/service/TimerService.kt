/*

package com.dzo.announcerclock.data.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
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
import com.dzo.announcerclock.App
import com.dzo.announcerclock.R
import com.dzo.announcerclock.data.local_source.AppPreferences
import com.dzo.announcerclock.domain.timer_usecase.AnnounceTimeUseCase
import com.dzo.announcerclock.presentation.activity.MainActivity
import com.dzo.announcerclock.utils.Constants
import com.dzo.announcerclock.utils.Utils.toast
import com.dzo.announcerclock.utils.helper.AudioPlaybackListener
import com.dzo.announcerclock.utils.helper.PhoneCallListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class TimerService : Service(), TextToSpeech.OnInitListener {

    @Inject
    lateinit var announceTimeUseCase: AnnounceTimeUseCase

    companion object {
        // Use your central Constants if you have them; fallback strings kept consistent.
        const val ACTION_TOGGLE_SERVICE_LOCAL = "com.dzo.announcerclock.ACTION_TOGGLE_SERVICE"
        const val ACTION_UPDATE_UI_LOCAL = "com.dzo.announcerclock.ACTION_UPDATE_UI"
    }

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
    var totalMillis = 0L
    var intervalMillis = 0L
    var totalRunMillis = 0L

    private val _progressFlow = MutableStateFlow(0)
    val progressFlow = _progressFlow.asStateFlow()

    private val _timeFlow = MutableStateFlow("00:00")
    val timeFlow = _timeFlow.asStateFlow()

    inner class TimerBinder : Binder() {
        fun getService(): TimerService = this@TimerService
    }

    // BroadcastReceiver for toggle action coming from notification
    private val toggleReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val action = intent?.action
            if (action == Constants.ACTION_TOGGLE_SERVICE_LOCAL) {
                if (isRunning) {
                    stopServiceManually()
                    sendServiceStatus(false)
                } else {
                    // start with last known totalTime (or default)
                    isRunning = true
                    startTimer(totalTime.takeIf { it > 0 } ?: 60_000L)
                    sendServiceStatus(true)
                }
                // refresh notification to update action label
                startForegroundServiceNotification()
            }
        }
    }

    private fun sendServiceStatus(enabled: Boolean) {
        // broadcast a UI update so fragment can toggle its switch
        val action = Constants.ACTION_UPDATE_UI_LOCAL
        val intent = Intent(action)
        intent.putExtra("enabled", enabled)
        sendBroadcast(intent)
    }

    override fun onCreate() {
        super.onCreate()
        tts = TextToSpeech(this, this)
        createForegroundNotificationChannel()

        // Register receiver for toggle action. Use package-scoped action intent.
        val actionToggle = Constants.ACTION_TOGGLE_SERVICE_LOCAL
        val filter = IntentFilter(actionToggle)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(toggleReceiver, filter,RECEIVER_NOT_EXPORTED)
        }

        phoneListener = PhoneCallListener.register(this) { state ->
            when (state) {
                TelephonyManager.CALL_STATE_RINGING,
                TelephonyManager.CALL_STATE_OFFHOOK -> {
                    if (AppPreferences.isEnableDuringPhoneCalls() == true) {
                        speakTts()
                    }
                }

                TelephonyManager.CALL_STATE_IDLE -> {
                    // nothing for now
                }
            }
        }

        audioListener?.register()
    }

    private fun speakTts() {
        if (!isRunning) {
            isRunning = true
            toast(App.appContext(), "tts speaking")
        }
    }

    private fun doNotSpeakTts() {
        if (isRunning) {
            isRunning = false
            tts?.stop()
            toast(App.appContext(), "tts not speaking")
        }
    }

    override fun onBind(intent: Intent?): IBinder = TimerBinder()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // ensure notification shows current state
        startForegroundServiceNotification()

        timerJob?.cancel()
        isRunning = false

        val isCustom = intent?.getBooleanExtra("isCustom", false) ?: false

        if (isCustom) {
            intervalMillis = intent.getLongExtra("intervalMillis", 60_000L)
            totalRunMillis = intent.getLongExtra("totalRunMillis", 300_000L)
            startCustomTimer(intervalMillis, totalRunMillis)
        } else {
            totalMillis = intent?.getLongExtra("totalMillis", 60_000L) ?: 60_000L
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

        // Build a broadcast intent that has only an action (no explicit component)
        val actionString = Constants.ACTION_TOGGLE_SERVICE_LOCAL
        val toggleIntent = Intent(actionString).apply {
            // limit broadcast to our app only
            `package` = packageName
        }
        val togglePendingIntent = PendingIntent.getBroadcast(
            this, 1, toggleIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val bigPictureBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_logo)
        val actionText = if (isRunning) "Disable" else "Enable" // correct label

        val builder = NotificationCompat.Builder(this, "timer_foreground_channel")
            .setContentTitle("Announcer Clock")
            .setContentText(if (isRunning) "Service is running" else "Service is stopped")
            .setSmallIcon(R.drawable.ic_logo)
            .setLargeIcon(bigPictureBitmap)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .addAction(
                R.drawable.ic_logo, // use appropriate action icon
                actionText,
                togglePendingIntent
            )
            .setPriority(NotificationCompat.PRIORITY_LOW)

        val notification = builder.build()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startForeground(
                1,
                notification,
                ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK
            )
        } else {
            startForeground(1, notification)
        }
    }

    // ----------------- Timer Logic -----------------
    private fun startTimer(totalMillisArg: Long) {
        totalTime = totalMillisArg
        if (isRunning) return
        isRunning = true

        timerJob?.cancel()
        timerJob = CoroutineScope(Dispatchers.Default).launch {
            var startTimeLocal = System.currentTimeMillis() - elapsedTime

            while (isActive) {
                if (!isRunning) {
                    delay(200)
                    continue
                }

                val now = System.currentTimeMillis()
                elapsedTime = now - startTimeLocal
                val remaining = totalTime - elapsedTime.coerceAtLeast(0)
                val progress = (elapsedTime.toFloat() / totalTime * 100).coerceIn(0f, 100f)

                val minutes = (remaining / 1000 / 60).toInt()
                val seconds = ((remaining / 1000) % 60).toInt()
                _progressFlow.value = progress.toInt()
                _timeFlow.value = String.format("%02d:%02d", minutes, seconds)

                if (elapsedTime >= totalTime) {
                    if (AppPreferences.isTimeSpeakingEnabled() == true) announceTime()
                    if (AppPreferences.isNotificationEnabled() == true) showCompletionNotification()
                    startTimeLocal = System.currentTimeMillis()
                    elapsedTime = 0L
                }

                // broadcast time/progress update for UI (fragment)
                val uiAction = Constants.ACTION_UPDATE_UI_LOCAL
                val uiIntent = Intent(uiAction).apply {
                    putExtra("enabled", isRunning)
                    */
/*putExtra("progress", _progressFlow.value)
                    putExtra("time", _timeFlow.value)*//*

                    `package` = packageName
                }
                sendBroadcast(uiIntent)

                delay(1000)
            }
        }
    }

    private fun startCustomTimer(totalRunMillisArg: Long, howLongMillis: Long) {
        totalDurationCustom = howLongMillis
        if (isRunning) return
        isRunning = true

        timerJob?.cancel()
        timerJob = CoroutineScope(Dispatchers.Default).launch {
            startTime = System.currentTimeMillis() - elapsedTimeCustom
            lastAnnounceTime = startTime

            while (isActive) {
                if (!isRunning) {
                    delay(200)
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

                if (now - lastAnnounceTime >= totalRunMillisArg) {
                    lastAnnounceTime = now
                    if (AppPreferences.isTimeSpeakingEnabled() == true) announceTime()
                    if (AppPreferences.isNotificationEnabled() == true) showCompletionNotification()
                }

                if (elapsedTimeCustom >= totalDurationCustom) {
                    startTime = System.currentTimeMillis()
                    elapsedTimeCustom = 0L
                }

                // broadcast updates similarly
                val uiAction = Constants.ACTION_UPDATE_UI_LOCAL
                val uiIntent = Intent(uiAction).apply {
                    putExtra("enabled", isRunning)
                    */
/*putExtra("progress", _progressFlow.value)
                    putExtra("time", _timeFlow.value)*//*

                    `package` = packageName
                }
                sendBroadcast(uiIntent)

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

    // ----------------- Notification for completion -----------------
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
        val currentTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())
        val pendingIntent = PendingIntent.getActivity(
            this@TimerService, 0,
            Intent(this@TimerService, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(this@TimerService, channelId)
            .setContentTitle("Announcement \uD83D\uDCE2")
            .setContentText("Time announced at $currentTime")
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
        try {
            unregisterReceiver(toggleReceiver)
        } catch (e: Exception) {
            // ignore if already unregistered
        }
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

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
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
import com.dzo.announcerclock.App
import com.dzo.announcerclock.R
import com.dzo.announcerclock.data.local_source.AppPreferences
import com.dzo.announcerclock.domain.timer_usecase.AnnounceTimeUseCase
import com.dzo.announcerclock.presentation.activity.MainActivity
import com.dzo.announcerclock.utils.Constants
import com.dzo.announcerclock.utils.Constants.ACTION_STOP
import com.dzo.announcerclock.utils.Utils.toast
import com.dzo.announcerclock.utils.helper.AudioPlaybackListener
import com.dzo.announcerclock.utils.helper.PhoneCallListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
@AndroidEntryPoint
class TimerService : Service(), TextToSpeech.OnInitListener {

    @Inject
    lateinit var announceTimeUseCase: AnnounceTimeUseCase

    private var tts: TextToSpeech? = null
    private var timerJob: Job? = null
    private var isRunning = false
    private var isPaused = false
    private var isSpeaking = false

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
    var totalMillis = 0L
    var intervalMillis = 0L
    var totalRunMillis = 0L

    private val _progressFlow = MutableStateFlow(0)
    val progressFlow = _progressFlow.asStateFlow()

    private val _timeFlow = MutableStateFlow("00:00")
    val timeFlow = _timeFlow.asStateFlow()

    inner class TimerBinder : Binder() {
        fun getService(): TimerService = this@TimerService
    }

    private fun sendServiceStatus(enabled: Boolean) {
        val intent = Intent("com.dzo.announcerclock.SERVICE_STATUS")
        intent.putExtra("enabled", enabled)
        sendBroadcast(intent)
    }

    override fun onCreate() {
        super.onCreate()
        tts = TextToSpeech(this, this)
        createForegroundNotificationChannel()

        phoneListener = PhoneCallListener.register(this) { state ->
            when (state) {
                TelephonyManager.CALL_STATE_RINGING -> {
                    pauseTimer()
                    doNotSpeakTts()
                }

                TelephonyManager.CALL_STATE_OFFHOOK -> {
                    pauseTimer()
                    if (AppPreferences.isEnableDuringPhoneCalls()!!) {
                        speakTts()
                    } else {
                        doNotSpeakTts()
                    }
                }

                TelephonyManager.CALL_STATE_IDLE -> {
                    resumeTimer()
                }
            }
        }


        audioListener = AudioPlaybackListener(this) { isPlaying ->
            if (isPlaying) {
                if (tts?.isSpeaking == true) {
                    wasPausedByAudio = true
                    tts?.stop()
                }
            } else if (wasPausedByAudio) {
                wasPausedByAudio = false
            }
        }
        audioListener?.register()
    }

    private fun speakTts() {
        try {
            if (tts != null) {
                tts!!.stop()
                isRunning = true
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun doNotSpeakTts() {
        if (isRunning) {
            try {
                if (tts != null && tts!!.isSpeaking) {
                    tts!!.stop()
                }
                isRunning = false
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun pauseTimer() {
        if (isRunning && !isPaused) {
            isPaused = true
            isRunning = false
        }
    }
    private fun resumeTimer() {
        if (isPaused) {
            isPaused = false
            isRunning = true
            startTime = System.currentTimeMillis() - elapsedTime // Resume correctly
        }
    }

    override fun onBind(intent: Intent?): IBinder = TimerBinder()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForegroundServiceNotification()

        timerJob?.cancel()
        isRunning = false

        val isCustom = intent?.getBooleanExtra("isCustom", false) ?: false

        if (isCustom) {
            intervalMillis = intent.getLongExtra("intervalMillis", 60_000L)
            totalRunMillis = intent.getLongExtra("totalRunMillis", 300_000L)
            startCustomTimer(intervalMillis, totalRunMillis)
        } else {
            totalMillis = intent?.getLongExtra("totalMillis", 60_000L) ?: 60_000L
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

    // ----------------- Timer Logic -----------------
    private fun startTimer(totalMillis: Long) {
        totalTime = totalMillis
        if (isRunning) return
        isRunning = true

        timerJob?.cancel()
        timerJob = CoroutineScope(Dispatchers.Default).launch {
            var startTime = System.currentTimeMillis() - elapsedTime

            while (isActive) {
                if (!isRunning) {
                    delay(200)
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

                /*if (elapsedTime >= totalTime) {
                    if (AppPreferences.isTimeSpeakingEnabled() == true) announceTime()
                    if (AppPreferences.isNotificationEnabled() == true) showCompletionNotification()
                    startTime = System.currentTimeMillis()
                    elapsedTime = 0L
                }
                broadcastState(Constants.ACTION_TOGGLE_UPDATE)
                delay(1000)*/

                if (elapsedTime >= totalTime) {
                    elapsedTime = 0L
                    startTime = System.currentTimeMillis()

                    if (AppPreferences.isTimeSpeakingEnabled() == true) announceTime()
                    if (AppPreferences.isNotificationEnabled() == true) showCompletionNotification()
                }

                broadcastState(Constants.ACTION_TOGGLE_UPDATE)
                delay(1000)
            }

        }
    }

    /*private fun startTimer(totalMillis: Long) {
        totalTime = totalMillis
        if (isRunning) return

        isRunning = true
        isPaused = false
        startTime = System.currentTimeMillis() - elapsedTime

        timerJob?.cancel()
        timerJob = CoroutineScope(Dispatchers.Default).launch {
            while (isActive) {
                if (!isRunning || isPaused) {
                    delay(200)
                    continue
                }

                if (!isSpeaking) {
                    val now = System.currentTimeMillis()
                    elapsedTime = now - startTime
                }


                // Prevent negative time
                val remaining = (totalTime - elapsedTime).coerceAtLeast(0)
                val progress = (elapsedTime.toFloat() / totalTime * 100).coerceIn(0f, 100f)

                val minutes = (remaining / 1000 / 60).toInt()
                val seconds = ((remaining / 1000) % 60).toInt()
                _progressFlow.value = progress.toInt()
                _timeFlow.value = String.format("%02d:%02d", minutes, seconds)

                if (elapsedTime >= totalTime) {
                    elapsedTime = 0L
                    startTime = System.currentTimeMillis()

                    if (AppPreferences.isTimeSpeakingEnabled() == true) announceTime()
                    if (AppPreferences.isNotificationEnabled() == true) showCompletionNotification()
                }

                broadcastState(Constants.ACTION_TOGGLE_UPDATE)
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
                    delay(200)
                    continue
                }
                val now = System.currentTimeMillis()
                if (!isSpeaking) {
                    elapsedTimeCustom = now - startTime
                }

                val remaining = (totalDurationCustom - elapsedTimeCustom).coerceAtLeast(0)
                val minutes = (remaining / 1000 / 60).toInt()
                val seconds = ((remaining / 1000) % 60).toInt()
                val formattedTime = String.format("%02d:%02d", minutes, seconds)
                val progress =
                    (elapsedTimeCustom.toFloat() / totalDurationCustom * 100).coerceIn(0f, 100f)

                _progressFlow.value = progress.toInt()
                _timeFlow.value = formattedTime

                if (now - lastAnnounceTime >= totalRunMillis) {
                    lastAnnounceTime = now
                    if (AppPreferences.isTimeSpeakingEnabled() == true) announceTime()
                    if (AppPreferences.isNotificationEnabled() == true) showCompletionNotification()
                }

                if (elapsedTimeCustom >= totalDurationCustom) {
                    startTime = System.currentTimeMillis()
                    elapsedTimeCustom = 0L
                    sendBroadcast(
                        Intent(ACTION_STOP).apply {
                            `package` = packageName
                        }
                    )
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
        val locale = if (localeParts.size == 2)
            Locale(localeParts[0], localeParts[1])
        else Locale.getDefault()
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
            isSpeaking = true
            withContext(Dispatchers.Main) {
                tts?.speak(message, TextToSpeech.QUEUE_FLUSH, null, null)
            }
            while (tts?.isSpeaking == true) {
                delay(200)
            }
            isSpeaking = false
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
                if (it.id.startsWith("timer_channel_") && it.id != channelId)
                    nm.deleteNotificationChannel(it.id)
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

        val currentTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())
        val pendingIntent = PendingIntent.getActivity(
            this@TimerService, 0,
            Intent(this@TimerService, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(this@TimerService, channelId)
            .setContentTitle("Announcement \uD83D\uDCE2")
            .setContentText("Clock announced at $currentTime")
            .setSmallIcon(R.drawable.ic_logo)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        if (isSoundEnabled && soundUri != null && Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
            builder.setSound(soundUri)

        nm.notify(2, builder.build())
    }

    private fun broadcastState(action: String?) {
        val actionIntent = Intent(action)
        sendBroadcast(actionIntent)
    }

    override fun onDestroy() {
        isRunning = false
        timerJob?.cancel()
        tts?.stop()
        tts?.shutdown()
        audioListener?.unregister()
        phoneListener?.let { PhoneCallListener.unregister(this, it) }
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
