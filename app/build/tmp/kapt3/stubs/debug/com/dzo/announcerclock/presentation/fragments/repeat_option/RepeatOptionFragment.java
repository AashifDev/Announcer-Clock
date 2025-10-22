package com.dzo.announcerclock.presentation.fragments.repeat_option;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u001a\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J \u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!H\u0002J(\u0010#\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020!2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!H\u0002J(\u0010%\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020!2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!H\u0002J\u0010\u0010&\u001a\u00020\u00182\u0006\u0010\'\u001a\u00020(H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006)"}, d2 = {"Lcom/dzo/announcerclock/presentation/fragments/repeat_option/RepeatOptionFragment;", "Lcom/dzo/announcerclock/utils/core/BaseFragment;", "Lcom/dzo/announcerclock/databinding/FragmentRepeatOptionBinding;", "()V", "colorHexx", "", "repeatOptionAdapter", "Lcom/dzo/announcerclock/presentation/fragments/repeat_option/adapter/RepeatOptionAdapter;", "getRepeatOptionAdapter", "()Lcom/dzo/announcerclock/presentation/fragments/repeat_option/adapter/RepeatOptionAdapter;", "setRepeatOptionAdapter", "(Lcom/dzo/announcerclock/presentation/fragments/repeat_option/adapter/RepeatOptionAdapter;)V", "timerViewModel", "Lcom/dzo/announcerclock/presentation/fragments/home_fragment/viewmodel/TimerViewModel;", "getTimerViewModel", "()Lcom/dzo/announcerclock/presentation/fragments/home_fragment/viewmodel/TimerViewModel;", "timerViewModel$delegate", "Lkotlin/Lazy;", "viewModel", "Lcom/dzo/announcerclock/presentation/fragments/repeat_option/viewmodel/RepeatOptionViewModel;", "getViewModel", "()Lcom/dzo/announcerclock/presentation/fragments/repeat_option/viewmodel/RepeatOptionViewModel;", "viewModel$delegate", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setupPicker", "picker", "Landroid/widget/NumberPicker;", "min", "", "max", "setupPickerIntervalMinuteWithStep", "step", "setupPickerIntervalTotalMinuteWithStep", "showCustomRepeatDialog", "option", "Lcom/dzo/announcerclock/presentation/fragments/repeat_option/model/RepeatOption;", "app_debug"})
public final class RepeatOptionFragment extends com.dzo.announcerclock.utils.core.BaseFragment<com.dzo.announcerclock.databinding.FragmentRepeatOptionBinding> {
    @javax.inject.Inject()
    public com.dzo.announcerclock.presentation.fragments.repeat_option.adapter.RepeatOptionAdapter repeatOptionAdapter;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy timerViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String colorHexx = "";
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    
    public RepeatOptionFragment() {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.dzo.announcerclock.presentation.fragments.repeat_option.adapter.RepeatOptionAdapter getRepeatOptionAdapter() {
        return null;
    }
    
    public final void setRepeatOptionAdapter(@org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.presentation.fragments.repeat_option.adapter.RepeatOptionAdapter p0) {
    }
    
    private final com.dzo.announcerclock.presentation.fragments.home_fragment.viewmodel.TimerViewModel getTimerViewModel() {
        return null;
    }
    
    private final com.dzo.announcerclock.presentation.fragments.repeat_option.viewmodel.RepeatOptionViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void showCustomRepeatDialog(com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption option) {
    }
    
    private final void setupPickerIntervalMinuteWithStep(android.widget.NumberPicker picker, int step, int min, int max) {
    }
    
    private final void setupPickerIntervalTotalMinuteWithStep(android.widget.NumberPicker picker, int step, int min, int max) {
    }
    
    private final void setupPicker(android.widget.NumberPicker picker, int min, int max) {
    }
}