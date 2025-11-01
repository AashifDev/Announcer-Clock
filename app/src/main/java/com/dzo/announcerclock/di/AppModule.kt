package com.dzo.announcerclock.di

import android.content.Context
import com.dzo.announcerclock.data.repeat_repository.RepeatOptionRepository
import com.dzo.announcerclock.data.repeat_repository.RepeatOptionRepositoryImpl
import com.dzo.announcerclock.data.sound_repository.SoundOptionRepository
import com.dzo.announcerclock.data.sound_repository.SoundOptionRepositoryImpl
import com.dzo.announcerclock.data.timer_repository.TimerRepository
import com.dzo.announcerclock.data.timer_repository.TimerRepositoryImpl
import com.dzo.announcerclock.domain.tts_usecase.GetTtsSettingsUseCase
import com.dzo.announcerclock.domain.tts_usecase.IsDisableWhilePlayingMusicUseCase
import com.dzo.announcerclock.domain.tts_usecase.IsEnableDuringPhoneCallsUseCase
import com.dzo.announcerclock.domain.tts_usecase.IsTimeSpeakingEnabledUseCase
import com.dzo.announcerclock.domain.tts_usecase.SaveDisableWhilePlayingMusicUseCase
import com.dzo.announcerclock.domain.tts_usecase.SaveEnableDuringPhoneCallsUseCase
import com.dzo.announcerclock.domain.tts_usecase.SaveTimeSpeakingEnabledUseCase
import com.dzo.announcerclock.domain.tts_usecase.SaveTtsSettingsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSoundOptionRepository(
        @ApplicationContext context: Context
    ): SoundOptionRepository = SoundOptionRepositoryImpl(context)

    @Provides
    @Singleton
    fun provideRepeatOptionRepository(): RepeatOptionRepository = RepeatOptionRepositoryImpl()

    @Provides
    @Singleton
    fun provideTimerRepository(): TimerRepository = TimerRepositoryImpl()

    @Provides
    @Singleton
    fun provideTtsUseCases(
        getTtsSettings: GetTtsSettingsUseCase,
        saveTtsSettings: SaveTtsSettingsUseCase,
        isTimeSpeakingEnabled: IsTimeSpeakingEnabledUseCase,
        saveTimeSpeakingEnabled: SaveTimeSpeakingEnabledUseCase,
        saveEnableDuringPhoneCalls: SaveEnableDuringPhoneCallsUseCase,
        isEnableDuringPhoneCalls: IsEnableDuringPhoneCallsUseCase,
        saveDisableWhilePlayingMusic: SaveDisableWhilePlayingMusicUseCase,
        isDisableWhilePlayingMusic: IsDisableWhilePlayingMusicUseCase
    ): TtsUseCases {
        return TtsUseCases(
            getTtsSettings,
            saveTtsSettings,
            isTimeSpeakingEnabled,
            saveTimeSpeakingEnabled,
            saveEnableDuringPhoneCalls,
            isEnableDuringPhoneCalls,
            saveDisableWhilePlayingMusic,
            isDisableWhilePlayingMusic
        )
    }
}

data class TtsUseCases(
    val getTtsSettings: GetTtsSettingsUseCase,
    val saveTtsSettings: SaveTtsSettingsUseCase,
    val isTimeSpeakingEnabled: IsTimeSpeakingEnabledUseCase,
    val saveTimeSpeakingEnabled: SaveTimeSpeakingEnabledUseCase,
    val saveEnableDuringPhoneCalls: SaveEnableDuringPhoneCallsUseCase,
    val isEnableDuringPhoneCalls: IsEnableDuringPhoneCallsUseCase,
    val saveDisableWhilePlayingMusic: SaveDisableWhilePlayingMusicUseCase,
    val isDisableWhilePlayingMusic: IsDisableWhilePlayingMusicUseCase
)