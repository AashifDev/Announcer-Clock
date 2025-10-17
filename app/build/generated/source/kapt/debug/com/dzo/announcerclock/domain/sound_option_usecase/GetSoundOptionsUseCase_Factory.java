package com.dzo.announcerclock.domain.sound_option_usecase;

import com.dzo.announcerclock.data.sound_repository.SoundOptionRepository;
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
public final class GetSoundOptionsUseCase_Factory implements Factory<GetSoundOptionsUseCase> {
  private final Provider<SoundOptionRepository> repositoryProvider;

  public GetSoundOptionsUseCase_Factory(Provider<SoundOptionRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetSoundOptionsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetSoundOptionsUseCase_Factory create(
      Provider<SoundOptionRepository> repositoryProvider) {
    return new GetSoundOptionsUseCase_Factory(repositoryProvider);
  }

  public static GetSoundOptionsUseCase newInstance(SoundOptionRepository repository) {
    return new GetSoundOptionsUseCase(repository);
  }
}
