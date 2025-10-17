package com.dzo.announcerclock.presentation.fragments.tts_fragment;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u001a\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0012H\u0002J\b\u0010\u0018\u001a\u00020\u0012H\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/dzo/announcerclock/presentation/fragments/tts_fragment/TextToSpeechFragment;", "Lcom/dzo/announcerclock/utils/core/BaseFragment;", "Lcom/dzo/announcerclock/databinding/FragmentTextToSpeechBinding;", "()V", "langAdapter", "Landroid/widget/ArrayAdapter;", "", "suppressLangSelection", "", "suppressVoiceSelection", "viewModel", "Lcom/dzo/announcerclock/presentation/fragments/tts_fragment/viewmodel/TtsViewModel;", "getViewModel", "()Lcom/dzo/announcerclock/presentation/fragments/tts_fragment/viewmodel/TtsViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "voiceAdapter", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setupListeners", "setupObservers", "app_debug"})
public final class TextToSpeechFragment extends com.dzo.announcerclock.utils.core.BaseFragment<com.dzo.announcerclock.databinding.FragmentTextToSpeechBinding> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private android.widget.ArrayAdapter<java.lang.String> langAdapter;
    private android.widget.ArrayAdapter<java.lang.String> voiceAdapter;
    private boolean suppressLangSelection = false;
    private boolean suppressVoiceSelection = false;
    
    public TextToSpeechFragment() {
        super(null);
    }
    
    private final com.dzo.announcerclock.presentation.fragments.tts_fragment.viewmodel.TtsViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupObservers() {
    }
    
    private final void setupListeners() {
    }
}