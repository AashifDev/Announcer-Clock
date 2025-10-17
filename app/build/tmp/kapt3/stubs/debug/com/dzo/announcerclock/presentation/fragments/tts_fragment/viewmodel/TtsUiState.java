package com.dzo.announcerclock.presentation.fragments.tts_fragment.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BW\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\n\u0012\b\b\u0002\u0010\r\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\u0005H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\nH\u00c6\u0003J\t\u0010\u001d\u001a\u00020\nH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\nH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\nH\u00c6\u0003J[\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\nH\u00c6\u0001J\u0013\u0010!\u001a\u00020\n2\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010#\u001a\u00020$H\u00d6\u0001J\t\u0010%\u001a\u00020&H\u00d6\u0001R\u0011\u0010\f\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\r\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\u000b\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013\u00a8\u0006\'"}, d2 = {"Lcom/dzo/announcerclock/presentation/fragments/tts_fragment/viewmodel/TtsUiState;", "", "settings", "Lcom/dzo/announcerclock/presentation/fragments/home_fragment/model/TtsSettings;", "languages", "", "Ljava/util/Locale;", "voices", "Landroid/speech/tts/Voice;", "timeSpeakingEnabled", "", "ttsReady", "disableDuringPhoneCalls", "disableWhilePlayingMusic", "(Lcom/dzo/announcerclock/presentation/fragments/home_fragment/model/TtsSettings;Ljava/util/List;Ljava/util/List;ZZZZ)V", "getDisableDuringPhoneCalls", "()Z", "getDisableWhilePlayingMusic", "getLanguages", "()Ljava/util/List;", "getSettings", "()Lcom/dzo/announcerclock/presentation/fragments/home_fragment/model/TtsSettings;", "getTimeSpeakingEnabled", "getTtsReady", "getVoices", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "", "app_debug"})
public final class TtsUiState {
    @org.jetbrains.annotations.NotNull()
    private final com.dzo.announcerclock.presentation.fragments.home_fragment.model.TtsSettings settings = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.util.Locale> languages = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<android.speech.tts.Voice> voices = null;
    private final boolean timeSpeakingEnabled = false;
    private final boolean ttsReady = false;
    private final boolean disableDuringPhoneCalls = false;
    private final boolean disableWhilePlayingMusic = false;
    
    public TtsUiState(@org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.presentation.fragments.home_fragment.model.TtsSettings settings, @org.jetbrains.annotations.NotNull()
    java.util.List<java.util.Locale> languages, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends android.speech.tts.Voice> voices, boolean timeSpeakingEnabled, boolean ttsReady, boolean disableDuringPhoneCalls, boolean disableWhilePlayingMusic) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.dzo.announcerclock.presentation.fragments.home_fragment.model.TtsSettings getSettings() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.util.Locale> getLanguages() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<android.speech.tts.Voice> getVoices() {
        return null;
    }
    
    public final boolean getTimeSpeakingEnabled() {
        return false;
    }
    
    public final boolean getTtsReady() {
        return false;
    }
    
    public final boolean getDisableDuringPhoneCalls() {
        return false;
    }
    
    public final boolean getDisableWhilePlayingMusic() {
        return false;
    }
    
    public TtsUiState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.dzo.announcerclock.presentation.fragments.home_fragment.model.TtsSettings component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.util.Locale> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<android.speech.tts.Voice> component3() {
        return null;
    }
    
    public final boolean component4() {
        return false;
    }
    
    public final boolean component5() {
        return false;
    }
    
    public final boolean component6() {
        return false;
    }
    
    public final boolean component7() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.dzo.announcerclock.presentation.fragments.tts_fragment.viewmodel.TtsUiState copy(@org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.presentation.fragments.home_fragment.model.TtsSettings settings, @org.jetbrains.annotations.NotNull()
    java.util.List<java.util.Locale> languages, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends android.speech.tts.Voice> voices, boolean timeSpeakingEnabled, boolean ttsReady, boolean disableDuringPhoneCalls, boolean disableWhilePlayingMusic) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}