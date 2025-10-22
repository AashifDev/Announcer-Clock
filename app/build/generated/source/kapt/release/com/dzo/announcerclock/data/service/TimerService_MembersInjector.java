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
public final class TimerService_MembersInjector implements MembersInjector<TimerService> {
  private final Provider<AnnounceTimeUseCase> announceTimeUseCaseProvider;

  public TimerService_MembersInjector(Provider<AnnounceTimeUseCase> announceTimeUseCaseProvider) {
    this.announceTimeUseCaseProvider = announceTimeUseCaseProvider;
  }

  public static MembersInjector<TimerService> create(
      Provider<AnnounceTimeUseCase> announceTimeUseCaseProvider) {
    return new TimerService_MembersInjector(announceTimeUseCaseProvider);
  }

  @Override
  public void injectMembers(TimerService instance) {
    injectAnnounceTimeUseCase(instance, announceTimeUseCaseProvider.get());
  }

  @InjectedFieldSignature("com.dzo.announcerclock.data.service.TimerService.announceTimeUseCase")
  public static void injectAnnounceTimeUseCase(TimerService instance,
      AnnounceTimeUseCase announceTimeUseCase) {
    instance.announceTimeUseCase = announceTimeUseCase;
  }
}
