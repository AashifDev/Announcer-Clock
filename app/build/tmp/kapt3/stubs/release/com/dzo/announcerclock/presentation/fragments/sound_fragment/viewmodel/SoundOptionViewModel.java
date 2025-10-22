package com.dzo.announcerclock.presentation.fragments.sound_fragment.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0012H\u0014J\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\nR\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001a"}, d2 = {"Lcom/dzo/announcerclock/presentation/fragments/sound_fragment/viewmodel/SoundOptionViewModel;", "Landroidx/lifecycle/ViewModel;", "getSoundOptionsUseCase", "Lcom/dzo/announcerclock/domain/sound_option_usecase/GetSoundOptionsUseCase;", "context", "Landroid/content/Context;", "(Lcom/dzo/announcerclock/domain/sound_option_usecase/GetSoundOptionsUseCase;Landroid/content/Context;)V", "_soundOptions", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/dzo/announcerclock/presentation/fragments/sound_fragment/model/SoundOption;", "mediaPlayer", "Landroid/media/MediaPlayer;", "soundOptions", "Lkotlinx/coroutines/flow/StateFlow;", "getSoundOptions", "()Lkotlinx/coroutines/flow/StateFlow;", "loadSoundOptions", "", "onCleared", "playSound", "resId", "", "selectOption", "Lkotlinx/coroutines/Job;", "selectedItem", "app_release"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SoundOptionViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.dzo.announcerclock.domain.sound_option_usecase.GetSoundOptionsUseCase getSoundOptionsUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.dzo.announcerclock.presentation.fragments.sound_fragment.model.SoundOption>> _soundOptions = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.dzo.announcerclock.presentation.fragments.sound_fragment.model.SoundOption>> soundOptions = null;
    @org.jetbrains.annotations.Nullable()
    private android.media.MediaPlayer mediaPlayer;
    
    @javax.inject.Inject()
    public SoundOptionViewModel(@org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.domain.sound_option_usecase.GetSoundOptionsUseCase getSoundOptionsUseCase, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.dzo.announcerclock.presentation.fragments.sound_fragment.model.SoundOption>> getSoundOptions() {
        return null;
    }
    
    private final void loadSoundOptions() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job selectOption(@org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.presentation.fragments.sound_fragment.model.SoundOption selectedItem) {
        return null;
    }
    
    private final void playSound(int resId) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}