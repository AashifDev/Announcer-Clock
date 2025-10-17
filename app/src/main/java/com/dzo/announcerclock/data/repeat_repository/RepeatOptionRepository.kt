package com.dzo.announcerclock.data.repeat_repository

import com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption

interface RepeatOptionRepository{
    fun getRepeatOption(): List<RepeatOption>
}