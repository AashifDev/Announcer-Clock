package com.dzo.announcerclock.presentation.fragments.repeat_option;

import com.dzo.announcerclock.presentation.fragments.repeat_option.adapter.RepeatOptionAdapter;
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
public final class RepeatOptionFragment_MembersInjector implements MembersInjector<RepeatOptionFragment> {
  private final Provider<RepeatOptionAdapter> repeatOptionAdapterProvider;

  public RepeatOptionFragment_MembersInjector(
      Provider<RepeatOptionAdapter> repeatOptionAdapterProvider) {
    this.repeatOptionAdapterProvider = repeatOptionAdapterProvider;
  }

  public static MembersInjector<RepeatOptionFragment> create(
      Provider<RepeatOptionAdapter> repeatOptionAdapterProvider) {
    return new RepeatOptionFragment_MembersInjector(repeatOptionAdapterProvider);
  }

  @Override
  public void injectMembers(RepeatOptionFragment instance) {
    injectRepeatOptionAdapter(instance, repeatOptionAdapterProvider.get());
  }

  @InjectedFieldSignature("com.dzo.announcerclock.presentation.fragments.repeat_option.RepeatOptionFragment.repeatOptionAdapter")
  public static void injectRepeatOptionAdapter(RepeatOptionFragment instance,
      RepeatOptionAdapter repeatOptionAdapter) {
    instance.repeatOptionAdapter = repeatOptionAdapter;
  }
}
