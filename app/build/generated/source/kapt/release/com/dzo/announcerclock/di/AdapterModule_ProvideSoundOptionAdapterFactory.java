package com.dzo.announcerclock.di;

import com.dzo.announcerclock.presentation.fragments.sound_fragment.adapter.SoundOptionAdapter;
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
public final class AdapterModule_ProvideSoundOptionAdapterFactory implements Factory<SoundOptionAdapter> {
  @Override
  public SoundOptionAdapter get() {
    return provideSoundOptionAdapter();
  }

  public static AdapterModule_ProvideSoundOptionAdapterFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SoundOptionAdapter provideSoundOptionAdapter() {
    return Preconditions.checkNotNullFromProvides(AdapterModule.INSTANCE.provideSoundOptionAdapter());
  }

  private static final class InstanceHolder {
    private static final AdapterModule_ProvideSoundOptionAdapterFactory INSTANCE = new AdapterModule_ProvideSoundOptionAdapterFactory();
  }
}
