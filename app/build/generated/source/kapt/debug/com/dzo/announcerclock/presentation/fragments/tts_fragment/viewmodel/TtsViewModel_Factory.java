package com.dzo.announcerclock.presentation.fragments.tts_fragment.viewmodel;

import android.app.Application;
import com.dzo.announcerclock.domain.tts_usecase.GetTtsSettingsUseCase;
import com.dzo.announcerclock.domain.tts_usecase.IsDisableDuringPhoneCallsUseCase;
import com.dzo.announcerclock.domain.tts_usecase.IsDisableWhilePlayingMusicUseCase;
import com.dzo.announcerclock.domain.tts_usecase.IsTimeSpeakingEnabledUseCase;
import com.dzo.announcerclock.domain.tts_usecase.SaveDisableDuringPhoneCallsUseCase;
import com.dzo.announcerclock.domain.tts_usecase.SaveDisableWhilePlayingMusicUseCase;
import com.dzo.announcerclock.domain.tts_usecase.SaveTimeSpeakingEnabledUseCase;
import com.dzo.announcerclock.domain.tts_usecase.SaveTtsSettingsUseCase;
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

  private final Provider<GetTtsSettingsUseCase> getTtsSettingsProvider;

  private final Provider<SaveTtsSettingsUseCase> saveTtsSettingsProvider;

  private final Provider<IsTimeSpeakingEnabledUseCase> isTimeSpeakingEnabledProvider;

  private final Provider<SaveTimeSpeakingEnabledUseCase> saveTimeSpeakingEnabledProvider;

  private final Provider<SaveDisableDuringPhoneCallsUseCase> saveDisableDuringPhoneCallsProvider;

  private final Provider<IsDisableDuringPhoneCallsUseCase> isDisableDuringPhoneCallsProvider;

  private final Provider<SaveDisableWhilePlayingMusicUseCase> saveDisableWhilePlayingMusicProvider;

  private final Provider<IsDisableWhilePlayingMusicUseCase> isDisableWhilePlayingMusicProvider;

  public TtsViewModel_Factory(Provider<Application> applicationProvider,
      Provider<GetTtsSettingsUseCase> getTtsSettingsProvider,
      Provider<SaveTtsSettingsUseCase> saveTtsSettingsProvider,
      Provider<IsTimeSpeakingEnabledUseCase> isTimeSpeakingEnabledProvider,
      Provider<SaveTimeSpeakingEnabledUseCase> saveTimeSpeakingEnabledProvider,
      Provider<SaveDisableDuringPhoneCallsUseCase> saveDisableDuringPhoneCallsProvider,
      Provider<IsDisableDuringPhoneCallsUseCase> isDisableDuringPhoneCallsProvider,
      Provider<SaveDisableWhilePlayingMusicUseCase> saveDisableWhilePlayingMusicProvider,
      Provider<IsDisableWhilePlayingMusicUseCase> isDisableWhilePlayingMusicProvider) {
    this.applicationProvider = applicationProvider;
    this.getTtsSettingsProvider = getTtsSettingsProvider;
    this.saveTtsSettingsProvider = saveTtsSettingsProvider;
    this.isTimeSpeakingEnabledProvider = isTimeSpeakingEnabledProvider;
    this.saveTimeSpeakingEnabledProvider = saveTimeSpeakingEnabledProvider;
    this.saveDisableDuringPhoneCallsProvider = saveDisableDuringPhoneCallsProvider;
    this.isDisableDuringPhoneCallsProvider = isDisableDuringPhoneCallsProvider;
    this.saveDisableWhilePlayingMusicProvider = saveDisableWhilePlayingMusicProvider;
    this.isDisableWhilePlayingMusicProvider = isDisableWhilePlayingMusicProvider;
  }

  @Override
  public TtsViewModel get() {
    return newInstance(applicationProvider.get(), getTtsSettingsProvider.get(), saveTtsSettingsProvider.get(), isTimeSpeakingEnabledProvider.get(), saveTimeSpeakingEnabledProvider.get(), saveDisableDuringPhoneCallsProvider.get(), isDisableDuringPhoneCallsProvider.get(), saveDisableWhilePlayingMusicProvider.get(), isDisableWhilePlayingMusicProvider.get());
  }

  public static TtsViewModel_Factory create(Provider<Application> applicationProvider,
      Provider<GetTtsSettingsUseCase> getTtsSettingsProvider,
      Provider<SaveTtsSettingsUseCase> saveTtsSettingsProvider,
      Provider<IsTimeSpeakingEnabledUseCase> isTimeSpeakingEnabledProvider,
      Provider<SaveTimeSpeakingEnabledUseCase> saveTimeSpeakingEnabledProvider,
      Provider<SaveDisableDuringPhoneCallsUseCase> saveDisableDuringPhoneCallsProvider,
      Provider<IsDisableDuringPhoneCallsUseCase> isDisableDuringPhoneCallsProvider,
      Provider<SaveDisableWhilePlayingMusicUseCase> saveDisableWhilePlayingMusicProvider,
      Provider<IsDisableWhilePlayingMusicUseCase> isDisableWhilePlayingMusicProvider) {
    return new TtsViewModel_Factory(applicationProvider, getTtsSettingsProvider, saveTtsSettingsProvider, isTimeSpeakingEnabledProvider, saveTimeSpeakingEnabledProvider, saveDisableDuringPhoneCallsProvider, isDisableDuringPhoneCallsProvider, saveDisableWhilePlayingMusicProvider, isDisableWhilePlayingMusicProvider);
  }

  public static TtsViewModel newInstance(Application application,
      GetTtsSettingsUseCase getTtsSettings, SaveTtsSettingsUseCase saveTtsSettings,
      IsTimeSpeakingEnabledUseCase isTimeSpeakingEnabled,
      SaveTimeSpeakingEnabledUseCase saveTimeSpeakingEnabled,
      SaveDisableDuringPhoneCallsUseCase saveDisableDuringPhoneCalls,
      IsDisableDuringPhoneCallsUseCase isDisableDuringPhoneCalls,
      SaveDisableWhilePlayingMusicUseCase saveDisableWhilePlayingMusic,
      IsDisableWhilePlayingMusicUseCase isDisableWhilePlayingMusic) {
    return new TtsViewModel(application, getTtsSettings, saveTtsSettings, isTimeSpeakingEnabled, saveTimeSpeakingEnabled, saveDisableDuringPhoneCalls, isDisableDuringPhoneCalls, saveDisableWhilePlayingMusic, isDisableWhilePlayingMusic);
  }
}
