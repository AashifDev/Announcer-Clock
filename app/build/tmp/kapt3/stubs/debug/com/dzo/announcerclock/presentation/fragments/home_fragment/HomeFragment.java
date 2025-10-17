package com.dzo.announcerclock.presentation.fragments.home_fragment;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00aa\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010.\u001a\u00020/H\u0002J\b\u00100\u001a\u00020/H\u0002J\b\u00101\u001a\u00020/H\u0002J\u0018\u00102\u001a\u00020/2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000206H\u0016J\b\u00107\u001a\u00020/H\u0016J\b\u00108\u001a\u00020/H\u0016J\u0010\u00109\u001a\u00020/2\u0006\u0010:\u001a\u00020;H\u0016J\u0010\u0010<\u001a\u00020$2\u0006\u0010=\u001a\u00020>H\u0016J\b\u0010?\u001a\u00020/H\u0016J\u001a\u0010@\u001a\u00020/2\u0006\u0010A\u001a\u00020B2\b\u0010C\u001a\u0004\u0018\u00010DH\u0016J\u0018\u0010E\u001a\u00020;2\u0006\u0010F\u001a\u00020;2\u0006\u0010G\u001a\u00020;H\u0002J\b\u0010H\u001a\u00020/H\u0002J\u000e\u0010I\u001a\u00020/2\u0006\u0010J\u001a\u00020KJ\u0018\u0010L\u001a\u00020;2\u0006\u0010M\u001a\u00020;2\u0006\u0010N\u001a\u00020;H\u0002J\u000e\u0010O\u001a\u00020/H\u0082@\u00a2\u0006\u0002\u0010PJ\b\u0010Q\u001a\u00020/H\u0002J\u000e\u0010R\u001a\u00020/2\u0006\u0010S\u001a\u00020TJ\b\u0010U\u001a\u00020/H\u0002J\b\u0010V\u001a\u00020/H\u0002R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001b\u0010\u0014\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001c\u001a\u00020\u001d8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b \u0010\u0014\u001a\u0004\b\u001e\u0010\u001fR\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\'\u001a\u00020(8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b+\u0010\u0014\u001a\u0004\b)\u0010*R\u000e\u0010,\u001a\u00020-X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006W"}, d2 = {"Lcom/dzo/announcerclock/presentation/fragments/home_fragment/HomeFragment;", "Lcom/dzo/announcerclock/utils/core/BaseFragment;", "Lcom/dzo/announcerclock/databinding/FragmentHomeBinding;", "Landroid/speech/tts/TextToSpeech$OnInitListener;", "()V", "announceTimeUseCase", "Lcom/dzo/announcerclock/domain/timer_usecase/AnnounceTimeUseCase;", "getAnnounceTimeUseCase", "()Lcom/dzo/announcerclock/domain/timer_usecase/AnnounceTimeUseCase;", "setAnnounceTimeUseCase", "(Lcom/dzo/announcerclock/domain/timer_usecase/AnnounceTimeUseCase;)V", "audioManager", "Landroid/media/AudioManager;", "repeatOption", "Lcom/dzo/announcerclock/presentation/fragments/repeat_option/model/RepeatOption;", "repeatOptionViewModel", "Lcom/dzo/announcerclock/presentation/fragments/repeat_option/viewmodel/RepeatOptionViewModel;", "getRepeatOptionViewModel", "()Lcom/dzo/announcerclock/presentation/fragments/repeat_option/viewmodel/RepeatOptionViewModel;", "repeatOptionViewModel$delegate", "Lkotlin/Lazy;", "soundOption", "Lcom/dzo/announcerclock/presentation/fragments/sound_fragment/model/SoundOption;", "soundOptionViewModel", "Lcom/dzo/announcerclock/presentation/fragments/sound_fragment/viewmodel/SoundOptionViewModel;", "getSoundOptionViewModel", "()Lcom/dzo/announcerclock/presentation/fragments/sound_fragment/viewmodel/SoundOptionViewModel;", "soundOptionViewModel$delegate", "timerViewModel", "Lcom/dzo/announcerclock/presentation/fragments/home_fragment/viewmodel/TimerViewModel;", "getTimerViewModel", "()Lcom/dzo/announcerclock/presentation/fragments/home_fragment/viewmodel/TimerViewModel;", "timerViewModel$delegate", "tts", "Landroid/speech/tts/TextToSpeech;", "ttsReady", "", "ttsSettings", "Lcom/dzo/announcerclock/presentation/fragments/home_fragment/model/TtsSettings;", "ttsViewModel", "Lcom/dzo/announcerclock/presentation/fragments/tts_fragment/viewmodel/TtsViewModel;", "getTtsViewModel", "()Lcom/dzo/announcerclock/presentation/fragments/tts_fragment/viewmodel/TtsViewModel;", "ttsViewModel$delegate", "volumeReceiver", "Landroid/content/BroadcastReceiver;", "clickListeners", "", "observeTtsState", "observeViewModel", "onCreateMenu", "menu", "Landroid/view/Menu;", "menuInflater", "Landroid/view/MenuInflater;", "onDestroy", "onDestroyView", "onInit", "status", "", "onMenuItemSelected", "menuItem", "Landroid/view/MenuItem;", "onStart", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "percentToDeviceVolume", "percent", "maxVol", "setupUIAfterPrefsLoaded", "showBottomSheet", "context", "Landroid/content/Context;", "snapToStep", "value", "step", "speakTestMessage", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncSliderWithSystemVolume", "updateAnimatedTimerText", "it", "", "updateSlider", "volumeRockerSetup", "app_debug"})
public final class HomeFragment extends com.dzo.announcerclock.utils.core.BaseFragment<com.dzo.announcerclock.databinding.FragmentHomeBinding> implements android.speech.tts.TextToSpeech.OnInitListener {
    @javax.inject.Inject()
    public com.dzo.announcerclock.domain.timer_usecase.AnnounceTimeUseCase announceTimeUseCase;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy ttsViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy repeatOptionViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy soundOptionViewModel$delegate = null;
    private android.media.AudioManager audioManager;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy timerViewModel$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private android.speech.tts.TextToSpeech tts;
    @org.jetbrains.annotations.Nullable()
    private com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption repeatOption;
    @org.jetbrains.annotations.Nullable()
    private com.dzo.announcerclock.presentation.fragments.sound_fragment.model.SoundOption soundOption;
    private boolean ttsReady = false;
    private com.dzo.announcerclock.presentation.fragments.home_fragment.model.TtsSettings ttsSettings;
    @org.jetbrains.annotations.NotNull()
    private final android.content.BroadcastReceiver volumeReceiver = null;
    
