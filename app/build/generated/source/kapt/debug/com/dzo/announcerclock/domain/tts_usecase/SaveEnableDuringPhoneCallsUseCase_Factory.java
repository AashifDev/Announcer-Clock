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
public final class SaveEnableDuringPhoneCallsUseCase_Factory implements Factory<SaveEnableDuringPhoneCallsUseCase> {
  private final Provider<TtsRepository> repositoryProvider;

  public SaveEnableDuringPhoneCallsUseCase_Factory(Provider<TtsRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public SaveEnableDuringPhoneCallsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static SaveEnableDuringPhoneCallsUseCase_Factory create(
      Provider<TtsRepository> repositoryProvider) {
    return new SaveEnableDuringPhoneCallsUseCase_Factory(repositoryProvider);
  }

  public static SaveEnableDuringPhoneCallsUseCase newInstance(TtsRepository repository) {
    return new SaveEnableDuringPhoneCallsUseCase(repository);
  }
}
