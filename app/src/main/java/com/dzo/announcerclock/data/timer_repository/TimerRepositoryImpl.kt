package com.dzo.announcerclock.data.timer_repository

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class TimerRepositoryImpl @Inject constructor(): TimerRepository {
    override fun getCurrentTime(): String {
        val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return formatter.format(Date())
    }
}