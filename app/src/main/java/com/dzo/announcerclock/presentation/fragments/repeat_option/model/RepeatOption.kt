package com.dzo.announcerclock.presentation.fragments.repeat_option.model

data class RepeatOption(
    val id: Int? = null,
    val title: String,
    val valueInMinute: Long? = null,
    var isSelected: Boolean = false,
    var custom: Pair<Long,Long>? = null
)