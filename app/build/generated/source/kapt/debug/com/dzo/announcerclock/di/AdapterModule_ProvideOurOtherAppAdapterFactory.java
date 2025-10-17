package com.dzo.announcerclock.di;

import com.dzo.announcerclock.presentation.fragments.our_app_fragment.adapter.OtherAppAdapter;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
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
  @Override
  public OtherAppAdapter get() {
    return provideOurOtherAppAdapter();
  }

  public static AdapterModule_ProvideOurOtherAppAdapterFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static OtherAppAdapter provideOurOtherAppAdapter() {
    return Preconditions.checkNotNullFromProvides(AdapterModule.INSTANCE.provideOurOtherAppAdapter());
  }

  private static final class InstanceHolder {
    private static final AdapterModule_ProvideOurOtherAppAdapterFactory INSTANCE = new AdapterModule_ProvideOurOtherAppAdapterFactory();
  }
}
