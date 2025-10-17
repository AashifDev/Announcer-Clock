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
public final class SaveDisableDuringPhoneCallsUseCase_Factory implements Factory<SaveDisableDuringPhoneCallsUseCase> {
  private final Provider<TtsRepository> repositoryProvider;

  public SaveDisableDuringPhoneCallsUseCase_Factory(Provider<TtsRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public SaveDisableDuringPhoneCallsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static SaveDisableDuringPhoneCallsUseCase_Factory create(
      Provider<TtsRepository> repositoryProvider) {
    return new SaveDisableDuringPhoneCallsUseCase_Factory(repositoryProvider);
  }

  public static SaveDisableDuringPhoneCallsUseCase newInstance(TtsRepository repository) {
    return new SaveDisableDuringPhoneCallsUseCase(repository);
  }
}
