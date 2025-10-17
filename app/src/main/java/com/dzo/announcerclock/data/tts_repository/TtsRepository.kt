package com.dzo.announcerclock.data.tts_repository


import com.dzo.announcerclock.presentation.fragments.home_fragment.model.TtsSettings

interface TtsRepository {
    suspend fun getTtsSettings(): TtsSettings
    suspend fun saveTtsSettings(settings: TtsSettings)
    suspend fun isTimeSpeakingEnabled(): Boolean
    suspend fun saveTimeSpeakingEnabled(enabled: Boolean)
    suspend fun saveDisableDuringPhoneCalls(disable: Boolean)
    suspend fun isDisableDuringPhoneCalls(): Boolean
    suspend fun saveDisableWhilePlayingMusic(disable: Boolean)
    suspend fun isDisableWhilePlayingMusic(): Boolean
}
