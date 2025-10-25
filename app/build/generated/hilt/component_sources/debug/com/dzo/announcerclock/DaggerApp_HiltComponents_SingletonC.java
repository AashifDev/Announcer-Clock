package com.dzo.announcerclock;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.dzo.announcerclock.data.repeat_repository.RepeatOptionRepository;
import com.dzo.announcerclock.data.service.ScheduleTimerService;
import com.dzo.announcerclock.data.service.ScheduleTimerService_MembersInjector;
import com.dzo.announcerclock.data.service.TimerService;
import com.dzo.announcerclock.data.service.TimerService_MembersInjector;
import com.dzo.announcerclock.data.sound_repository.SoundOptionRepository;
import com.dzo.announcerclock.data.timer_repository.TimerRepository;
import com.dzo.announcerclock.data.tts_repository.TtsRepositoryImpl;
import com.dzo.announcerclock.di.AdapterModule_ProvideOurOtherAppAdapterFactory;
import com.dzo.announcerclock.di.AdapterModule_ProvideRepeatOptionAdapterFactory;
import com.dzo.announcerclock.di.AdapterModule_ProvideSoundOptionAdapterFactory;
import com.dzo.announcerclock.di.AppModule_ProvideRepeatOptionRepositoryFactory;
import com.dzo.announcerclock.di.AppModule_ProvideSoundOptionRepositoryFactory;
import com.dzo.announcerclock.di.AppModule_ProvideTimerRepositoryFactory;
import com.dzo.announcerclock.domain.repeat_option_usecase.GetRepeatOptionUseCase;
import com.dzo.announcerclock.domain.sound_option_usecase.GetSoundOptionsUseCase;
import com.dzo.announcerclock.domain.timer_usecase.AnnounceTimeUseCase;
import com.dzo.announcerclock.domain.tts_usecase.GetTtsSettingsUseCase;
import com.dzo.announcerclock.domain.tts_usecase.IsDisableDuringPhoneCallsUseCase;
import com.dzo.announcerclock.domain.tts_usecase.IsDisableWhilePlayingMusicUseCase;
import com.dzo.announcerclock.domain.tts_usecase.IsTimeSpeakingEnabledUseCase;
import com.dzo.announcerclock.domain.tts_usecase.SaveDisableDuringPhoneCallsUseCase;
import com.dzo.announcerclock.domain.tts_usecase.SaveDisableWhilePlayingMusicUseCase;
import com.dzo.announcerclock.domain.tts_usecase.SaveTimeSpeakingEnabledUseCase;
import com.dzo.announcerclock.domain.tts_usecase.SaveTtsSettingsUseCase;
import com.dzo.announcerclock.presentation.activity.MainActivity;
import com.dzo.announcerclock.presentation.fragments.home_fragment.HomeFragment;
import com.dzo.announcerclock.presentation.fragments.home_fragment.HomeFragment_MembersInjector;
import com.dzo.announcerclock.presentation.fragments.home_fragment.viewmodel.ScheduleTimerViewModel;
import com.dzo.announcerclock.presentation.fragments.home_fragment.viewmodel.ScheduleTimerViewModel_HiltModules;
import com.dzo.announcerclock.presentation.fragments.home_fragment.viewmodel.TimerViewModel;
import com.dzo.announcerclock.presentation.fragments.home_fragment.viewmodel.TimerViewModel_HiltModules;
import com.dzo.announcerclock.presentation.fragments.our_app_fragment.OurAppFragment;
import com.dzo.announcerclock.presentation.fragments.our_app_fragment.OurAppFragment_MembersInjector;
import com.dzo.announcerclock.presentation.fragments.our_app_fragment.adapter.OtherAppAdapter;
import com.dzo.announcerclock.presentation.fragments.repeat_option.RepeatOptionFragment;
import com.dzo.announcerclock.presentation.fragments.repeat_option.RepeatOptionFragment_MembersInjector;
import com.dzo.announcerclock.presentation.fragments.repeat_option.adapter.RepeatOptionAdapter;
import com.dzo.announcerclock.presentation.fragments.repeat_option.viewmodel.RepeatOptionViewModel;
import com.dzo.announcerclock.presentation.fragments.repeat_option.viewmodel.RepeatOptionViewModel_HiltModules;
import com.dzo.announcerclock.presentation.fragments.sound_fragment.NotificationSoundFragment;
import com.dzo.announcerclock.presentation.fragments.sound_fragment.NotificationSoundFragment_MembersInjector;
import com.dzo.announcerclock.presentation.fragments.sound_fragment.adapter.SoundOptionAdapter;
import com.dzo.announcerclock.presentation.fragments.sound_fragment.viewmodel.SoundOptionViewModel;
import com.dzo.announcerclock.presentation.fragments.sound_fragment.viewmodel.SoundOptionViewModel_HiltModules;
import com.dzo.announcerclock.presentation.fragments.tts_fragment.TextToSpeechFragment;
import com.dzo.announcerclock.presentation.fragments.tts_fragment.viewmodel.TtsViewModel;
import com.dzo.announcerclock.presentation.fragments.tts_fragment.viewmodel.TtsViewModel_HiltModules;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideApplicationFactory;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.IdentifierNameString;
import dagger.internal.KeepFieldType;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

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
public final class DaggerApp_HiltComponents_SingletonC {
  private DaggerApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public App_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements App_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public App_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements App_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public App_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements App_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public App_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements App_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public App_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements App_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public App_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements App_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public App_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements App_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public App_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends App_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends App_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    private AnnounceTimeUseCase announceTimeUseCase() {
      return new AnnounceTimeUseCase(singletonCImpl.provideTimerRepositoryProvider.get());
    }

