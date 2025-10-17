package com.dzo.announcerclock.data.repeat_repository;

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
public final class RepeatOptionRepositoryImpl_Factory implements Factory<RepeatOptionRepositoryImpl> {
  @Override
  public RepeatOptionRepositoryImpl get() {
    return newInstance();
  }

  public static RepeatOptionRepositoryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static RepeatOptionRepositoryImpl newInstance() {
    return new RepeatOptionRepositoryImpl();
  }

  private static final class InstanceHolder {
    private static final RepeatOptionRepositoryImpl_Factory INSTANCE = new RepeatOptionRepositoryImpl_Factory();
  }
}
