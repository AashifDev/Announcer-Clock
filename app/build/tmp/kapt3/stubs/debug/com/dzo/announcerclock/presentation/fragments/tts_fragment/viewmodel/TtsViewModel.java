package com.dzo.announcerclock.presentation.fragments.tts_fragment.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0012H\u0014J\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020\u0012J\u000e\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u001bJ\u000e\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u001bJ\u000e\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\u001bR\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/dzo/announcerclock/presentation/fragments/tts_fragment/viewmodel/TtsViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "Landroid/speech/tts/TextToSpeech$OnInitListener;", "application", "Landroid/app/Application;", "ttsUseCases", "Lcom/dzo/announcerclock/di/TtsUseCases;", "(Landroid/app/Application;Lcom/dzo/announcerclock/di/TtsUseCases;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/dzo/announcerclock/presentation/fragments/tts_fragment/viewmodel/TtsUiState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "tts", "Landroid/speech/tts/TextToSpeech;", "loadLanguages", "", "onCleared", "onInit", "status", "", "selectLanguage", "locale", "Ljava/util/Locale;", "init", "", "selectVoice", "voice", "Landroid/speech/tts/Voice;", "speakCurrentTime", "toggleDisableWhilePlayingMusic", "disable", "toggleEnableDuringPhoneCalls", "enable", "toggleTimeSpeaking", "enabled", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class TtsViewModel extends androidx.lifecycle.AndroidViewModel implements android.speech.tts.TextToSpeech.OnInitListener {
    @org.jetbrains.annotations.NotNull()
    private final com.dzo.announcerclock.di.TtsUseCases ttsUseCases = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.dzo.announcerclock.presentation.fragments.tts_fragment.viewmodel.TtsUiState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.dzo.announcerclock.presentation.fragments.tts_fragment.viewmodel.TtsUiState> state = null;
    @org.jetbrains.annotations.Nullable()
    private android.speech.tts.TextToSpeech tts;
    
    @javax.inject.Inject()
    public TtsViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application, @org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.di.TtsUseCases ttsUseCases) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.dzo.announcerclock.presentation.fragments.tts_fragment.viewmodel.TtsUiState> getState() {
        return null;
    }
    
    @java.lang.Override()
    public void onInit(int status) {
    }
    
    private final void loadLanguages() {
    }
    
    public final void selectLanguage(@org.jetbrains.annotations.NotNull()
    java.util.Locale locale, boolean init) {
    }
    
    public final void selectVoice(@org.jetbrains.annotations.NotNull()
    android.speech.tts.Voice voice) {
    }
    
    public final void toggleTimeSpeaking(boolean enabled) {
    }
    
    public final void toggleEnableDuringPhoneCalls(boolean enable) {
    }
    
    public final void toggleDisableWhilePlayingMusic(boolean disable) {
    }
    
    public final void speakCurrentTime() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}