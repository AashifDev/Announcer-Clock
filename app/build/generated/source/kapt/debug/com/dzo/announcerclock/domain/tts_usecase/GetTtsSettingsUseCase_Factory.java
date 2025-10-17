package com.dzo.announcerclock.domain.tts_usecase;

import com.dzo.announcerclock.data.tts_repository.TtsRepository;
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
public final class GetTtsSettingsUseCase_Factory implements Factory<GetTtsSettingsUseCase> {
  private final Provider<TtsRepository> repositoryProvider;

  public GetTtsSettingsUseCase_Factory(Provider<TtsRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetTtsSettingsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetTtsSettingsUseCase_Factory create(Provider<TtsRepository> repositoryProvider) {
    return new GetTtsSettingsUseCase_Factory(repositoryProvider);
  }

  public static GetTtsSettingsUseCase newInstance(TtsRepository repository) {
    return new GetTtsSettingsUseCase(repository);
  }
}
