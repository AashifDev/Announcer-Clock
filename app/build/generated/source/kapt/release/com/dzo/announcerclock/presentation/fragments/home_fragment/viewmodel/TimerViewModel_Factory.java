package com.dzo.announcerclock.presentation.fragments.home_fragment.viewmodel;

import android.content.Context;
import com.dzo.announcerclock.domain.timer_usecase.AnnounceTimeUseCase;
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
public final class TimerViewModel_Factory implements Factory<TimerViewModel> {
  private final Provider<AnnounceTimeUseCase> announceTimeUseCaseProvider;

  private final Provider<Context> contextProvider;

  public TimerViewModel_Factory(Provider<AnnounceTimeUseCase> announceTimeUseCaseProvider,
      Provider<Context> contextProvider) {
    this.announceTimeUseCaseProvider = announceTimeUseCaseProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public TimerViewModel get() {
    return newInstance(announceTimeUseCaseProvider.get(), contextProvider.get());
  }

  public static TimerViewModel_Factory create(
      Provider<AnnounceTimeUseCase> announceTimeUseCaseProvider,
      Provider<Context> contextProvider) {
    return new TimerViewModel_Factory(announceTimeUseCaseProvider, contextProvider);
  }

  public static TimerViewModel newInstance(AnnounceTimeUseCase announceTimeUseCase,
      Context context) {
    return new TimerViewModel(announceTimeUseCase, context);
  }
}
