package com.dzo.announcerclock.presentation.fragments.our_app_fragment.adapter;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("dagger.hilt.android.scopes.FragmentScoped")
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
public final class OtherAppAdapter_Factory implements Factory<OtherAppAdapter> {
  private final Provider<Context> contextProvider;

  public OtherAppAdapter_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public OtherAppAdapter get() {
    return newInstance(contextProvider.get());
  }

  public static OtherAppAdapter_Factory create(Provider<Context> contextProvider) {
    return new OtherAppAdapter_Factory(contextProvider);
  }

  public static OtherAppAdapter newInstance(Context context) {
    return new OtherAppAdapter(context);
  }
}
