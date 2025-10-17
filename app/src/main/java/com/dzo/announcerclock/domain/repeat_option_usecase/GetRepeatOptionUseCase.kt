package com.dzo.announcerclock.domain.repeat_option_usecase

import com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption
import com.dzo.announcerclock.data.repeat_repository.RepeatOptionRepository
import javax.inject.Inject

class GetRepeatOptionUseCase @Inject constructor(
    private val repeatOptionRepository: RepeatOptionRepository
) {
    operator fun invoke(): List<RepeatOption> {
        return repeatOptionRepository.getRepeatOption()
    }
}