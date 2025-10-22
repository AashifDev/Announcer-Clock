package com.dzo.announcerclock.domain.timer_usecase;

import com.dzo.announcerclock.data.timer_repository.TimerRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class AnnounceTimeUseCase_Factory implements Factory<AnnounceTimeUseCase> {
  private final Provider<TimerRepository> timerRepositoryProvider;

  public AnnounceTimeUseCase_Factory(Provider<TimerRepository> timerRepositoryProvider) {
    this.timerRepositoryProvider = timerRepositoryProvider;
  }

  @Override
  public AnnounceTimeUseCase get() {
    return newInstance(timerRepositoryProvider.get());
  }

  public static AnnounceTimeUseCase_Factory create(
      Provider<TimerRepository> timerRepositoryProvider) {
    return new AnnounceTimeUseCase_Factory(timerRepositoryProvider);
  }

  public static AnnounceTimeUseCase newInstance(TimerRepository timerRepository) {
    return new AnnounceTimeUseCase(timerRepository);
  }
}