    public HomeFragment() {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.dzo.announcerclock.domain.timer_usecase.AnnounceTimeUseCase getAnnounceTimeUseCase() {
        return null;
    }
    
    public final void setAnnounceTimeUseCase(@org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.domain.timer_usecase.AnnounceTimeUseCase p0) {
    }
    
    private final com.dzo.announcerclock.presentation.fragments.tts_fragment.viewmodel.TtsViewModel getTtsViewModel() {
        return null;
    }
    
    private final com.dzo.announcerclock.presentation.fragments.repeat_option.viewmodel.RepeatOptionViewModel getRepeatOptionViewModel() {
        return null;
    }
    
    private final com.dzo.announcerclock.presentation.fragments.sound_fragment.viewmodel.SoundOptionViewModel getSoundOptionViewModel() {
        return null;
    }
    
    private final com.dzo.announcerclock.presentation.fragments.home_fragment.viewmodel.TimerViewModel getTimerViewModel() {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void volumeRockerSetup() {
    }
    
    private final void clickListeners() {
    }
    
    private final void setupUIAfterPrefsLoaded() {
    }
    
    @java.lang.Override()
    public void onCreateMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu, @org.jetbrains.annotations.NotNull()
    android.view.MenuInflater menuInflater) {
    }
    
    @java.lang.Override()
    public boolean onMenuItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem menuItem) {
        return false;
    }
    
    private final java.lang.Object speakTestMessage(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final void observeTtsState() {
    }
    
    private final void observeViewModel() {
    }
    
    public final void showBottomSheet(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void updateAnimatedTimerText(@org.jetbrains.annotations.NotNull()
    java.lang.String it) {
    }
    
    @java.lang.Override()
    public void onStart() {
    }
    
    private final void updateSlider() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    private final int percentToDeviceVolume(int percent, int maxVol) {
        return 0;
    }
    
    private final int snapToStep(int value, int step) {
        return 0;
    }
    
    private final void syncSliderWithSystemVolume() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public void onInit(int status) {
    }
}