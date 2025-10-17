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
public final class IsDisableWhilePlayingMusicUseCase_Factory implements Factory<IsDisableWhilePlayingMusicUseCase> {
  private final Provider<TtsRepository> repositoryProvider;

  public IsDisableWhilePlayingMusicUseCase_Factory(Provider<TtsRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public IsDisableWhilePlayingMusicUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static IsDisableWhilePlayingMusicUseCase_Factory create(
      Provider<TtsRepository> repositoryProvider) {
    return new IsDisableWhilePlayingMusicUseCase_Factory(repositoryProvider);
  }

  public static IsDisableWhilePlayingMusicUseCase newInstance(TtsRepository repository) {
    return new IsDisableWhilePlayingMusicUseCase(repository);
  }
}
