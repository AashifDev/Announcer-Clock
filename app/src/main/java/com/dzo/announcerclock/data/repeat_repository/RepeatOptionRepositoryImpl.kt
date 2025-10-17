package com.dzo.announcerclock.data.repeat_repository

import com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption
import javax.inject.Inject

class RepeatOptionRepositoryImpl @Inject constructor(
) : RepeatOptionRepository {
    override fun getRepeatOption(): List<RepeatOption> {
        val getRepeatOption = listOf(
            RepeatOption(id = 1, title = "1 Minute", valueInMinute = 1),
            RepeatOption(id = 2, title = "2 Minute", valueInMinute = 2),
            RepeatOption(id = 3, title = "5 Minute", valueInMinute = 5),
            RepeatOption(id = 4, title = "10 Minute", valueInMinute = 10),
            RepeatOption(id = 5, title = "15 Minute", valueInMinute = 15),
            RepeatOption(id = 6, title = "30 Minute", valueInMinute = 30),
            RepeatOption(id = 7, title = "1 Hour", valueInMinute = 60),
            RepeatOption(id = 8, title = "Custom"),
        )
        return getRepeatOption
    }
}