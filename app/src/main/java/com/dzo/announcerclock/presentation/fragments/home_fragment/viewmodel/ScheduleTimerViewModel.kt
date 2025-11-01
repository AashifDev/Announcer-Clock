package com.dzo.announcerclock.presentation.fragments.home_fragment.viewmodel

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.os.Build
import android.os.IBinder
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.registerReceiver
import androidx.core.content.ContextCompat.startForegroundService
import androidx.lifecycle.ViewModel
import com.dzo.announcerclock.App
import com.dzo.announcerclock.data.local_source.AppPreferences
import com.dzo.announcerclock.data.local_source.isScheduleTimeExist
import com.dzo.announcerclock.data.service.ScheduleTimerService
import com.dzo.announcerclock.utils.Constants.ACTION_STOP
import com.dzo.announcerclock.utils.Constants.ACTION_TOGGLE_UPDATE
import com.dzo.announcerclock.utils.Constants.EXTRA_IS_ENABLED
import com.dzo.announcerclock.utils.Utils.toast
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ScheduleTimerViewModel
@Inject constructor(
    @ApplicationContext private val context: Context,
) : ViewModel() {

    @SuppressLint("StaticFieldLeak")
    private var timerService: ScheduleTimerService? = null
    private var isBound = false
    private val _isScheduleFinished = MutableStateFlow(false)
    val isScheduleFinished = _isScheduleFinished.asStateFlow()
    private var scheduleFinishedReceiver: BroadcastReceiver? = null

    private val _isScheduleStart = MutableStateFlow(false)
    val isScheduleStart = _isScheduleStart.asStateFlow()
    private var scheduleStartReceiver: BroadcastReceiver? = null

    init {
        registerToggleReceiver(context)
    }

    private fun registerToggleReceiver(context: Context) {
        val filter = IntentFilter(ACTION_TOGGLE_UPDATE)
        val filter1 = IntentFilter(ACTION_STOP)

        scheduleFinishedReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                _isScheduleFinished.value = true
            }
        }
        scheduleStartReceiver = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                _isScheduleStart.value = true
            }
        }

        registerReceiver(
            context,
            scheduleFinishedReceiver,
            filter,
            ContextCompat.RECEIVER_NOT_EXPORTED
        )
        registerReceiver(
            context,
            scheduleStartReceiver,
            filter1,
            ContextCompat.RECEIVER_NOT_EXPORTED
        )
    }

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
        timerService?.stopServiceManually()
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
        AppPreferences.saveCustomToggleState(false)
    }

    override fun onCleared() {
        super.onCleared()
        try {
            //context.unregisterReceiver(scheduleFinishedReceiver)
        } catch (e: Exception) {
         e.printStackTrace()
        }
    }

}