package com.dzo.announcerclock.data.timer_repository;

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
public final class TimerRepositoryImpl_Factory implements Factory<TimerRepositoryImpl> {
  @Override
  public TimerRepositoryImpl get() {
    return newInstance();
  }

  public static TimerRepositoryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static TimerRepositoryImpl newInstance() {
    return new TimerRepositoryImpl();
  }

  private static final class InstanceHolder {
    private static final TimerRepositoryImpl_Factory INSTANCE = new TimerRepositoryImpl_Factory();
  }
}
