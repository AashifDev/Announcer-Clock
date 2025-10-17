package com.dzo.announcerclock.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\fJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0011J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0013J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eJ\u0016\u0010\u0015\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\fJ\u0016\u0010\u0017\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0011J\u0016\u0010\u0018\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0013J\u0016\u0010\u0019\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000eJ\u000e\u0010\u001a\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eR\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u001b"}, d2 = {"Lcom/dzo/announcerclock/utils/PreferenceHelper;", "", "()V", "prefs", "Landroid/content/SharedPreferences;", "getPrefs", "()Landroid/content/SharedPreferences;", "prefs$delegate", "Lkotlin/Lazy;", "clear", "", "getBoolean", "", "key", "", "defaultValue", "getInt", "", "getLong", "", "getString", "putBoolean", "value", "putInt", "putLong", "putString", "remove", "app_debug"})
public final class PreferenceHelper {
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy prefs$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.dzo.announcerclock.utils.PreferenceHelper INSTANCE = null;
    
    private PreferenceHelper() {
        super();
    }
    
    private final android.content.SharedPreferences getPrefs() {
        return null;
    }
    
    public final int getInt(@org.jetbrains.annotations.NotNull()
    java.lang.String key, int defaultValue) {
        return 0;
    }
    
    public final long getLong(@org.jetbrains.annotations.NotNull()
    java.lang.String key, long defaultValue) {
        return 0L;
    }
    
    public final void putInt(@org.jetbrains.annotations.NotNull()
    java.lang.String key, int value) {
    }
    
    public final void putLong(@org.jetbrains.annotations.NotNull()
    java.lang.String key, long value) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getString(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.String defaultValue) {
        return null;
    }
    
    public final void putString(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final boolean getBoolean(@org.jetbrains.annotations.NotNull()
    java.lang.String key, boolean defaultValue) {
        return false;
    }
    
    public final void putBoolean(@org.jetbrains.annotations.NotNull()
    java.lang.String key, boolean value) {
    }
    
    public final void remove(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
    }
    
    public final void clear() {
    }
}