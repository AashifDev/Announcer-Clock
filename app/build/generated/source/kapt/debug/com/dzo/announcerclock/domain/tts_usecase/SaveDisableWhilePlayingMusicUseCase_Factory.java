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
public final class SaveDisableWhilePlayingMusicUseCase_Factory implements Factory<SaveDisableWhilePlayingMusicUseCase> {
  private final Provider<TtsRepository> repositoryProvider;

  public SaveDisableWhilePlayingMusicUseCase_Factory(Provider<TtsRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public SaveDisableWhilePlayingMusicUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static SaveDisableWhilePlayingMusicUseCase_Factory create(
      Provider<TtsRepository> repositoryProvider) {
    return new SaveDisableWhilePlayingMusicUseCase_Factory(repositoryProvider);
  }

  public static SaveDisableWhilePlayingMusicUseCase newInstance(TtsRepository repository) {
    return new SaveDisableWhilePlayingMusicUseCase(repository);
  }
}
