package com.dzo.announcerclock.presentation.fragments.sound_fragment;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0002J\u001a\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0013H\u0002J\b\u0010\u001b\u001a\u00020\u0013H\u0002J\b\u0010\u001c\u001a\u00020\u0013H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001d"}, d2 = {"Lcom/dzo/announcerclock/presentation/fragments/sound_fragment/NotificationSoundFragment;", "Lcom/dzo/announcerclock/utils/core/BaseFragment;", "Lcom/dzo/announcerclock/databinding/FragmentNotificationSoundBinding;", "()V", "colorHex", "", "soundOptionAdapter", "Lcom/dzo/announcerclock/presentation/fragments/sound_fragment/adapter/SoundOptionAdapter;", "getSoundOptionAdapter", "()Lcom/dzo/announcerclock/presentation/fragments/sound_fragment/adapter/SoundOptionAdapter;", "setSoundOptionAdapter", "(Lcom/dzo/announcerclock/presentation/fragments/sound_fragment/adapter/SoundOptionAdapter;)V", "viewModel", "Lcom/dzo/announcerclock/presentation/fragments/sound_fragment/viewmodel/SoundOptionViewModel;", "getViewModel", "()Lcom/dzo/announcerclock/presentation/fragments/sound_fragment/viewmodel/SoundOptionViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "collectSoundOptions", "", "observeThemeChanges", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "restorePreviousState", "setupRecyclerView", "setupToggles", "app_release"})
public final class NotificationSoundFragment extends com.dzo.announcerclock.utils.core.BaseFragment<com.dzo.announcerclock.databinding.FragmentNotificationSoundBinding> {
    @javax.inject.Inject()
    public com.dzo.announcerclock.presentation.fragments.sound_fragment.adapter.SoundOptionAdapter soundOptionAdapter;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String colorHex = "";
    
    public NotificationSoundFragment() {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.dzo.announcerclock.presentation.fragments.sound_fragment.adapter.SoundOptionAdapter getSoundOptionAdapter() {
        return null;
    }
    
    public final void setSoundOptionAdapter(@org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.presentation.fragments.sound_fragment.adapter.SoundOptionAdapter p0) {
    }
    
    private final com.dzo.announcerclock.presentation.fragments.sound_fragment.viewmodel.SoundOptionViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void setupToggles() {
    }
    
    private final void collectSoundOptions() {
    }
    
    private final void restorePreviousState() {
    }
    
    private final void observeThemeChanges() {
    }
}