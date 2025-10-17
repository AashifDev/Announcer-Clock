package com.dzo.announcerclock.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\b\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0007\u001a\u00020\bH\u0007\u00a8\u0006\t"}, d2 = {"Lcom/dzo/announcerclock/di/AdapterModule;", "", "()V", "provideOurOtherAppAdapter", "Lcom/dzo/announcerclock/presentation/fragments/our_app_fragment/adapter/OtherAppAdapter;", "provideRepeatOptionAdapter", "Lcom/dzo/announcerclock/presentation/fragments/repeat_option/adapter/RepeatOptionAdapter;", "provideSoundOptionAdapter", "Lcom/dzo/announcerclock/presentation/fragments/sound_fragment/adapter/SoundOptionAdapter;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class AdapterModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.dzo.announcerclock.di.AdapterModule INSTANCE = null;
    
    private AdapterModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.dzo.announcerclock.presentation.fragments.repeat_option.adapter.RepeatOptionAdapter provideRepeatOptionAdapter() {
        return null;
    }
    
    @javax.inject.Singleton()
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.dzo.announcerclock.presentation.fragments.sound_fragment.adapter.SoundOptionAdapter provideSoundOptionAdapter() {
        return null;
    }
    
    @javax.inject.Singleton()
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.dzo.announcerclock.presentation.fragments.our_app_fragment.adapter.OtherAppAdapter provideOurOtherAppAdapter() {
        return null;
    }
}