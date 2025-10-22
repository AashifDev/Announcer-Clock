package com.dzo.announcerclock.data.service;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002:\u0001EB\u0005\u00a2\u0006\u0002\u0010\u0003J\u000e\u0010(\u001a\u00020)H\u0082@\u00a2\u0006\u0002\u0010*J\b\u0010+\u001a\u00020)H\u0002J\u0012\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\b\u00100\u001a\u00020)H\u0016J\b\u00101\u001a\u00020)H\u0016J\u0010\u00102\u001a\u00020)2\u0006\u00103\u001a\u00020\u0006H\u0016J\"\u00104\u001a\u00020\u00062\b\u0010.\u001a\u0004\u0018\u00010/2\u0006\u00105\u001a\u00020\u00062\u0006\u00106\u001a\u00020\u0006H\u0016J\u0012\u00107\u001a\u00020)2\b\u00108\u001a\u0004\u0018\u00010/H\u0016J\b\u00109\u001a\u00020)H\u0002J\b\u0010:\u001a\u00020)H\u0002J\b\u0010;\u001a\u00020)H\u0002J\b\u0010<\u001a\u00020)H\u0002J\u000e\u0010=\u001a\u00020)H\u0082@\u00a2\u0006\u0002\u0010*J\u0018\u0010>\u001a\u00020)2\u0006\u0010?\u001a\u00020\u00122\u0006\u0010@\u001a\u00020\u0012H\u0002J\b\u0010A\u001a\u00020)H\u0002J\u0010\u0010B\u001a\u00020)2\u0006\u0010C\u001a\u00020\u0012H\u0002J\u0006\u0010D\u001a\u00020)R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\b0\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001cR\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\'\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006F"}, d2 = {"Lcom/dzo/announcerclock/data/service/TimerService;", "Landroid/app/Service;", "Landroid/speech/tts/TextToSpeech$OnInitListener;", "()V", "_progressFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_timeFlow", "", "announceTimeUseCase", "Lcom/dzo/announcerclock/domain/timer_usecase/AnnounceTimeUseCase;", "getAnnounceTimeUseCase", "()Lcom/dzo/announcerclock/domain/timer_usecase/AnnounceTimeUseCase;", "setAnnounceTimeUseCase", "(Lcom/dzo/announcerclock/domain/timer_usecase/AnnounceTimeUseCase;)V", "audioListener", "Lcom/dzo/announcerclock/utils/AudioPlaybackListener;", "elapsedTime", "", "elapsedTimeCustom", "isRunning", "", "lastAnnounceTime", "phoneListener", "Lcom/dzo/announcerclock/utils/PhoneCallListener;", "progressFlow", "Lkotlinx/coroutines/flow/StateFlow;", "getProgressFlow", "()Lkotlinx/coroutines/flow/StateFlow;", "startTime", "timeFlow", "getTimeFlow", "timerJob", "Lkotlinx/coroutines/Job;", "totalDurationCustom", "totalTime", "tts", "Landroid/speech/tts/TextToSpeech;", "ttsReady", "wasPausedByAudio", "announceTime", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createForegroundNotificationChannel", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onInit", "status", "onStartCommand", "flags", "startId", "onTaskRemoved", "rootIntent", "pauseServiceForCall", "pauseTts", "resumeServiceAfterCall", "resumeTts", "showCompletionNotification", "startCustomTimer", "totalRunMillis", "howLongMillis", "startForegroundServiceNotification", "startTimer", "totalMillis", "stopServiceManually", "TimerBinder", "app_release"})
public final class TimerService extends android.app.Service implements android.speech.tts.TextToSpeech.OnInitListener {
    @javax.inject.Inject()
    public com.dzo.announcerclock.domain.timer_usecase.AnnounceTimeUseCase announceTimeUseCase;
    @org.jetbrains.annotations.Nullable()
    private android.speech.tts.TextToSpeech tts;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job timerJob;
    private boolean isRunning = false;
    private boolean ttsReady = false;
    @org.jetbrains.annotations.Nullable()
    private com.dzo.announcerclock.utils.PhoneCallListener phoneListener;
    @org.jetbrains.annotations.Nullable()
    private com.dzo.announcerclock.utils.AudioPlaybackListener audioListener;
    private boolean wasPausedByAudio = false;
    private long elapsedTime = 0L;
    private long totalTime = 0L;
    private long elapsedTimeCustom = 0L;
    private long totalDurationCustom = 0L;
    private long lastAnnounceTime = 0L;
    private long startTime = 0L;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _progressFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> progressFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _timeFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> timeFlow = null;
    
    public TimerService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.dzo.announcerclock.domain.timer_usecase.AnnounceTimeUseCase getAnnounceTimeUseCase() {
        return null;
    }
    
    public final void setAnnounceTimeUseCase(@org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.domain.timer_usecase.AnnounceTimeUseCase p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getProgressFlow() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getTimeFlow() {
        return null;
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    private final void pauseServiceForCall() {
    }
    
    private final void resumeServiceAfterCall() {
    }
    
    private final void pauseTts() {
    }
    
    private final void resumeTts() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
        return null;
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    @java.lang.Override()
    public void onTaskRemoved(@org.jetbrains.annotations.Nullable()
    android.content.Intent rootIntent) {
    }
    
    private final void createForegroundNotificationChannel() {
    }
    
    private final void startForegroundServiceNotification() {
    }
    
    private final void startTimer(long totalMillis) {
    }
    
    private final void startCustomTimer(long totalRunMillis, long howLongMillis) {
    }
    
    private final java.lang.Object announceTime(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object showCompletionNotification(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public void onInit(int status) {
    }
    
    public final void stopServiceManually() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/dzo/announcerclock/data/service/TimerService$TimerBinder;", "Landroid/os/Binder;", "(Lcom/dzo/announcerclock/data/service/TimerService;)V", "getService", "Lcom/dzo/announcerclock/data/service/TimerService;", "app_release"})
    public final class TimerBinder extends android.os.Binder {
        
        public TimerBinder() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.dzo.announcerclock.data.service.TimerService getService() {
            return null;
        }
    }
}