package com.dzo.announcerclock.data.tts_repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0012\u00a8\u0006\u0013"}, d2 = {"Lcom/dzo/announcerclock/data/tts_repository/TtsRepository;", "", "getTtsSettings", "Lcom/dzo/announcerclock/presentation/fragments/home_fragment/model/TtsSettings;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isDisableDuringPhoneCalls", "", "isDisableWhilePlayingMusic", "isTimeSpeakingEnabled", "saveDisableDuringPhoneCalls", "", "disable", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveDisableWhilePlayingMusic", "saveTimeSpeakingEnabled", "enabled", "saveTtsSettings", "settings", "(Lcom/dzo/announcerclock/presentation/fragments/home_fragment/model/TtsSettings;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public abstract interface TtsRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTtsSettings(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.dzo.announcerclock.presentation.fragments.home_fragment.model.TtsSettings> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object saveTtsSettings(@org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.presentation.fragments.home_fragment.model.TtsSettings settings, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isTimeSpeakingEnabled(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object saveTimeSpeakingEnabled(boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object saveDisableDuringPhoneCalls(boolean disable, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isDisableDuringPhoneCalls(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object saveDisableWhilePlayingMusic(boolean disable, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isDisableWhilePlayingMusic(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
}