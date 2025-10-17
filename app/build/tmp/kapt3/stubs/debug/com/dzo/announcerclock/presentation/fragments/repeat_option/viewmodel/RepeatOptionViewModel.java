package com.dzo.announcerclock.presentation.fragments.repeat_option.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u00020\u000eH\u0002J&\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\b2\u0016\b\u0002\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012J\u000e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\bR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0015"}, d2 = {"Lcom/dzo/announcerclock/presentation/fragments/repeat_option/viewmodel/RepeatOptionViewModel;", "Landroidx/lifecycle/ViewModel;", "getRepeatOptionUseCase", "Lcom/dzo/announcerclock/domain/repeat_option_usecase/GetRepeatOptionUseCase;", "(Lcom/dzo/announcerclock/domain/repeat_option_usecase/GetRepeatOptionUseCase;)V", "_repeatOptions", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/dzo/announcerclock/presentation/fragments/repeat_option/model/RepeatOption;", "repeatOptions", "Lkotlinx/coroutines/flow/StateFlow;", "getRepeatOptions", "()Lkotlinx/coroutines/flow/StateFlow;", "loadRepeatOptions", "", "selectCustomOption", "selectedItem", "customPair", "Lkotlin/Pair;", "", "selectOption", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class RepeatOptionViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.dzo.announcerclock.domain.repeat_option_usecase.GetRepeatOptionUseCase getRepeatOptionUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption>> _repeatOptions = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption>> repeatOptions = null;
    
    @javax.inject.Inject()
    public RepeatOptionViewModel(@org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.domain.repeat_option_usecase.GetRepeatOptionUseCase getRepeatOptionUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption>> getRepeatOptions() {
        return null;
    }
    
    private final void loadRepeatOptions() {
    }
    
    public final void selectCustomOption(@org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption selectedItem, @org.jetbrains.annotations.Nullable()
    kotlin.Pair<java.lang.Long, java.lang.Long> customPair) {
    }
    
    public final void selectOption(@org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption selectedItem) {
    }
}