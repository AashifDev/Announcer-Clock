package com.dzo.announcerclock.domain.repeat_option_usecase;

import com.dzo.announcerclock.data.repeat_repository.RepeatOptionRepository;
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
public final class GetRepeatOptionUseCase_Factory implements Factory<GetRepeatOptionUseCase> {
  private final Provider<RepeatOptionRepository> repeatOptionRepositoryProvider;

  public GetRepeatOptionUseCase_Factory(
      Provider<RepeatOptionRepository> repeatOptionRepositoryProvider) {
    this.repeatOptionRepositoryProvider = repeatOptionRepositoryProvider;
  }

  @Override
  public GetRepeatOptionUseCase get() {
    return newInstance(repeatOptionRepositoryProvider.get());
  }

  public static GetRepeatOptionUseCase_Factory create(
      Provider<RepeatOptionRepository> repeatOptionRepositoryProvider) {
    return new GetRepeatOptionUseCase_Factory(repeatOptionRepositoryProvider);
  }

  public static GetRepeatOptionUseCase newInstance(RepeatOptionRepository repeatOptionRepository) {
    return new GetRepeatOptionUseCase(repeatOptionRepository);
  }
}
