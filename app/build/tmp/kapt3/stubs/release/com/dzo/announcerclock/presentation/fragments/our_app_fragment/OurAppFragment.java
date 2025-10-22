package com.dzo.announcerclock.presentation.fragments.our_app_fragment;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u001a\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t\u00a8\u0006\u0010"}, d2 = {"Lcom/dzo/announcerclock/presentation/fragments/our_app_fragment/OurAppFragment;", "Lcom/dzo/announcerclock/utils/core/BaseFragment;", "Lcom/dzo/announcerclock/databinding/FragmentOurAppBinding;", "()V", "ourAppAdapter", "Lcom/dzo/announcerclock/presentation/fragments/our_app_fragment/adapter/OtherAppAdapter;", "getOurAppAdapter", "()Lcom/dzo/announcerclock/presentation/fragments/our_app_fragment/adapter/OtherAppAdapter;", "setOurAppAdapter", "(Lcom/dzo/announcerclock/presentation/fragments/our_app_fragment/adapter/OtherAppAdapter;)V", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "app_release"})
public final class OurAppFragment extends com.dzo.announcerclock.utils.core.BaseFragment<com.dzo.announcerclock.databinding.FragmentOurAppBinding> {
    @javax.inject.Inject()
    public com.dzo.announcerclock.presentation.fragments.our_app_fragment.adapter.OtherAppAdapter ourAppAdapter;
    
    public OurAppFragment() {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.dzo.announcerclock.presentation.fragments.our_app_fragment.adapter.OtherAppAdapter getOurAppAdapter() {
        return null;
    }
    
    public final void setOurAppAdapter(@org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.presentation.fragments.our_app_fragment.adapter.OtherAppAdapter p0) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
}