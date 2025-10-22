package com.dzo.announcerclock.presentation.fragments.repeat_option.adapter;

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
public final class RepeatOptionAdapter_Factory implements Factory<RepeatOptionAdapter> {
  @Override
  public RepeatOptionAdapter get() {
    return newInstance();
  }

  public static RepeatOptionAdapter_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static RepeatOptionAdapter newInstance() {
    return new RepeatOptionAdapter();
  }

  private static final class InstanceHolder {
    private static final RepeatOptionAdapter_Factory INSTANCE = new RepeatOptionAdapter_Factory();
  }
}
