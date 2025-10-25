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
import com.dzo.announcerclock.data.service.ScheduleTimerService
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
   /* private val scheduleFinishedReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val isEnabled = intent?.getBooleanExtra(EXTRA_IS_ENABLED, false) ?: false
            _isScheduleFinished.value = isEnabled
        }
    }*/




    /*init {
        val filter = IntentFilter(ACTION_TOGGLE_UPDATE)
        *//*ContextCompat.registerReceiver(
            context,
            scheduleFinishedReceiver,
            filter,
            ContextCompat.RECEIVER_NOT_EXPORTED
        )*//*
       *//* context.registerReceiver(
            scheduleFinishedReceiver,
            filter,
            Context.RECEIVER_NOT_EXPORTED
        )*//*
        registerToggleReceiver(context)

    }*/

    init {
        val filter = IntentFilter("SCHEDULE_FINISHED")
        registerReceiver(context, object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                _isScheduleFinished.value = true
            }
        }, filter, ContextCompat.RECEIVER_NOT_EXPORTED)
    }

    private fun registerToggleReceiver(context: Context) {
        val filter = IntentFilter(ACTION_TOGGLE_UPDATE)

        scheduleFinishedReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val isEnabled = intent?.getBooleanExtra(EXTRA_IS_ENABLED, false) ?: false
                toast(App.appContext(),"Received toggle update: $isEnabled")
                _isScheduleFinished.value = isEnabled
            }
        }

        registerReceiver(
            context,
            scheduleFinishedReceiver,
            filter,
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

        // Reset states
        AppPreferences.saveCustomToggleState(false)
        //PreferenceHelper.remove(Constants.KEY_CUSTOM_TOGGLE_STATE)
    }

    override fun onCleared() {
        super.onCleared()
        try {
            context.unregisterReceiver(scheduleFinishedReceiver)
        } catch (e: Exception) {
         e.printStackTrace()
        }
    }

}