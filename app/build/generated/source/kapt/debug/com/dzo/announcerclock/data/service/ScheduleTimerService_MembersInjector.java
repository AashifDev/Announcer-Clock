package com.dzo.announcerclock.data.service;

import com.dzo.announcerclock.domain.timer_usecase.AnnounceTimeUseCase;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class ScheduleTimerService_MembersInjector implements MembersInjector<ScheduleTimerService> {
  private final Provider<AnnounceTimeUseCase> announceTimeUseCaseProvider;

  public ScheduleTimerService_MembersInjector(
      Provider<AnnounceTimeUseCase> announceTimeUseCaseProvider) {
    this.announceTimeUseCaseProvider = announceTimeUseCaseProvider;
  }

  public static MembersInjector<ScheduleTimerService> create(
      Provider<AnnounceTimeUseCase> announceTimeUseCaseProvider) {
    return new ScheduleTimerService_MembersInjector(announceTimeUseCaseProvider);
  }

  @Override
  public void injectMembers(ScheduleTimerService instance) {
    injectAnnounceTimeUseCase(instance, announceTimeUseCaseProvider.get());
  }

  @InjectedFieldSignature("com.dzo.announcerclock.data.service.ScheduleTimerService.announceTimeUseCase")
  public static void injectAnnounceTimeUseCase(ScheduleTimerService instance,
      AnnounceTimeUseCase announceTimeUseCase) {
    instance.announceTimeUseCase = announceTimeUseCase;
  }
}
