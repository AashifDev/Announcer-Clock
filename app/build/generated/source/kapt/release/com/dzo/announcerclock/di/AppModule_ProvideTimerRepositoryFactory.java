package com.dzo.announcerclock.di;

import com.dzo.announcerclock.data.timer_repository.TimerRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class AppModule_ProvideTimerRepositoryFactory implements Factory<TimerRepository> {
  @Override
  public TimerRepository get() {
    return provideTimerRepository();
  }

  public static AppModule_ProvideTimerRepositoryFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static TimerRepository provideTimerRepository() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideTimerRepository());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideTimerRepositoryFactory INSTANCE = new AppModule_ProvideTimerRepositoryFactory();
  }
}
