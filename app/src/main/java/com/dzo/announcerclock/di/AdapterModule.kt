package com.dzo.announcerclock.di

import com.dzo.announcerclock.presentation.fragments.our_app_fragment.adapter.OtherAppAdapter
import com.dzo.announcerclock.presentation.fragments.repeat_option.adapter.RepeatOptionAdapter
import com.dzo.announcerclock.presentation.fragments.sound_fragment.adapter.SoundOptionAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdapterModule {

    @Provides
    @Singleton
    fun provideRepeatOptionAdapter(): RepeatOptionAdapter {
        return RepeatOptionAdapter()
    }


    @Singleton
    @Provides
    fun provideSoundOptionAdapter(): SoundOptionAdapter {
        return SoundOptionAdapter()
    }

    @Singleton
    @Provides
    fun provideOurOtherAppAdapter(): OtherAppAdapter {
        return OtherAppAdapter()
    }
}