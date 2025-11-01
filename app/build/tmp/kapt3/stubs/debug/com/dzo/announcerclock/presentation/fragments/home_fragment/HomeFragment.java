package com.dzo.announcerclock.presentation.fragments.home_fragment;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00f2\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010M\u001a\u00020NH\u0002J\b\u0010O\u001a\u00020NH\u0002J\b\u0010P\u001a\u00020NH\u0002J\b\u0010Q\u001a\u00020NH\u0002J\b\u0010R\u001a\u00020NH\u0002J\b\u0010S\u001a\u00020NH\u0002J\b\u0010T\u001a\u00020NH\u0002J\u0018\u0010U\u001a\u00020N2\u0006\u0010V\u001a\u00020W2\u0006\u0010X\u001a\u00020YH\u0016J\b\u0010Z\u001a\u00020NH\u0016J\b\u0010[\u001a\u00020NH\u0016J\u0010\u0010\\\u001a\u00020D2\u0006\u0010]\u001a\u00020^H\u0016J\b\u0010_\u001a\u00020NH\u0016J\b\u0010`\u001a\u00020NH\u0016J\u001a\u0010a\u001a\u00020N2\u0006\u0010b\u001a\u00020c2\b\u0010d\u001a\u0004\u0018\u00010eH\u0016J\u0018\u0010f\u001a\u00020\u00052\u0006\u0010g\u001a\u00020\u00052\u0006\u0010h\u001a\u00020\u0005H\u0002J\b\u0010i\u001a\u00020NH\u0002J\u0010\u0010j\u001a\u00020N2\u0006\u0010k\u001a\u00020DH\u0002J\b\u0010l\u001a\u00020NH\u0002J\b\u0010m\u001a\u00020NH\u0002J\b\u0010n\u001a\u00020NH\u0002J\u000e\u0010o\u001a\u00020N2\u0006\u0010p\u001a\u00020qJ\b\u0010r\u001a\u00020NH\u0002J&\u0010s\u001a\u00020N2\u0006\u0010t\u001a\u00020u2\f\u0010v\u001a\b\u0012\u0004\u0012\u00020c0w2\u0006\u0010x\u001a\u00020\u0005H\u0002J\u0018\u0010y\u001a\u00020\u00052\u0006\u0010z\u001a\u00020\u00052\u0006\u0010{\u001a\u00020\u0005H\u0002J\u000e\u0010|\u001a\u00020NH\u0082@\u00a2\u0006\u0002\u0010}J\u001a\u0010~\u001a\u00020N2\u0007\u0010\u007f\u001a\u00030\u0080\u00012\u0007\u0010\u0081\u0001\u001a\u00020\u0005H\u0002J\t\u0010\u0082\u0001\u001a\u00020NH\u0002J\t\u0010\u0083\u0001\u001a\u00020NH\u0002J\t\u0010\u0084\u0001\u001a\u00020NH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0002\b\u0003\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0019R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0019R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u001f\u001a\u00020 8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b!\u0010\"R\u001b\u0010%\u001a\u00020&8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b)\u0010$\u001a\u0004\b\'\u0010(R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010,\u001a\u00020-8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b0\u0010$\u001a\u0004\b.\u0010/R\u000e\u00101\u001a\u000202X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u000104X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u00105\u001a\u0002068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b9\u0010$\u001a\u0004\b7\u00108R\u0010\u0010:\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010;\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0019R\u001b\u0010<\u001a\u00020=8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b@\u0010$\u001a\u0004\b>\u0010?R\u0010\u0010A\u001a\u0004\u0018\u00010BX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020DX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020FX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010G\u001a\u00020H8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bK\u0010$\u001a\u0004\bI\u0010JR\u000e\u0010L\u001a\u000202X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0085\u0001"}, d2 = {"Lcom/dzo/announcerclock/presentation/fragments/home_fragment/HomeFragment;", "Lcom/dzo/announcerclock/utils/core/BaseFragment;", "Lcom/dzo/announcerclock/databinding/FragmentHomeBinding;", "()V", "DAYS_FOR_FLEXIBLE_UPDATE", "", "DAYS_FOR_IMMEDIATE_UPDATE", "activityResultLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "announceTimeUseCase", "Lcom/dzo/announcerclock/domain/timer_usecase/AnnounceTimeUseCase;", "getAnnounceTimeUseCase", "()Lcom/dzo/announcerclock/domain/timer_usecase/AnnounceTimeUseCase;", "setAnnounceTimeUseCase", "(Lcom/dzo/announcerclock/domain/timer_usecase/AnnounceTimeUseCase;)V", "appUpdateManager", "Lcom/google/android/play/core/appupdate/AppUpdateManager;", "audioManager", "Landroid/media/AudioManager;", "colorHexx", "", "endCal", "Ljava/util/Calendar;", "endTime", "", "Ljava/lang/Long;", "listener", "Lcom/google/android/play/core/install/InstallStateUpdatedListener;", "repeatEvery", "repeatOption", "Lcom/dzo/announcerclock/presentation/fragments/repeat_option/model/RepeatOption;", "repeatOptionViewModel", "Lcom/dzo/announcerclock/presentation/fragments/repeat_option/viewmodel/RepeatOptionViewModel;", "getRepeatOptionViewModel", "()Lcom/dzo/announcerclock/presentation/fragments/repeat_option/viewmodel/RepeatOptionViewModel;", "repeatOptionViewModel$delegate", "Lkotlin/Lazy;", "rippleTypedValue", "Landroid/util/TypedValue;", "getRippleTypedValue", "()Landroid/util/TypedValue;", "rippleTypedValue$delegate", "schTime", "Lcom/dzo/announcerclock/presentation/fragments/home_fragment/model/ScheduleTimerModel;", "scheduleTimerModel", "Lcom/dzo/announcerclock/presentation/fragments/home_fragment/viewmodel/ScheduleTimerViewModel;", "getScheduleTimerModel", "()Lcom/dzo/announcerclock/presentation/fragments/home_fragment/viewmodel/ScheduleTimerViewModel;", "scheduleTimerModel$delegate", "serviceStatusReceiver", "Landroid/content/BroadcastReceiver;", "soundOption", "Lcom/dzo/announcerclock/presentation/fragments/sound_fragment/model/SoundOption;", "soundOptionViewModel", "Lcom/dzo/announcerclock/presentation/fragments/sound_fragment/viewmodel/SoundOptionViewModel;", "getSoundOptionViewModel", "()Lcom/dzo/announcerclock/presentation/fragments/sound_fragment/viewmodel/SoundOptionViewModel;", "soundOptionViewModel$delegate", "startCal", "startTime", "timerViewModel", "Lcom/dzo/announcerclock/presentation/fragments/home_fragment/viewmodel/TimerViewModel;", "getTimerViewModel", "()Lcom/dzo/announcerclock/presentation/fragments/home_fragment/viewmodel/TimerViewModel;", "timerViewModel$delegate", "tts", "Landroid/speech/tts/TextToSpeech;", "ttsReady", "", "ttsSettings", "Lcom/dzo/announcerclock/presentation/fragments/home_fragment/model/TtsSettings;", "ttsViewModel", "Lcom/dzo/announcerclock/presentation/fragments/tts_fragment/viewmodel/TtsViewModel;", "getTtsViewModel", "()Lcom/dzo/announcerclock/presentation/fragments/tts_fragment/viewmodel/TtsViewModel;", "ttsViewModel$delegate", "volumeReceiver", "applyDynamicColor", "", "checkForUpdate", "clickListeners", "customSchedulingRipple", "headerCardRipple", "observeTtsState", "observeViewModel", "onCreateMenu", "menu", "Landroid/view/Menu;", "menuInflater", "Landroid/view/MenuInflater;", "onDestroy", "onDestroyView", "onMenuItemSelected", "menuItem", "Landroid/view/MenuItem;", "onResume", "onStart", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "percentToDeviceVolume", "percent", "maxVol", "popupSnackBarForCompleteUpdate", "setThemeMode", "isDark", "setUiThemeMode", "setupUIAfterPrefsLoaded", "showAnnouncerDialog", "showBottomSheet", "context", "Landroid/content/Context;", "showIntroGuide", "showNextTarget", "scrollView", "Landroid/widget/ScrollView;", "targets", "", "index", "snapToStep", "value", "step", "speakTestMessage", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startInAppUpdate", "appUpdateInfo", "Lcom/google/android/play/core/appupdate/AppUpdateInfo;", "updateType", "syncSliderWithSystemVolume", "updateSlider", "volumeRockerSetup", "app_debug"})
public final class HomeFragment extends com.dzo.announcerclock.utils.core.BaseFragment<com.dzo.announcerclock.databinding.FragmentHomeBinding> {
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
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy scheduleTimerModel$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private android.speech.tts.TextToSpeech tts;
    @org.jetbrains.annotations.Nullable()
    private com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption repeatOption;
    @org.jetbrains.annotations.Nullable()
    private com.dzo.announcerclock.presentation.fragments.sound_fragment.model.SoundOption soundOption;
    private boolean ttsReady = false;
    private com.dzo.announcerclock.presentation.fragments.home_fragment.model.TtsSettings ttsSettings;
    @org.jetbrains.annotations.Nullable()
    private com.google.android.play.core.appupdate.AppUpdateManager appUpdateManager;
    @org.jetbrains.annotations.Nullable()
    private com.google.android.play.core.install.InstallStateUpdatedListener listener;
    @org.jetbrains.annotations.Nullable()
    private androidx.activity.result.ActivityResultLauncher<?> activityResultLauncher;
    private int DAYS_FOR_FLEXIBLE_UPDATE = 7;
    private int DAYS_FOR_IMMEDIATE_UPDATE = 14;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String colorHexx = "";
    @org.jetbrains.annotations.Nullable()
    private java.util.Calendar startCal;
    @org.jetbrains.annotations.Nullable()
    private java.util.Calendar endCal;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Long startTime;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Long endTime;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Long repeatEvery;
    @org.jetbrains.annotations.Nullable()
    private com.dzo.announcerclock.presentation.fragments.home_fragment.model.ScheduleTimerModel schTime;
    @org.jetbrains.annotations.NotNull()
    private final android.content.BroadcastReceiver volumeReceiver = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy rippleTypedValue$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.BroadcastReceiver serviceStatusReceiver = null;
    
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
    
    private final com.dzo.announcerclock.presentation.fragments.home_fragment.viewmodel.ScheduleTimerViewModel getScheduleTimerModel() {
        return null;
    }
    
    private final android.util.TypedValue getRippleTypedValue() {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void applyDynamicColor() {
    }
    
    private final void checkForUpdate() {
    }
    
    private final void startInAppUpdate(com.google.android.play.core.appupdate.AppUpdateInfo appUpdateInfo, int updateType) {
    }
    
    private final void popupSnackBarForCompleteUpdate() {
    }
    
    private final void volumeRockerSetup() {
    }
    
    private final void clickListeners() {
    }
    
    private final void setupUIAfterPrefsLoaded() {
    }
    
    private final void headerCardRipple() {
    }
    
    private final void customSchedulingRipple() {
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
    
    private final void showIntroGuide() {
    }
    
    private final void showNextTarget(android.widget.ScrollView scrollView, java.util.List<? extends android.view.View> targets, int index) {
    }
    
    private final java.lang.Object speakTestMessage(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final void observeTtsState() {
    }
    
    private final void observeViewModel() {
    }
    
    private final void showAnnouncerDialog() {
    }
    
    public final void showBottomSheet(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @java.lang.Override()
    public void onStart() {
    }
    
    private final void setUiThemeMode() {
    }
    
    private final void setThemeMode(boolean isDark) {
    }
    
    @java.lang.Override()
    public void onResume() {
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
}