package com.dzo.announcerclock.domain.timer_usecase

import com.dzo.announcerclock.data.timer_repository.TimerRepository
import javax.inject.Inject

class AnnounceTimeUseCase @Inject constructor(
    private val timerRepository: TimerRepository
) {
    operator fun invoke(): String?{
        val currentTime = timerRepository.getCurrentTime()
        return currentTime

    }
}