package com.dzo.announcerclock.domain.tts_usecase

import com.dzo.announcerclock.data.tts_repository.TtsRepository
import com.dzo.announcerclock.presentation.fragments.home_fragment.model.TtsSettings
import javax.inject.Inject

class GetTtsSettingsUseCase @Inject constructor(
    private val repository: TtsRepository
) {
    suspend operator fun invoke(): TtsSettings = repository.getTtsSettings()
}

class SaveTtsSettingsUseCase @Inject constructor(
    private val repository: TtsRepository
) {
    suspend operator fun invoke(settings: TtsSettings) {
        repository.saveTtsSettings(settings)
    }
}

class IsTimeSpeakingEnabledUseCase @Inject constructor(
    private val repository: TtsRepository
) {
    suspend operator fun invoke(): Boolean = repository.isTimeSpeakingEnabled()
}

class SaveTimeSpeakingEnabledUseCase @Inject constructor(
    private val repository: TtsRepository
) {
    suspend operator fun invoke(enabled: Boolean) {
        repository.saveTimeSpeakingEnabled(enabled)
    }
}

class IsDisableDuringPhoneCallsUseCase @Inject constructor(
    private val repository: TtsRepository
) {
    suspend operator fun invoke(): Boolean = repository.isDisableDuringPhoneCalls()
}

class SaveDisableDuringPhoneCallsUseCase @Inject constructor(
    private val repository: TtsRepository
) {
    suspend operator fun invoke(disable: Boolean) {
        repository.saveDisableDuringPhoneCalls(disable)
    }
}

class IsDisableWhilePlayingMusicUseCase @Inject constructor(
    private val repository: TtsRepository

){
    suspend operator fun invoke(): Boolean = repository.isDisableWhilePlayingMusic()

}

class SaveDisableWhilePlayingMusicUseCase @Inject constructor(
    private val repository: TtsRepository
) {
    suspend operator fun invoke(disable: Boolean) {
        repository.saveDisableWhilePlayingMusic(disable)
    }
}