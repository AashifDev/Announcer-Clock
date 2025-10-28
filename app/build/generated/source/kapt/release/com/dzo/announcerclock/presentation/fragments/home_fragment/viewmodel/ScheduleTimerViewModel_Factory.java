package com.dzo.announcerclock.presentation.fragments.home_fragment.viewmodel;

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
public final class ScheduleTimerViewModel_Factory implements Factory<ScheduleTimerViewModel> {
  private final Provider<Context> contextProvider;

  public ScheduleTimerViewModel_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ScheduleTimerViewModel get() {
    return newInstance(contextProvider.get());
  }

  public static ScheduleTimerViewModel_Factory create(Provider<Context> contextProvider) {
    return new ScheduleTimerViewModel_Factory(contextProvider);
  }

  public static ScheduleTimerViewModel newInstance(Context context) {
    return new ScheduleTimerViewModel(context);
  }
}
