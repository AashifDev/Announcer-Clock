package com.dzo.announcerclock.presentation.fragments.tts_fragment.viewmodel;

import android.app.Application;
import com.dzo.announcerclock.di.TtsUseCases;
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
public final class TtsViewModel_Factory implements Factory<TtsViewModel> {
  private final Provider<Application> applicationProvider;

  private final Provider<TtsUseCases> ttsUseCasesProvider;

  public TtsViewModel_Factory(Provider<Application> applicationProvider,
      Provider<TtsUseCases> ttsUseCasesProvider) {
    this.applicationProvider = applicationProvider;
    this.ttsUseCasesProvider = ttsUseCasesProvider;
  }

  @Override
  public TtsViewModel get() {
    return newInstance(applicationProvider.get(), ttsUseCasesProvider.get());
  }

  public static TtsViewModel_Factory create(Provider<Application> applicationProvider,
      Provider<TtsUseCases> ttsUseCasesProvider) {
    return new TtsViewModel_Factory(applicationProvider, ttsUseCasesProvider);
  }

  public static TtsViewModel newInstance(Application application, TtsUseCases ttsUseCases) {
    return new TtsViewModel(application, ttsUseCases);
  }
}
