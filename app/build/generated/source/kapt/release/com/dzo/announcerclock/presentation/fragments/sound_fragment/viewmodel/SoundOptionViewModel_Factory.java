package com.dzo.announcerclock.presentation.fragments.sound_fragment.viewmodel;

import android.content.Context;
import com.dzo.announcerclock.domain.sound_option_usecase.GetSoundOptionsUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class SoundOptionViewModel_Factory implements Factory<SoundOptionViewModel> {
  private final Provider<GetSoundOptionsUseCase> getSoundOptionsUseCaseProvider;

  private final Provider<Context> contextProvider;

  public SoundOptionViewModel_Factory(
      Provider<GetSoundOptionsUseCase> getSoundOptionsUseCaseProvider,
      Provider<Context> contextProvider) {
    this.getSoundOptionsUseCaseProvider = getSoundOptionsUseCaseProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public SoundOptionViewModel get() {
    return newInstance(getSoundOptionsUseCaseProvider.get(), contextProvider.get());
  }

  public static SoundOptionViewModel_Factory create(
      Provider<GetSoundOptionsUseCase> getSoundOptionsUseCaseProvider,
      Provider<Context> contextProvider) {
    return new SoundOptionViewModel_Factory(getSoundOptionsUseCaseProvider, contextProvider);
  }

  public static SoundOptionViewModel newInstance(GetSoundOptionsUseCase getSoundOptionsUseCase,
      Context context) {
    return new SoundOptionViewModel(getSoundOptionsUseCase, context);
  }
}
