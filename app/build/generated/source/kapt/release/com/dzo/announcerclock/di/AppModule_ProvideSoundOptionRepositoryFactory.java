package com.dzo.announcerclock.di;

import android.content.Context;
import com.dzo.announcerclock.data.sound_repository.SoundOptionRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AppModule_ProvideSoundOptionRepositoryFactory implements Factory<SoundOptionRepository> {
  private final Provider<Context> contextProvider;

  public AppModule_ProvideSoundOptionRepositoryFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SoundOptionRepository get() {
    return provideSoundOptionRepository(contextProvider.get());
  }

  public static AppModule_ProvideSoundOptionRepositoryFactory create(
      Provider<Context> contextProvider) {
    return new AppModule_ProvideSoundOptionRepositoryFactory(contextProvider);
  }

  public static SoundOptionRepository provideSoundOptionRepository(Context context) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideSoundOptionRepository(context));
  }
}
