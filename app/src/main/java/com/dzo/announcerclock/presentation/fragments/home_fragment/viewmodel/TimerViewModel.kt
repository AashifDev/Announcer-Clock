package com.dzo.announcerclock.presentation.fragments.home_fragment.viewmodel

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.IBinder
import androidx.core.content.ContextCompat.startForegroundService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzo.announcerclock.data.local_source.AppPreferences
import com.dzo.announcerclock.data.service.TimerService
import com.dzo.announcerclock.domain.timer_usecase.AnnounceTimeUseCase
import com.dzo.announcerclock.utils.Constants
import com.dzo.announcerclock.utils.PreferenceHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class TimerViewModel @Inject constructor(
    private val announceTimeUseCase: AnnounceTimeUseCase,
    @ApplicationContext private val context: Context,
) : ViewModel() {

    private val _announcement = MutableStateFlow<String?>(null)
    val announcement = _announcement.asStateFlow()

    private val _progress = MutableStateFlow(0)
    val progress = _progress.asStateFlow()

    private val _timeText = MutableStateFlow("00:00")
    val timeText = _timeText.asStateFlow()

    private var timerJob: Job? = null

    private var timerService: TimerService? = null
    private var isBound = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            val serviceBinder = binder as TimerService.TimerBinder
            timerService = serviceBinder.getService()
            isBound = true
            observeServiceFlows()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
            timerService = null
        }
    }


    fun startTimer(intervalSeconds: Long) {
        /*timerJob?.cancel()
        timerJob = viewModelScope.launch {
            val totalMillis = intervalSeconds * 60 * 1000L
            var startTime = AppPreferences.getStartTime().takeIf { it > 0 } ?: System.currentTimeMillis()
            AppPreferences.saveStartTime(startTime)


            while (isActive) {
                val now = System.currentTimeMillis()
               // val elapsed = now % totalMillis
                val elapsed = now - startTime
                val remaining = totalMillis - elapsed.coerceAtLeast(0)

                // progress %
                val prog = (elapsed.toFloat() / totalMillis) * 100
                _progress.value = prog.coerceIn(0f, 100f).toInt()

                // time text
                val minutes = (remaining / 1000 / 60).toInt()
                val seconds = ((remaining / 1000) % 60).toInt()
                _timeText.value = String.format("%02d:%02d", minutes, seconds)

                if (elapsed >= totalMillis) {
                    val message = announceTimeUseCase()
                    _announcement.value = message

                    startTime = System.currentTimeMillis()
                    AppPreferences.saveStartTime(startTime)
                }

                delay(16)
            }
        }*/
        val totalMillis = intervalSeconds * 60 * 1000L // announce every 1 minute
        startTimerService(totalMillis)

    }

    fun startCustomTimer(intervalMinute: Long, totalRunMinute: Long) {
        val intervalMillis = intervalMinute * 60 * 1000L // minutes → millis
        val totalRunMillis = totalRunMinute * 60 * 1000L // minutes → millis
        startCustomTimerService(intervalMillis,totalRunMillis)

    }


    private fun startTimerService(totalMillis: Long) {
        val intent = Intent(context, TimerService::class.java).apply {
            putExtra("totalMillis", totalMillis)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(context, intent)
        } else {
            context.startService(intent)
        }
        context.bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    private fun startCustomTimerService(totalMillis: Long, totalRunMillis: Long) {
        val intent = Intent(context, TimerService::class.java).apply {
            putExtra("isCustom", true)
            putExtra("intervalMillis", totalMillis)
            putExtra("totalRunMillis", totalRunMillis)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(context, intent)
        } else {
            context.startService(intent)
        }
        context.bindService(intent, connection, Context.BIND_AUTO_CREATE)

    }

    private fun observeServiceFlows() {
        viewModelScope.launch {
            timerService?.progressFlow?.collect {
                _progress.value = it
            }
        }

        viewModelScope.launch {
            timerService?.timeFlow?.collect {
                _timeText.value = it
            }
        }
    }

    /*fun stopTimer() {
        context.stopService(Intent(context, TimerService::class.java))
        if (isBound) {
            context.unbindService(connection)
            isBound = false
        }

        AppPreferences.saveToggleState(false)
        PreferenceHelper.remove(Constants.KEY_START_TIME)
        _progress.value = 0
        _timeText.value = "00:00"

        //timerJob?.cancel()
    }*/

    fun stopTimer() {
        // Tell service to stop itself
        timerService?.stopServiceManually()

        // Unbind connection
        if (isBound) {
            try {
                context.unbindService(connection)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            isBound = false
        }

        // Stop service explicitly (safe fallback)
        try {
            context.stopService(Intent(context, TimerService::class.java))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Reset states
        AppPreferences.saveToggleState(false)
        PreferenceHelper.remove(Constants.KEY_START_TIME)
        _progress.value = 0
        _timeText.value = "00:00"
    }


}