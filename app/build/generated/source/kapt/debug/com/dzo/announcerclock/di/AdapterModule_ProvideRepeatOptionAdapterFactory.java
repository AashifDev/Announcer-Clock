package com.dzo.announcerclock.di;

import com.dzo.announcerclock.presentation.fragments.repeat_option.adapter.RepeatOptionAdapter;
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
public final class AdapterModule_ProvideRepeatOptionAdapterFactory implements Factory<RepeatOptionAdapter> {
  @Override
  public RepeatOptionAdapter get() {
    return provideRepeatOptionAdapter();
  }

  public static AdapterModule_ProvideRepeatOptionAdapterFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static RepeatOptionAdapter provideRepeatOptionAdapter() {
    return Preconditions.checkNotNullFromProvides(AdapterModule.INSTANCE.provideRepeatOptionAdapter());
  }

  private static final class InstanceHolder {
    private static final AdapterModule_ProvideRepeatOptionAdapterFactory INSTANCE = new AdapterModule_ProvideRepeatOptionAdapterFactory();
  }
}
