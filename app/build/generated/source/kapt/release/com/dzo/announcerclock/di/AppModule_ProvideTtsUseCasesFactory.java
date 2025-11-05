package com.dzo.announcerclock.di;

import com.dzo.announcerclock.domain.tts_usecase.GetTtsSettingsUseCase;
import com.dzo.announcerclock.domain.tts_usecase.IsDisableWhilePlayingMusicUseCase;
import com.dzo.announcerclock.domain.tts_usecase.IsEnableDuringPhoneCallsUseCase;
import com.dzo.announcerclock.domain.tts_usecase.IsTimeSpeakingEnabledUseCase;
import com.dzo.announcerclock.domain.tts_usecase.SaveDisableWhilePlayingMusicUseCase;
import com.dzo.announcerclock.domain.tts_usecase.SaveEnableDuringPhoneCallsUseCase;
import com.dzo.announcerclock.domain.tts_usecase.SaveTimeSpeakingEnabledUseCase;
import com.dzo.announcerclock.domain.tts_usecase.SaveTtsSettingsUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AppModule_ProvideTtsUseCasesFactory implements Factory<TtsUseCases> {
  private final Provider<GetTtsSettingsUseCase> getTtsSettingsProvider;

  private final Provider<SaveTtsSettingsUseCase> saveTtsSettingsProvider;

  private final Provider<IsTimeSpeakingEnabledUseCase> isTimeSpeakingEnabledProvider;

  private final Provider<SaveTimeSpeakingEnabledUseCase> saveTimeSpeakingEnabledProvider;

  private final Provider<SaveEnableDuringPhoneCallsUseCase> saveEnableDuringPhoneCallsProvider;

  private final Provider<IsEnableDuringPhoneCallsUseCase> isEnableDuringPhoneCallsProvider;

  private final Provider<SaveDisableWhilePlayingMusicUseCase> saveDisableWhilePlayingMusicProvider;

  private final Provider<IsDisableWhilePlayingMusicUseCase> isDisableWhilePlayingMusicProvider;

  public AppModule_ProvideTtsUseCasesFactory(Provider<GetTtsSettingsUseCase> getTtsSettingsProvider,
      Provider<SaveTtsSettingsUseCase> saveTtsSettingsProvider,
      Provider<IsTimeSpeakingEnabledUseCase> isTimeSpeakingEnabledProvider,
      Provider<SaveTimeSpeakingEnabledUseCase> saveTimeSpeakingEnabledProvider,
      Provider<SaveEnableDuringPhoneCallsUseCase> saveEnableDuringPhoneCallsProvider,
      Provider<IsEnableDuringPhoneCallsUseCase> isEnableDuringPhoneCallsProvider,
      Provider<SaveDisableWhilePlayingMusicUseCase> saveDisableWhilePlayingMusicProvider,
      Provider<IsDisableWhilePlayingMusicUseCase> isDisableWhilePlayingMusicProvider) {
    this.getTtsSettingsProvider = getTtsSettingsProvider;
    this.saveTtsSettingsProvider = saveTtsSettingsProvider;
    this.isTimeSpeakingEnabledProvider = isTimeSpeakingEnabledProvider;
    this.saveTimeSpeakingEnabledProvider = saveTimeSpeakingEnabledProvider;
    this.saveEnableDuringPhoneCallsProvider = saveEnableDuringPhoneCallsProvider;
    this.isEnableDuringPhoneCallsProvider = isEnableDuringPhoneCallsProvider;
    this.saveDisableWhilePlayingMusicProvider = saveDisableWhilePlayingMusicProvider;
    this.isDisableWhilePlayingMusicProvider = isDisableWhilePlayingMusicProvider;
  }

  @Override
  public TtsUseCases get() {
    return provideTtsUseCases(getTtsSettingsProvider.get(), saveTtsSettingsProvider.get(), isTimeSpeakingEnabledProvider.get(), saveTimeSpeakingEnabledProvider.get(), saveEnableDuringPhoneCallsProvider.get(), isEnableDuringPhoneCallsProvider.get(), saveDisableWhilePlayingMusicProvider.get(), isDisableWhilePlayingMusicProvider.get());
  }

  public static AppModule_ProvideTtsUseCasesFactory create(
      Provider<GetTtsSettingsUseCase> getTtsSettingsProvider,
      Provider<SaveTtsSettingsUseCase> saveTtsSettingsProvider,
      Provider<IsTimeSpeakingEnabledUseCase> isTimeSpeakingEnabledProvider,
      Provider<SaveTimeSpeakingEnabledUseCase> saveTimeSpeakingEnabledProvider,
      Provider<SaveEnableDuringPhoneCallsUseCase> saveEnableDuringPhoneCallsProvider,
      Provider<IsEnableDuringPhoneCallsUseCase> isEnableDuringPhoneCallsProvider,
      Provider<SaveDisableWhilePlayingMusicUseCase> saveDisableWhilePlayingMusicProvider,
      Provider<IsDisableWhilePlayingMusicUseCase> isDisableWhilePlayingMusicProvider) {
    return new AppModule_ProvideTtsUseCasesFactory(getTtsSettingsProvider, saveTtsSettingsProvider, isTimeSpeakingEnabledProvider, saveTimeSpeakingEnabledProvider, saveEnableDuringPhoneCallsProvider, isEnableDuringPhoneCallsProvider, saveDisableWhilePlayingMusicProvider, isDisableWhilePlayingMusicProvider);
  }

  public static TtsUseCases provideTtsUseCases(GetTtsSettingsUseCase getTtsSettings,
      SaveTtsSettingsUseCase saveTtsSettings, IsTimeSpeakingEnabledUseCase isTimeSpeakingEnabled,
      SaveTimeSpeakingEnabledUseCase saveTimeSpeakingEnabled,
      SaveEnableDuringPhoneCallsUseCase saveEnableDuringPhoneCalls,
      IsEnableDuringPhoneCallsUseCase isEnableDuringPhoneCalls,
      SaveDisableWhilePlayingMusicUseCase saveDisableWhilePlayingMusic,
      IsDisableWhilePlayingMusicUseCase isDisableWhilePlayingMusic) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideTtsUseCases(getTtsSettings, saveTtsSettings, isTimeSpeakingEnabled, saveTimeSpeakingEnabled, saveEnableDuringPhoneCalls, isEnableDuringPhoneCalls, saveDisableWhilePlayingMusic, isDisableWhilePlayingMusic));
  }
}
