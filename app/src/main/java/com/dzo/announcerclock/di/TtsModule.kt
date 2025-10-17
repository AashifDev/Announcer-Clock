package com.dzo.announcerclock.di

import com.dzo.announcerclock.data.tts_repository.TtsRepository
import com.dzo.announcerclock.data.tts_repository.TtsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TtsModule {
    @Binds
    abstract fun bindTtsRepository(
        impl: TtsRepositoryImpl
    ): TtsRepository
}
