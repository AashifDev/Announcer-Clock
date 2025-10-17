package com.dzo.announcerclock.data.service.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.dzo.announcerclock.data.service.TimerService

class TimerActionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            "ACTION_STOP_TIMER" -> {
                context?.stopService(Intent(context, TimerService::class.java))
                Toast.makeText(context, "Timer stopped", Toast.LENGTH_SHORT).show()
            }
            "ACTION_START_TIMER" -> {
                val startIntent = Intent(context, TimerService::class.java).apply {
                    putExtra("intervalSeconds", 1)
                }
                context?.startForegroundService(startIntent)
                Toast.makeText(context, "Timer started", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
