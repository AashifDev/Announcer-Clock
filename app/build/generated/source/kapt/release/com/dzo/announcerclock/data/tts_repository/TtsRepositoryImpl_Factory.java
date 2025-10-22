package com.dzo.announcerclock.data.tts_repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class TtsRepositoryImpl_Factory implements Factory<TtsRepositoryImpl> {
  @Override
  public TtsRepositoryImpl get() {
    return newInstance();
  }

  public static TtsRepositoryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static TtsRepositoryImpl newInstance() {
    return new TtsRepositoryImpl();
  }

  private static final class InstanceHolder {
    private static final TtsRepositoryImpl_Factory INSTANCE = new TtsRepositoryImpl_Factory();
  }
}
