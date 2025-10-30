package com.dzo.announcerclock.presentation.fragments.home_fragment;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0086\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010N\u001a\u00020OH\u0002J\b\u0010P\u001a\u00020OH\u0002J\b\u0010Q\u001a\u00020OH\u0002J\b\u0010R\u001a\u00020OH\u0002J\u0012\u0010S\u001a\u00020\u00152\b\u0010;\u001a\u0004\u0018\u00010\u0017H\u0002J\b\u0010T\u001a\u00020OH\u0002J\u0010\u0010U\u001a\u00020\u00152\u0006\u0010V\u001a\u00020\u0019H\u0002J\b\u0010W\u001a\u00020OH\u0002J\b\u0010X\u001a\u00020OH\u0002J\u0018\u0010Y\u001a\u00020O2\u0006\u0010Z\u001a\u00020[2\u0006\u0010\\\u001a\u00020]H\u0016J\b\u0010^\u001a\u00020OH\u0016J\b\u0010_\u001a\u00020OH\u0016J\b\u0010`\u001a\u00020OH\u0016J\u0010\u0010a\u001a\u00020E2\u0006\u0010b\u001a\u00020cH\u0016J\b\u0010d\u001a\u00020OH\u0016J\b\u0010e\u001a\u00020OH\u0016J\u001a\u0010f\u001a\u00020O2\u0006\u0010g\u001a\u00020h2\b\u0010i\u001a\u0004\u0018\u00010jH\u0016J\u0018\u0010k\u001a\u00020\u00062\u0006\u0010l\u001a\u00020\u00062\u0006\u0010m\u001a\u00020\u0006H\u0002J\b\u0010n\u001a\u00020OH\u0002J\u0010\u0010o\u001a\u00020O2\u0006\u0010p\u001a\u00020qH\u0002J\u0010\u0010r\u001a\u00020O2\u0006\u0010s\u001a\u00020EH\u0002J\b\u0010t\u001a\u00020OH\u0002J\b\u0010u\u001a\u00020OH\u0002J\b\u0010v\u001a\u00020OH\u0002J\u000e\u0010w\u001a\u00020O2\u0006\u0010x\u001a\u00020yJ\u001c\u0010z\u001a\u00020O2\b\u0010{\u001a\u0004\u0018\u00010|2\b\u0010}\u001a\u0004\u0018\u00010|H\u0002J\b\u0010~\u001a\u00020OH\u0002J\u0012\u0010\u007f\u001a\u00020O2\b\u0010}\u001a\u0004\u0018\u00010|H\u0002J,\u0010\u0080\u0001\u001a\u00020O2\b\u0010\u0081\u0001\u001a\u00030\u0082\u00012\u000e\u0010\u0083\u0001\u001a\t\u0012\u0004\u0012\u00020h0\u0084\u00012\u0007\u0010\u0085\u0001\u001a\u00020\u0006H\u0002J\u000f\u0010\u0086\u0001\u001a\u00020O2\u0006\u0010\u001b\u001a\u00020\u0003J\u0014\u0010\u0087\u0001\u001a\u00020O2\t\u0010\u0088\u0001\u001a\u0004\u0018\u00010|H\u0002J\u001b\u0010\u0089\u0001\u001a\u00020\u00062\u0007\u0010\u008a\u0001\u001a\u00020\u00062\u0007\u0010\u008b\u0001\u001a\u00020\u0006H\u0002J\u0010\u0010\u008c\u0001\u001a\u00020OH\u0082@\u00a2\u0006\u0003\u0010\u008d\u0001J\u001c\u0010\u008e\u0001\u001a\u00020O2\b\u0010\u008f\u0001\u001a\u00030\u0090\u00012\u0007\u0010\u0091\u0001\u001a\u00020\u0006H\u0002J\t\u0010\u0092\u0001\u001a\u00020OH\u0002J\t\u0010\u0093\u0001\u001a\u00020OH\u0002J\t\u0010\u0094\u0001\u001a\u00020OH\u0002J\t\u0010\u0095\u0001\u001a\u00020OH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u001aR\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u001aR\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010 \u001a\u00020!8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b\"\u0010#R\u001b\u0010&\u001a\u00020\'8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b*\u0010%\u001a\u0004\b(\u0010)R\u0010\u0010+\u001a\u0004\u0018\u00010,X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010-\u001a\u00020.8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b1\u0010%\u001a\u0004\b/\u00100R\u000e\u00102\u001a\u000203X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u000105X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u00106\u001a\u0002078BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b:\u0010%\u001a\u0004\b8\u00109R\u0010\u0010;\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010<\u001a\u0004\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u001aR\u001b\u0010=\u001a\u00020>8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bA\u0010%\u001a\u0004\b?\u0010@R\u0010\u0010B\u001a\u0004\u0018\u00010CX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020EX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020GX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010H\u001a\u00020I8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bL\u0010%\u001a\u0004\bJ\u0010KR\u000e\u0010M\u001a\u000203X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0096\u0001"}, d2 = {"Lcom/dzo/announcerclock/presentation/fragments/home_fragment/HomeFragment;", "Lcom/dzo/announcerclock/utils/core/BaseFragment;", "Lcom/dzo/announcerclock/databinding/FragmentHomeBinding;", "Lcom/dzo/announcerclock/presentation/fragments/home_fragment/OnBottomSheetResultListener;", "()V", "DAYS_FOR_FLEXIBLE_UPDATE", "", "DAYS_FOR_IMMEDIATE_UPDATE", "activityResultLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "announceTimeUseCase", "Lcom/dzo/announcerclock/domain/timer_usecase/AnnounceTimeUseCase;", "getAnnounceTimeUseCase", "()Lcom/dzo/announcerclock/domain/timer_usecase/AnnounceTimeUseCase;", "setAnnounceTimeUseCase", "(Lcom/dzo/announcerclock/domain/timer_usecase/AnnounceTimeUseCase;)V", "appUpdateManager", "Lcom/google/android/play/core/appupdate/AppUpdateManager;", "audioManager", "Landroid/media/AudioManager;", "colorHexx", "", "endCal", "Ljava/util/Calendar;", "endTime", "", "Ljava/lang/Long;", "listener", "Lcom/google/android/play/core/install/InstallStateUpdatedListener;", "repeatEvery", "repeatOption", "Lcom/dzo/announcerclock/presentation/fragments/repeat_option/model/RepeatOption;", "repeatOptionViewModel", "Lcom/dzo/announcerclock/presentation/fragments/repeat_option/viewmodel/RepeatOptionViewModel;", "getRepeatOptionViewModel", "()Lcom/dzo/announcerclock/presentation/fragments/repeat_option/viewmodel/RepeatOptionViewModel;", "repeatOptionViewModel$delegate", "Lkotlin/Lazy;", "rippleTypedValue", "Landroid/util/TypedValue;", "getRippleTypedValue", "()Landroid/util/TypedValue;", "rippleTypedValue$delegate", "schTime", "Lcom/dzo/announcerclock/presentation/fragments/home_fragment/model/ScheduleTimerModel;", "scheduleTimerModel", "Lcom/dzo/announcerclock/presentation/fragments/home_fragment/viewmodel/ScheduleTimerViewModel;", "getScheduleTimerModel", "()Lcom/dzo/announcerclock/presentation/fragments/home_fragment/viewmodel/ScheduleTimerViewModel;", "scheduleTimerModel$delegate", "serviceStatusReceiver", "Landroid/content/BroadcastReceiver;", "soundOption", "Lcom/dzo/announcerclock/presentation/fragments/sound_fragment/model/SoundOption;", "soundOptionViewModel", "Lcom/dzo/announcerclock/presentation/fragments/sound_fragment/viewmodel/SoundOptionViewModel;", "getSoundOptionViewModel", "()Lcom/dzo/announcerclock/presentation/fragments/sound_fragment/viewmodel/SoundOptionViewModel;", "soundOptionViewModel$delegate", "startCal", "startTime", "timerViewModel", "Lcom/dzo/announcerclock/presentation/fragments/home_fragment/viewmodel/TimerViewModel;", "getTimerViewModel", "()Lcom/dzo/announcerclock/presentation/fragments/home_fragment/viewmodel/TimerViewModel;", "timerViewModel$delegate", "tts", "Landroid/speech/tts/TextToSpeech;", "ttsReady", "", "ttsSettings", "Lcom/dzo/announcerclock/presentation/fragments/home_fragment/model/TtsSettings;", "ttsViewModel", "Lcom/dzo/announcerclock/presentation/fragments/tts_fragment/viewmodel/TtsViewModel;", "getTtsViewModel", "()Lcom/dzo/announcerclock/presentation/fragments/tts_fragment/viewmodel/TtsViewModel;", "ttsViewModel$delegate", "volumeReceiver", "applyDynamicColor", "", "checkForUpdate", "clickListeners", "customSchedulingRipple", "formattedTime", "headerCardRipple", "milliSecondToTime", "timeInMillis", "observeTtsState", "observeViewModel", "onCreateMenu", "menu", "Landroid/view/Menu;", "menuInflater", "Landroid/view/MenuInflater;", "onDataUpdated", "onDestroy", "onDestroyView", "onMenuItemSelected", "menuItem", "Landroid/view/MenuItem;", "onResume", "onStart", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "percentToDeviceVolume", "percent", "maxVol", "popupSnackBarForCompleteUpdate", "removeNumberPickerDividers", "numberPicker", "Landroid/widget/NumberPicker;", "setThemeMode", "isDark", "setUiThemeMode", "setupUIAfterPrefsLoaded", "showAnnouncerDialog", "showBottomSheet", "context", "Landroid/content/Context;", "showEndTimePicker", "txtSetEndTime", "Landroidx/appcompat/widget/AppCompatTextView;", "txtSetRepeatEveryMinute", "showIntroGuide", "showMinutePickerDialog", "showNextTarget", "scrollView", "Landroid/widget/ScrollView;", "targets", "", "index", "showScheduleTimerBottomSheet", "showStartTimePicker", "txtSetStartTime", "snapToStep", "value", "step", "speakTestMessage", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startInAppUpdate", "appUpdateInfo", "Lcom/google/android/play/core/appupdate/AppUpdateInfo;", "updateType", "syncSliderWithSystemVolume", "updateSlider", "updateUiWithSessionData", "volumeRockerSetup", "app_debug"})
public final class HomeFragment extends com.dzo.announcerclock.utils.core.BaseFragment<com.dzo.announcerclock.databinding.FragmentHomeBinding> implements com.dzo.announcerclock.presentation.fragments.home_fragment.OnBottomSheetResultListener {
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
    
    public final void showScheduleTimerBottomSheet(@org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.presentation.fragments.home_fragment.OnBottomSheetResultListener listener) {
    }
    
    private final void showStartTimePicker(androidx.appcompat.widget.AppCompatTextView txtSetStartTime) {
    }
    
    private final void showEndTimePicker(androidx.appcompat.widget.AppCompatTextView txtSetEndTime, androidx.appcompat.widget.AppCompatTextView txtSetRepeatEveryMinute) {
    }
    
    private final java.lang.String milliSecondToTime(long timeInMillis) {
        return null;
    }
    
    private final java.lang.String formattedTime(java.util.Calendar startCal) {
        return null;
    }
    
    private final void showMinutePickerDialog(androidx.appcompat.widget.AppCompatTextView txtSetRepeatEveryMinute) {
    }
    
    private final void removeNumberPickerDividers(android.widget.NumberPicker numberPicker) {
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
    
    @java.lang.Override()
    public void onDataUpdated() {
    }
    
    private final void updateUiWithSessionData() {
    }
}