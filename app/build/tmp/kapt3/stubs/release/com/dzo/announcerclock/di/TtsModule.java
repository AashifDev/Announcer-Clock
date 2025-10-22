package com.dzo.announcerclock.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'\u00a8\u0006\u0007"}, d2 = {"Lcom/dzo/announcerclock/di/TtsModule;", "", "()V", "bindTtsRepository", "Lcom/dzo/announcerclock/data/tts_repository/TtsRepository;", "impl", "Lcom/dzo/announcerclock/data/tts_repository/TtsRepositoryImpl;", "app_release"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract class TtsModule {
    
    public TtsModule() {
        super();
    }
    
    @dagger.Binds()
    @org.jetbrains.annotations.NotNull()
    public abstract com.dzo.announcerclock.data.tts_repository.TtsRepository bindTtsRepository(@org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.data.tts_repository.TtsRepositoryImpl impl);
}