package com.dzo.announcerclock.presentation.fragments.tts_fragment.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002BO\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\u0002\u0010\u0015J\b\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020 H\u0014J\u0010\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020$H\u0016J\u0018\u0010%\u001a\u00020 2\u0006\u0010&\u001a\u00020\'2\b\b\u0002\u0010(\u001a\u00020)J\u000e\u0010*\u001a\u00020 2\u0006\u0010+\u001a\u00020,J\u0006\u0010-\u001a\u00020 J\u000e\u0010.\u001a\u00020 2\u0006\u0010/\u001a\u00020)J\u000e\u00100\u001a\u00020 2\u0006\u0010/\u001a\u00020)J\u000e\u00101\u001a\u00020 2\u0006\u00102\u001a\u00020)R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00063"}, d2 = {"Lcom/dzo/announcerclock/presentation/fragments/tts_fragment/viewmodel/TtsViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "Landroid/speech/tts/TextToSpeech$OnInitListener;", "application", "Landroid/app/Application;", "getTtsSettings", "Lcom/dzo/announcerclock/domain/tts_usecase/GetTtsSettingsUseCase;", "saveTtsSettings", "Lcom/dzo/announcerclock/domain/tts_usecase/SaveTtsSettingsUseCase;", "isTimeSpeakingEnabled", "Lcom/dzo/announcerclock/domain/tts_usecase/IsTimeSpeakingEnabledUseCase;", "saveTimeSpeakingEnabled", "Lcom/dzo/announcerclock/domain/tts_usecase/SaveTimeSpeakingEnabledUseCase;", "saveDisableDuringPhoneCalls", "Lcom/dzo/announcerclock/domain/tts_usecase/SaveDisableDuringPhoneCallsUseCase;", "isDisableDuringPhoneCalls", "Lcom/dzo/announcerclock/domain/tts_usecase/IsDisableDuringPhoneCallsUseCase;", "saveDisableWhilePlayingMusic", "Lcom/dzo/announcerclock/domain/tts_usecase/SaveDisableWhilePlayingMusicUseCase;", "isDisableWhilePlayingMusic", "Lcom/dzo/announcerclock/domain/tts_usecase/IsDisableWhilePlayingMusicUseCase;", "(Landroid/app/Application;Lcom/dzo/announcerclock/domain/tts_usecase/GetTtsSettingsUseCase;Lcom/dzo/announcerclock/domain/tts_usecase/SaveTtsSettingsUseCase;Lcom/dzo/announcerclock/domain/tts_usecase/IsTimeSpeakingEnabledUseCase;Lcom/dzo/announcerclock/domain/tts_usecase/SaveTimeSpeakingEnabledUseCase;Lcom/dzo/announcerclock/domain/tts_usecase/SaveDisableDuringPhoneCallsUseCase;Lcom/dzo/announcerclock/domain/tts_usecase/IsDisableDuringPhoneCallsUseCase;Lcom/dzo/announcerclock/domain/tts_usecase/SaveDisableWhilePlayingMusicUseCase;Lcom/dzo/announcerclock/domain/tts_usecase/IsDisableWhilePlayingMusicUseCase;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/dzo/announcerclock/presentation/fragments/tts_fragment/viewmodel/TtsUiState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "tts", "Landroid/speech/tts/TextToSpeech;", "loadLanguages", "", "onCleared", "onInit", "status", "", "selectLanguage", "locale", "Ljava/util/Locale;", "init", "", "selectVoice", "voice", "Landroid/speech/tts/Voice;", "speakCurrentTime", "toggleDisableDuringPhoneCalls", "disable", "toggleDisableWhilePlayingMusic", "toggleTimeSpeaking", "enabled", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class TtsViewModel extends androidx.lifecycle.AndroidViewModel implements android.speech.tts.TextToSpeech.OnInitListener {
    @org.jetbrains.annotations.NotNull()
    private final com.dzo.announcerclock.domain.tts_usecase.GetTtsSettingsUseCase getTtsSettings = null;
    @org.jetbrains.annotations.NotNull()
    private final com.dzo.announcerclock.domain.tts_usecase.SaveTtsSettingsUseCase saveTtsSettings = null;
    @org.jetbrains.annotations.NotNull()
    private final com.dzo.announcerclock.domain.tts_usecase.IsTimeSpeakingEnabledUseCase isTimeSpeakingEnabled = null;
    @org.jetbrains.annotations.NotNull()
    private final com.dzo.announcerclock.domain.tts_usecase.SaveTimeSpeakingEnabledUseCase saveTimeSpeakingEnabled = null;
    @org.jetbrains.annotations.NotNull()
    private final com.dzo.announcerclock.domain.tts_usecase.SaveDisableDuringPhoneCallsUseCase saveDisableDuringPhoneCalls = null;
    @org.jetbrains.annotations.NotNull()
    private final com.dzo.announcerclock.domain.tts_usecase.IsDisableDuringPhoneCallsUseCase isDisableDuringPhoneCalls = null;
    @org.jetbrains.annotations.NotNull()
    private final com.dzo.announcerclock.domain.tts_usecase.SaveDisableWhilePlayingMusicUseCase saveDisableWhilePlayingMusic = null;
    @org.jetbrains.annotations.NotNull()
    private final com.dzo.announcerclock.domain.tts_usecase.IsDisableWhilePlayingMusicUseCase isDisableWhilePlayingMusic = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.dzo.announcerclock.presentation.fragments.tts_fragment.viewmodel.TtsUiState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.dzo.announcerclock.presentation.fragments.tts_fragment.viewmodel.TtsUiState> state = null;
    @org.jetbrains.annotations.Nullable()
    private android.speech.tts.TextToSpeech tts;
    
    @javax.inject.Inject()
    public TtsViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application, @org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.domain.tts_usecase.GetTtsSettingsUseCase getTtsSettings, @org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.domain.tts_usecase.SaveTtsSettingsUseCase saveTtsSettings, @org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.domain.tts_usecase.IsTimeSpeakingEnabledUseCase isTimeSpeakingEnabled, @org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.domain.tts_usecase.SaveTimeSpeakingEnabledUseCase saveTimeSpeakingEnabled, @org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.domain.tts_usecase.SaveDisableDuringPhoneCallsUseCase saveDisableDuringPhoneCalls, @org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.domain.tts_usecase.IsDisableDuringPhoneCallsUseCase isDisableDuringPhoneCalls, @org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.domain.tts_usecase.SaveDisableWhilePlayingMusicUseCase saveDisableWhilePlayingMusic, @org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.domain.tts_usecase.IsDisableWhilePlayingMusicUseCase isDisableWhilePlayingMusic) {
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
    
    public final void toggleDisableDuringPhoneCalls(boolean disable) {
    }
    
    public final void toggleDisableWhilePlayingMusic(boolean disable) {
    }
    
    public final void speakCurrentTime() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}