package com.dzo.announcerclock.presentation.fragments.home_fragment;

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
public final class HomeFragment_MembersInjector implements MembersInjector<HomeFragment> {
  private final Provider<AnnounceTimeUseCase> announceTimeUseCaseProvider;

  public HomeFragment_MembersInjector(Provider<AnnounceTimeUseCase> announceTimeUseCaseProvider) {
    this.announceTimeUseCaseProvider = announceTimeUseCaseProvider;
  }

  public static MembersInjector<HomeFragment> create(
      Provider<AnnounceTimeUseCase> announceTimeUseCaseProvider) {
    return new HomeFragment_MembersInjector(announceTimeUseCaseProvider);
  }

  @Override
  public void injectMembers(HomeFragment instance) {
    injectAnnounceTimeUseCase(instance, announceTimeUseCaseProvider.get());
  }

  @InjectedFieldSignature("com.dzo.announcerclock.presentation.fragments.home_fragment.HomeFragment.announceTimeUseCase")
  public static void injectAnnounceTimeUseCase(HomeFragment instance,
      AnnounceTimeUseCase announceTimeUseCase) {
    instance.announceTimeUseCase = announceTimeUseCase;
  }
}
