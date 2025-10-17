package com.dzo.announcerclock.data.tts_repository


import com.dzo.announcerclock.data.local_source.AppPreferences
import com.dzo.announcerclock.presentation.fragments.home_fragment.model.TtsSettings
import javax.inject.Inject

class TtsRepositoryImpl @Inject constructor() : TtsRepository {
    override suspend fun getTtsSettings(): TtsSettings = AppPreferences.getTtsSettings()
    override suspend fun saveTtsSettings(settings: TtsSettings) = AppPreferences.saveTtsSettings(settings)
    override suspend fun isTimeSpeakingEnabled(): Boolean = AppPreferences.isTimeSpeakingEnabled() ?: true
    override suspend fun saveTimeSpeakingEnabled(enabled: Boolean) = AppPreferences.saveTimeSpeakingEnabled(enabled)
    override suspend fun saveDisableDuringPhoneCalls(disable: Boolean) = AppPreferences.saveDisableDuringPhoneCalls(disable)
    override suspend fun isDisableDuringPhoneCalls(): Boolean = AppPreferences.isDisableDuringPhoneCalls() ?: false
    override suspend fun saveDisableWhilePlayingMusic(disable: Boolean) = AppPreferences.saveDisableWhilePlayingMusic(disable)
    override suspend fun isDisableWhilePlayingMusic(): Boolean = AppPreferences.isDisableWhilePlayingMusic() ?: false
}
