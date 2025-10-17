package com.dzo.announcerclock.data.sound_repository;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class SoundOptionRepositoryImpl_Factory implements Factory<SoundOptionRepositoryImpl> {
  private final Provider<Context> contextProvider;

  public SoundOptionRepositoryImpl_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SoundOptionRepositoryImpl get() {
    return newInstance(contextProvider.get());
  }

  public static SoundOptionRepositoryImpl_Factory create(Provider<Context> contextProvider) {
    return new SoundOptionRepositoryImpl_Factory(contextProvider);
  }

  public static SoundOptionRepositoryImpl newInstance(Context context) {
    return new SoundOptionRepositoryImpl(context);
  }
}
