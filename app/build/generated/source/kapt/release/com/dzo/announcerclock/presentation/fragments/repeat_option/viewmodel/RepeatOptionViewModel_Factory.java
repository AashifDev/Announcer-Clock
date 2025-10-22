package com.dzo.announcerclock.presentation.fragments.repeat_option.viewmodel;

import com.dzo.announcerclock.domain.repeat_option_usecase.GetRepeatOptionUseCase;
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
public final class RepeatOptionViewModel_Factory implements Factory<RepeatOptionViewModel> {
  private final Provider<GetRepeatOptionUseCase> getRepeatOptionUseCaseProvider;

  public RepeatOptionViewModel_Factory(
      Provider<GetRepeatOptionUseCase> getRepeatOptionUseCaseProvider) {
    this.getRepeatOptionUseCaseProvider = getRepeatOptionUseCaseProvider;
  }

  @Override
  public RepeatOptionViewModel get() {
    return newInstance(getRepeatOptionUseCaseProvider.get());
  }

  public static RepeatOptionViewModel_Factory create(
      Provider<GetRepeatOptionUseCase> getRepeatOptionUseCaseProvider) {
    return new RepeatOptionViewModel_Factory(getRepeatOptionUseCaseProvider);
  }

  public static RepeatOptionViewModel newInstance(GetRepeatOptionUseCase getRepeatOptionUseCase) {
    return new RepeatOptionViewModel(getRepeatOptionUseCase);
  }
}
