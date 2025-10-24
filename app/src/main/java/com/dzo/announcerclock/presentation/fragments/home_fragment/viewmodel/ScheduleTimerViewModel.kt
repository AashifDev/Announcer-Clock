package com.dzo.announcerclock.presentation.fragments.home_fragment.viewmodel

import android.annotation.SuppressLint
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
import com.dzo.announcerclock.data.service.ScheduleTimerService
import com.dzo.announcerclock.data.service.TimerService
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
class ScheduleTimerViewModel
@Inject constructor(
    @ApplicationContext private val context: Context,
) : ViewModel() {

    @SuppressLint("StaticFieldLeak")
    private var timerService: ScheduleTimerService? = null
    private var isBound = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            val serviceBinder = binder as ScheduleTimerService.TimerBinder
            timerService = serviceBinder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
            timerService = null
        }
    }

    fun startScheduleTimer(startTimeMillis: Long, endTimeMillis: Long, intervalMillis: Long) {
        val intervalMillis = intervalMillis * 60 * 1000L
        startScheduleTimerService(startTimeMillis, endTimeMillis, intervalMillis)
    }

    private fun startScheduleTimerService(
        startTimeMillis: Long,
        endTimeMillis: Long,
        intervalMillis: Long
    ) {
        val intent = Intent(context, ScheduleTimerService::class.java).apply {
            putExtra("startTimeMillis", startTimeMillis)
            putExtra("endTimeMillis", endTimeMillis)
            putExtra("intervalMillis", intervalMillis)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(context, intent)
        } else {
            context.startService(intent)
        }
        context.bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

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
            context.stopService(Intent(context, ScheduleTimerService::class.java))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Reset states
        AppPreferences.saveCustomToggleState(false)
        //PreferenceHelper.remove(Constants.KEY_CUSTOM_TOGGLE_STATE)
    }


}