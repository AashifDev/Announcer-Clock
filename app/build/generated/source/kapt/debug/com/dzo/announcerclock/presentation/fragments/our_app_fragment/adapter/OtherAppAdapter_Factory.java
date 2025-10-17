package com.dzo.announcerclock.presentation.fragments.our_app_fragment.adapter;

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
public final class OtherAppAdapter_Factory implements Factory<OtherAppAdapter> {
  @Override
  public OtherAppAdapter get() {
    return newInstance();
  }

  public static OtherAppAdapter_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static OtherAppAdapter newInstance() {
    return new OtherAppAdapter();
  }

  private static final class InstanceHolder {
    private static final OtherAppAdapter_Factory INSTANCE = new OtherAppAdapter_Factory();
  }
}
