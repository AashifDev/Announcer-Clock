package com.dzo.announcerclock.di;

import android.content.Context;
import com.dzo.announcerclock.presentation.fragments.our_app_fragment.adapter.OtherAppAdapter;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class AdapterModule_ProvideOurOtherAppAdapterFactory implements Factory<OtherAppAdapter> {
  private final Provider<Context> contextProvider;

  public AdapterModule_ProvideOurOtherAppAdapterFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public OtherAppAdapter get() {
    return provideOurOtherAppAdapter(contextProvider.get());
  }

  public static AdapterModule_ProvideOurOtherAppAdapterFactory create(
      Provider<Context> contextProvider) {
    return new AdapterModule_ProvideOurOtherAppAdapterFactory(contextProvider);
  }

  public static OtherAppAdapter provideOurOtherAppAdapter(Context context) {
    return Preconditions.checkNotNullFromProvides(AdapterModule.INSTANCE.provideOurOtherAppAdapter(context));
  }
}