    @Override
    public void injectHomeFragment(HomeFragment homeFragment) {
      injectHomeFragment2(homeFragment);
    }

    @Override
    public void injectOurAppFragment(OurAppFragment ourAppFragment) {
      injectOurAppFragment2(ourAppFragment);
    }

    @Override
    public void injectRepeatOptionFragment(RepeatOptionFragment repeatOptionFragment) {
      injectRepeatOptionFragment2(repeatOptionFragment);
    }

    @Override
    public void injectNotificationSoundFragment(
        NotificationSoundFragment notificationSoundFragment) {
      injectNotificationSoundFragment2(notificationSoundFragment);
    }

    @Override
    public void injectTextToSpeechFragment(TextToSpeechFragment textToSpeechFragment) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }

    @CanIgnoreReturnValue
    private HomeFragment injectHomeFragment2(HomeFragment instance) {
      HomeFragment_MembersInjector.injectAnnounceTimeUseCase(instance, announceTimeUseCase());
      return instance;
    }

    @CanIgnoreReturnValue
    private OurAppFragment injectOurAppFragment2(OurAppFragment instance) {
      OurAppFragment_MembersInjector.injectOurAppAdapter(instance, singletonCImpl.provideOurOtherAppAdapterProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private RepeatOptionFragment injectRepeatOptionFragment2(RepeatOptionFragment instance) {
      RepeatOptionFragment_MembersInjector.injectRepeatOptionAdapter(instance, singletonCImpl.provideRepeatOptionAdapterProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private NotificationSoundFragment injectNotificationSoundFragment2(
        NotificationSoundFragment instance) {
      NotificationSoundFragment_MembersInjector.injectSoundOptionAdapter(instance, singletonCImpl.provideSoundOptionAdapterProvider.get());
      return instance;
    }
  }

  private static final class ViewCImpl extends App_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends App_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(MapBuilder.<String, Boolean>newMapBuilder(5).put(LazyClassKeyProvider.com_dzo_announcerclock_presentation_fragments_repeat_option_viewmodel_RepeatOptionViewModel, RepeatOptionViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_dzo_announcerclock_presentation_fragments_home_fragment_viewmodel_ScheduleTimerViewModel, ScheduleTimerViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_dzo_announcerclock_presentation_fragments_sound_fragment_viewmodel_SoundOptionViewModel, SoundOptionViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_dzo_announcerclock_presentation_fragments_home_fragment_viewmodel_TimerViewModel, TimerViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_dzo_announcerclock_presentation_fragments_tts_fragment_viewmodel_TtsViewModel, TtsViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_dzo_announcerclock_presentation_fragments_home_fragment_viewmodel_TimerViewModel = "com.dzo.announcerclock.presentation.fragments.home_fragment.viewmodel.TimerViewModel";

      static String com_dzo_announcerclock_presentation_fragments_home_fragment_viewmodel_ScheduleTimerViewModel = "com.dzo.announcerclock.presentation.fragments.home_fragment.viewmodel.ScheduleTimerViewModel";

      static String com_dzo_announcerclock_presentation_fragments_tts_fragment_viewmodel_TtsViewModel = "com.dzo.announcerclock.presentation.fragments.tts_fragment.viewmodel.TtsViewModel";

      static String com_dzo_announcerclock_presentation_fragments_repeat_option_viewmodel_RepeatOptionViewModel = "com.dzo.announcerclock.presentation.fragments.repeat_option.viewmodel.RepeatOptionViewModel";

      static String com_dzo_announcerclock_presentation_fragments_sound_fragment_viewmodel_SoundOptionViewModel = "com.dzo.announcerclock.presentation.fragments.sound_fragment.viewmodel.SoundOptionViewModel";

      @KeepFieldType
      TimerViewModel com_dzo_announcerclock_presentation_fragments_home_fragment_viewmodel_TimerViewModel2;

      @KeepFieldType
      ScheduleTimerViewModel com_dzo_announcerclock_presentation_fragments_home_fragment_viewmodel_ScheduleTimerViewModel2;

      @KeepFieldType
      TtsViewModel com_dzo_announcerclock_presentation_fragments_tts_fragment_viewmodel_TtsViewModel2;

      @KeepFieldType
      RepeatOptionViewModel com_dzo_announcerclock_presentation_fragments_repeat_option_viewmodel_RepeatOptionViewModel2;

      @KeepFieldType
      SoundOptionViewModel com_dzo_announcerclock_presentation_fragments_sound_fragment_viewmodel_SoundOptionViewModel2;
    }
  }

  private static final class ViewModelCImpl extends App_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<RepeatOptionViewModel> repeatOptionViewModelProvider;

    private Provider<ScheduleTimerViewModel> scheduleTimerViewModelProvider;

    private Provider<SoundOptionViewModel> soundOptionViewModelProvider;

    private Provider<TimerViewModel> timerViewModelProvider;

    private Provider<TtsViewModel> ttsViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    private GetRepeatOptionUseCase getRepeatOptionUseCase() {
      return new GetRepeatOptionUseCase(singletonCImpl.provideRepeatOptionRepositoryProvider.get());
    }

    private GetSoundOptionsUseCase getSoundOptionsUseCase() {
      return new GetSoundOptionsUseCase(singletonCImpl.provideSoundOptionRepositoryProvider.get());
    }

    private AnnounceTimeUseCase announceTimeUseCase() {
      return new AnnounceTimeUseCase(singletonCImpl.provideTimerRepositoryProvider.get());
    }

    private GetTtsSettingsUseCase getTtsSettingsUseCase() {
      return new GetTtsSettingsUseCase(new TtsRepositoryImpl());
    }

    private SaveTtsSettingsUseCase saveTtsSettingsUseCase() {
      return new SaveTtsSettingsUseCase(new TtsRepositoryImpl());
    }

    private IsTimeSpeakingEnabledUseCase isTimeSpeakingEnabledUseCase() {
      return new IsTimeSpeakingEnabledUseCase(new TtsRepositoryImpl());
    }

    private SaveTimeSpeakingEnabledUseCase saveTimeSpeakingEnabledUseCase() {
      return new SaveTimeSpeakingEnabledUseCase(new TtsRepositoryImpl());
    }

    private SaveDisableDuringPhoneCallsUseCase saveDisableDuringPhoneCallsUseCase() {
      return new SaveDisableDuringPhoneCallsUseCase(new TtsRepositoryImpl());
    }

    private IsDisableDuringPhoneCallsUseCase isDisableDuringPhoneCallsUseCase() {
      return new IsDisableDuringPhoneCallsUseCase(new TtsRepositoryImpl());
    }

    private SaveDisableWhilePlayingMusicUseCase saveDisableWhilePlayingMusicUseCase() {
      return new SaveDisableWhilePlayingMusicUseCase(new TtsRepositoryImpl());
    }

    private IsDisableWhilePlayingMusicUseCase isDisableWhilePlayingMusicUseCase() {
      return new IsDisableWhilePlayingMusicUseCase(new TtsRepositoryImpl());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.repeatOptionViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.scheduleTimerViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.soundOptionViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.timerViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.ttsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(5).put(LazyClassKeyProvider.com_dzo_announcerclock_presentation_fragments_repeat_option_viewmodel_RepeatOptionViewModel, ((Provider) repeatOptionViewModelProvider)).put(LazyClassKeyProvider.com_dzo_announcerclock_presentation_fragments_home_fragment_viewmodel_ScheduleTimerViewModel, ((Provider) scheduleTimerViewModelProvider)).put(LazyClassKeyProvider.com_dzo_announcerclock_presentation_fragments_sound_fragment_viewmodel_SoundOptionViewModel, ((Provider) soundOptionViewModelProvider)).put(LazyClassKeyProvider.com_dzo_announcerclock_presentation_fragments_home_fragment_viewmodel_TimerViewModel, ((Provider) timerViewModelProvider)).put(LazyClassKeyProvider.com_dzo_announcerclock_presentation_fragments_tts_fragment_viewmodel_TtsViewModel, ((Provider) ttsViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return Collections.<Class<?>, Object>emptyMap();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_dzo_announcerclock_presentation_fragments_home_fragment_viewmodel_TimerViewModel = "com.dzo.announcerclock.presentation.fragments.home_fragment.viewmodel.TimerViewModel";

      static String com_dzo_announcerclock_presentation_fragments_home_fragment_viewmodel_ScheduleTimerViewModel = "com.dzo.announcerclock.presentation.fragments.home_fragment.viewmodel.ScheduleTimerViewModel";

      static String com_dzo_announcerclock_presentation_fragments_sound_fragment_viewmodel_SoundOptionViewModel = "com.dzo.announcerclock.presentation.fragments.sound_fragment.viewmodel.SoundOptionViewModel";

      static String com_dzo_announcerclock_presentation_fragments_tts_fragment_viewmodel_TtsViewModel = "com.dzo.announcerclock.presentation.fragments.tts_fragment.viewmodel.TtsViewModel";

      static String com_dzo_announcerclock_presentation_fragments_repeat_option_viewmodel_RepeatOptionViewModel = "com.dzo.announcerclock.presentation.fragments.repeat_option.viewmodel.RepeatOptionViewModel";

      @KeepFieldType
      TimerViewModel com_dzo_announcerclock_presentation_fragments_home_fragment_viewmodel_TimerViewModel2;

      @KeepFieldType
      ScheduleTimerViewModel com_dzo_announcerclock_presentation_fragments_home_fragment_viewmodel_ScheduleTimerViewModel2;

      @KeepFieldType
      SoundOptionViewModel com_dzo_announcerclock_presentation_fragments_sound_fragment_viewmodel_SoundOptionViewModel2;

      @KeepFieldType
      TtsViewModel com_dzo_announcerclock_presentation_fragments_tts_fragment_viewmodel_TtsViewModel2;

      @KeepFieldType
      RepeatOptionViewModel com_dzo_announcerclock_presentation_fragments_repeat_option_viewmodel_RepeatOptionViewModel2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.dzo.announcerclock.presentation.fragments.repeat_option.viewmodel.RepeatOptionViewModel 
          return (T) new RepeatOptionViewModel(viewModelCImpl.getRepeatOptionUseCase());

          case 1: // com.dzo.announcerclock.presentation.fragments.home_fragment.viewmodel.ScheduleTimerViewModel 
          return (T) new ScheduleTimerViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 2: // com.dzo.announcerclock.presentation.fragments.sound_fragment.viewmodel.SoundOptionViewModel 
          return (T) new SoundOptionViewModel(viewModelCImpl.getSoundOptionsUseCase(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 3: // com.dzo.announcerclock.presentation.fragments.home_fragment.viewmodel.TimerViewModel 
          return (T) new TimerViewModel(viewModelCImpl.announceTimeUseCase(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 4: // com.dzo.announcerclock.presentation.fragments.tts_fragment.viewmodel.TtsViewModel 
          return (T) new TtsViewModel(ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonCImpl.applicationContextModule), viewModelCImpl.getTtsSettingsUseCase(), viewModelCImpl.saveTtsSettingsUseCase(), viewModelCImpl.isTimeSpeakingEnabledUseCase(), viewModelCImpl.saveTimeSpeakingEnabledUseCase(), viewModelCImpl.saveDisableDuringPhoneCallsUseCase(), viewModelCImpl.isDisableDuringPhoneCallsUseCase(), viewModelCImpl.saveDisableWhilePlayingMusicUseCase(), viewModelCImpl.isDisableWhilePlayingMusicUseCase());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends App_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends App_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }

    private AnnounceTimeUseCase announceTimeUseCase() {
      return new AnnounceTimeUseCase(singletonCImpl.provideTimerRepositoryProvider.get());
    }

    @Override
    public void injectScheduleTimerService(ScheduleTimerService scheduleTimerService) {
      injectScheduleTimerService2(scheduleTimerService);
    }

    @Override
    public void injectTimerService(TimerService timerService) {
      injectTimerService2(timerService);
    }

    @CanIgnoreReturnValue
    private ScheduleTimerService injectScheduleTimerService2(ScheduleTimerService instance) {
      ScheduleTimerService_MembersInjector.injectAnnounceTimeUseCase(instance, announceTimeUseCase());
      return instance;
    }

    @CanIgnoreReturnValue
    private TimerService injectTimerService2(TimerService instance) {
      TimerService_MembersInjector.injectAnnounceTimeUseCase(instance, announceTimeUseCase());
      return instance;
    }
  }

  private static final class SingletonCImpl extends App_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<TimerRepository> provideTimerRepositoryProvider;

    private Provider<OtherAppAdapter> provideOurOtherAppAdapterProvider;

    private Provider<RepeatOptionAdapter> provideRepeatOptionAdapterProvider;

    private Provider<SoundOptionAdapter> provideSoundOptionAdapterProvider;

    private Provider<RepeatOptionRepository> provideRepeatOptionRepositoryProvider;

    private Provider<SoundOptionRepository> provideSoundOptionRepositoryProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideTimerRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<TimerRepository>(singletonCImpl, 0));
      this.provideOurOtherAppAdapterProvider = DoubleCheck.provider(new SwitchingProvider<OtherAppAdapter>(singletonCImpl, 1));
      this.provideRepeatOptionAdapterProvider = DoubleCheck.provider(new SwitchingProvider<RepeatOptionAdapter>(singletonCImpl, 2));
      this.provideSoundOptionAdapterProvider = DoubleCheck.provider(new SwitchingProvider<SoundOptionAdapter>(singletonCImpl, 3));
      this.provideRepeatOptionRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<RepeatOptionRepository>(singletonCImpl, 4));
      this.provideSoundOptionRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<SoundOptionRepository>(singletonCImpl, 5));
    }

    @Override
    public void injectApp(App app) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.dzo.announcerclock.data.timer_repository.TimerRepository 
          return (T) AppModule_ProvideTimerRepositoryFactory.provideTimerRepository();

          case 1: // com.dzo.announcerclock.presentation.fragments.our_app_fragment.adapter.OtherAppAdapter 
          return (T) AdapterModule_ProvideOurOtherAppAdapterFactory.provideOurOtherAppAdapter();

          case 2: // com.dzo.announcerclock.presentation.fragments.repeat_option.adapter.RepeatOptionAdapter 
          return (T) AdapterModule_ProvideRepeatOptionAdapterFactory.provideRepeatOptionAdapter();

          case 3: // com.dzo.announcerclock.presentation.fragments.sound_fragment.adapter.SoundOptionAdapter 
          return (T) AdapterModule_ProvideSoundOptionAdapterFactory.provideSoundOptionAdapter();

          case 4: // com.dzo.announcerclock.data.repeat_repository.RepeatOptionRepository 
          return (T) AppModule_ProvideRepeatOptionRepositoryFactory.provideRepeatOptionRepository();

          case 5: // com.dzo.announcerclock.data.sound_repository.SoundOptionRepository 
          return (T) AppModule_ProvideSoundOptionRepositoryFactory.provideSoundOptionRepository(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
