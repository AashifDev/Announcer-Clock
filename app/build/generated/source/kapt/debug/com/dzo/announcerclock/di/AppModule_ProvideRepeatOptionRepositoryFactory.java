package com.dzo.announcerclock.di;

import com.dzo.announcerclock.data.repeat_repository.RepeatOptionRepository;
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
public final class AppModule_ProvideRepeatOptionRepositoryFactory implements Factory<RepeatOptionRepository> {
  @Override
  public RepeatOptionRepository get() {
    return provideRepeatOptionRepository();
  }

  public static AppModule_ProvideRepeatOptionRepositoryFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static RepeatOptionRepository provideRepeatOptionRepository() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideRepeatOptionRepository());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideRepeatOptionRepositoryFactory INSTANCE = new AppModule_ProvideRepeatOptionRepositoryFactory();
  }
}
