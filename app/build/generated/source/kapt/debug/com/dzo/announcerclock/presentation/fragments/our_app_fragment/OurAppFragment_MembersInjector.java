package com.dzo.announcerclock.presentation.fragments.our_app_fragment;

import com.dzo.announcerclock.presentation.fragments.our_app_fragment.adapter.OtherAppAdapter;
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
public final class OurAppFragment_MembersInjector implements MembersInjector<OurAppFragment> {
  private final Provider<OtherAppAdapter> ourAppAdapterProvider;

  public OurAppFragment_MembersInjector(Provider<OtherAppAdapter> ourAppAdapterProvider) {
    this.ourAppAdapterProvider = ourAppAdapterProvider;
  }

  public static MembersInjector<OurAppFragment> create(
      Provider<OtherAppAdapter> ourAppAdapterProvider) {
    return new OurAppFragment_MembersInjector(ourAppAdapterProvider);
  }

  @Override
  public void injectMembers(OurAppFragment instance) {
    injectOurAppAdapter(instance, ourAppAdapterProvider.get());
  }

  @InjectedFieldSignature("com.dzo.announcerclock.presentation.fragments.our_app_fragment.OurAppFragment.ourAppAdapter")
  public static void injectOurAppAdapter(OurAppFragment instance, OtherAppAdapter ourAppAdapter) {
    instance.ourAppAdapter = ourAppAdapter;
  }
}
