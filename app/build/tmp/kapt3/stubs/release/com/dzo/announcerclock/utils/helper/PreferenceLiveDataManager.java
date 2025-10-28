package com.dzo.announcerclock.utils.helper;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J#\u0010\u0007\u001a\u0002H\b\"\u0004\b\u0000\u0010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u0002H\bH\u0002\u00a2\u0006\u0002\u0010\fJ\'\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\b0\u000e\"\u0004\b\u0000\u0010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u0002H\b\u00a2\u0006\u0002\u0010\u000fR\u0014\u0010\u0003\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/dzo/announcerclock/utils/helper/PreferenceLiveDataManager;", "", "()V", "prefs", "Landroid/content/SharedPreferences;", "getPrefs", "()Landroid/content/SharedPreferences;", "getValue", "T", "key", "", "defaultValue", "(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "observe", "Lkotlinx/coroutines/flow/Flow;", "(Ljava/lang/String;Ljava/lang/Object;)Lkotlinx/coroutines/flow/Flow;", "app_release"})
public final class PreferenceLiveDataManager {
    @org.jetbrains.annotations.NotNull()
    public static final com.dzo.announcerclock.utils.helper.PreferenceLiveDataManager INSTANCE = null;
    
    private PreferenceLiveDataManager() {
        super();
    }
    
    private final android.content.SharedPreferences getPrefs() {
        return null;
    }
    
    /**
     * Generic observer for any SharedPreference key.
     * Returns Flow<T> that emits value whenever key changes.
     */
    @org.jetbrains.annotations.NotNull()
    public final <T extends java.lang.Object>kotlinx.coroutines.flow.Flow<T> observe(@org.jetbrains.annotations.NotNull()
    java.lang.String key, T defaultValue) {
        return null;
    }
    
    /**
     * Internal helper to return value type-safely
     */
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    private final <T extends java.lang.Object>T getValue(java.lang.String key, T defaultValue) {
        return null;
    }
}