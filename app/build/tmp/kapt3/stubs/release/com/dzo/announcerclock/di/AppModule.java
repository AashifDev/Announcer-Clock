package com.dzo.announcerclock.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\u0012\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\t\u001a\u00020\nH\u0007\u00a8\u0006\u000b"}, d2 = {"Lcom/dzo/announcerclock/di/AppModule;", "", "()V", "provideRepeatOptionRepository", "Lcom/dzo/announcerclock/data/repeat_repository/RepeatOptionRepository;", "provideSoundOptionRepository", "Lcom/dzo/announcerclock/data/sound_repository/SoundOptionRepository;", "context", "Landroid/content/Context;", "provideTimerRepository", "Lcom/dzo/announcerclock/data/timer_repository/TimerRepository;", "app_release"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class AppModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.dzo.announcerclock.di.AppModule INSTANCE = null;
    
    private AppModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.dzo.announcerclock.data.sound_repository.SoundOptionRepository provideSoundOptionRepository(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.dzo.announcerclock.data.repeat_repository.RepeatOptionRepository provideRepeatOptionRepository() {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.dzo.announcerclock.data.timer_repository.TimerRepository provideTimerRepository() {
        return null;
    }
}