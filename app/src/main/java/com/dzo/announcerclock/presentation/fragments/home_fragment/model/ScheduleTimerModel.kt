package com.dzo.announcerclock.presentation.fragments.home_fragment.model

data class ScheduleTimerModel(
    val isEnabled: Boolean = false,
    val startTimeMillis: Long? = null,
    val endTimeMillis: Long? = null,
    val intervalMillis: Long? = null
)
