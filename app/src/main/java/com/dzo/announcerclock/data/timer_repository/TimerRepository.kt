package com.dzo.announcerclock.data.timer_repository

interface TimerRepository {
    fun getCurrentTime(): String
}