package com.dzo.announcerclock.presentation.fragments.sound_fragment;

import com.dzo.announcerclock.presentation.fragments.sound_fragment.adapter.SoundOptionAdapter;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class NotificationSoundFragment_MembersInjector implements MembersInjector<NotificationSoundFragment> {
  private final Provider<SoundOptionAdapter> soundOptionAdapterProvider;

  public NotificationSoundFragment_MembersInjector(
      Provider<SoundOptionAdapter> soundOptionAdapterProvider) {
    this.soundOptionAdapterProvider = soundOptionAdapterProvider;
  }

  public static MembersInjector<NotificationSoundFragment> create(
      Provider<SoundOptionAdapter> soundOptionAdapterProvider) {
    return new NotificationSoundFragment_MembersInjector(soundOptionAdapterProvider);
  }

  @Override
  public void injectMembers(NotificationSoundFragment instance) {
    injectSoundOptionAdapter(instance, soundOptionAdapterProvider.get());
  }

  @InjectedFieldSignature("com.dzo.announcerclock.presentation.fragments.sound_fragment.NotificationSoundFragment.soundOptionAdapter")
  public static void injectSoundOptionAdapter(NotificationSoundFragment instance,
      SoundOptionAdapter soundOptionAdapter) {
    instance.soundOptionAdapter = soundOptionAdapter;
  }
}
