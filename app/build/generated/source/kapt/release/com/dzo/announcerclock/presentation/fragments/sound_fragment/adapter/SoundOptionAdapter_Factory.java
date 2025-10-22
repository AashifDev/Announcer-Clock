package com.dzo.announcerclock.presentation.fragments.sound_fragment.adapter;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("dagger.hilt.android.scopes.FragmentScoped")
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
public final class SoundOptionAdapter_Factory implements Factory<SoundOptionAdapter> {
  @Override
  public SoundOptionAdapter get() {
    return newInstance();
  }

  public static SoundOptionAdapter_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SoundOptionAdapter newInstance() {
    return new SoundOptionAdapter();
  }

  private static final class InstanceHolder {
    private static final SoundOptionAdapter_Factory INSTANCE = new SoundOptionAdapter_Factory();
  }
}
