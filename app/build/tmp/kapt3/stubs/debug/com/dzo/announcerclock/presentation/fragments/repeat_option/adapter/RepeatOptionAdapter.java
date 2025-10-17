package com.dzo.announcerclock.presentation.fragments.repeat_option.adapter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00142\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u0014\u0015B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\f\u001a\u00020\u00072\n\u0010\r\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001c\u0010\u0010\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000fH\u0016R(\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lcom/dzo/announcerclock/presentation/fragments/repeat_option/adapter/RepeatOptionAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/dzo/announcerclock/presentation/fragments/repeat_option/model/RepeatOption;", "Lcom/dzo/announcerclock/presentation/fragments/repeat_option/adapter/RepeatOptionAdapter$ViewHolder;", "()V", "onItemClick", "Lkotlin/Function1;", "", "getOnItemClick", "()Lkotlin/jvm/functions/Function1;", "setOnItemClick", "(Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "ViewHolder", "app_debug"})
@dagger.hilt.android.scopes.FragmentScoped()
public final class RepeatOptionAdapter extends androidx.recyclerview.widget.ListAdapter<com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption, com.dzo.announcerclock.presentation.fragments.repeat_option.adapter.RepeatOptionAdapter.ViewHolder> {
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function1<? super com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption, kotlin.Unit> onItemClick;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.recyclerview.widget.DiffUtil.ItemCallback<com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption> DIFF_CALLBACK = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.dzo.announcerclock.presentation.fragments.repeat_option.adapter.RepeatOptionAdapter.Companion Companion = null;
    
    @javax.inject.Inject()
    public RepeatOptionAdapter() {
        super(null);
    }
    
    @org.jetbrains.annotations.Nullable()
    public final kotlin.jvm.functions.Function1<com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption, kotlin.Unit> getOnItemClick() {
        return null;
    }
    
    public final void setOnItemClick(@org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption, kotlin.Unit> p0) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.dzo.announcerclock.presentation.fragments.repeat_option.adapter.RepeatOptionAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.presentation.fragments.repeat_option.adapter.RepeatOptionAdapter.ViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/dzo/announcerclock/presentation/fragments/repeat_option/adapter/RepeatOptionAdapter$Companion;", "", "()V", "DIFF_CALLBACK", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/dzo/announcerclock/presentation/fragments/repeat_option/model/RepeatOption;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000b"}, d2 = {"Lcom/dzo/announcerclock/presentation/fragments/repeat_option/adapter/RepeatOptionAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/dzo/announcerclock/databinding/RepeatOptionRawItemBinding;", "(Lcom/dzo/announcerclock/presentation/fragments/repeat_option/adapter/RepeatOptionAdapter;Lcom/dzo/announcerclock/databinding/RepeatOptionRawItemBinding;)V", "getBinding", "()Lcom/dzo/announcerclock/databinding/RepeatOptionRawItemBinding;", "bind", "", "item", "Lcom/dzo/announcerclock/presentation/fragments/repeat_option/model/RepeatOption;", "app_debug"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.dzo.announcerclock.databinding.RepeatOptionRawItemBinding binding = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        com.dzo.announcerclock.databinding.RepeatOptionRawItemBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.dzo.announcerclock.databinding.RepeatOptionRawItemBinding getBinding() {
            return null;
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption item) {
        }
    }
}