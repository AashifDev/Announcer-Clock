package com.dzo.announcerclock.presentation.fragments.home_fragment.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u0016\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!J\u0018\u0010#\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020!2\u0006\u0010%\u001a\u00020!H\u0002J\u000e\u0010&\u001a\u00020\u001e2\u0006\u0010\'\u001a\u00020!J\u0010\u0010(\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020!H\u0002J\u0006\u0010)\u001a\u00020\u001eR\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\t0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lcom/dzo/announcerclock/presentation/fragments/home_fragment/viewmodel/TimerViewModel;", "Landroidx/lifecycle/ViewModel;", "announceTimeUseCase", "Lcom/dzo/announcerclock/domain/timer_usecase/AnnounceTimeUseCase;", "context", "Landroid/content/Context;", "(Lcom/dzo/announcerclock/domain/timer_usecase/AnnounceTimeUseCase;Landroid/content/Context;)V", "_announcement", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_progress", "", "_timeText", "announcement", "Lkotlinx/coroutines/flow/StateFlow;", "getAnnouncement", "()Lkotlinx/coroutines/flow/StateFlow;", "connection", "Landroid/content/ServiceConnection;", "isBound", "", "progress", "getProgress", "timeText", "getTimeText", "timerJob", "Lkotlinx/coroutines/Job;", "timerService", "Lcom/dzo/announcerclock/data/service/TimerService;", "observeServiceFlows", "", "startCustomTimer", "intervalMinute", "", "totalRunMinute", "startCustomTimerService", "totalMillis", "totalRunMillis", "startTimer", "intervalSeconds", "startTimerService", "stopTimer", "app_release"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class TimerViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.dzo.announcerclock.domain.timer_usecase.AnnounceTimeUseCase announceTimeUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _announcement = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> announcement = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _progress = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> progress = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _timeText = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> timeText = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job timerJob;
    @org.jetbrains.annotations.Nullable()
    private com.dzo.announcerclock.data.service.TimerService timerService;
    private boolean isBound = false;
    @org.jetbrains.annotations.NotNull()
    private final android.content.ServiceConnection connection = null;
    
    @javax.inject.Inject()
    public TimerViewModel(@org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.domain.timer_usecase.AnnounceTimeUseCase announceTimeUseCase, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getAnnouncement() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getProgress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getTimeText() {
        return null;
    }
    
    public final void startTimer(long intervalSeconds) {
    }
    
    public final void startCustomTimer(long intervalMinute, long totalRunMinute) {
    }
    
    private final void startTimerService(long totalMillis) {
    }
    
    private final void startCustomTimerService(long totalMillis, long totalRunMillis) {
    }
    
    private final void observeServiceFlows() {
    }
    
    public final void stopTimer() {
    }
}