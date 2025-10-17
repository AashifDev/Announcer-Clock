package com.dzo.announcerclock.di

import android.content.Context
import com.dzo.announcerclock.data.repeat_repository.RepeatOptionRepository
import com.dzo.announcerclock.data.repeat_repository.RepeatOptionRepositoryImpl
import com.dzo.announcerclock.data.sound_repository.SoundOptionRepository
import com.dzo.announcerclock.data.sound_repository.SoundOptionRepositoryImpl
import com.dzo.announcerclock.data.timer_repository.TimerRepository
import com.dzo.announcerclock.data.timer_repository.TimerRepositoryImpl
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
}
