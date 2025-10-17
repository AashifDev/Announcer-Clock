package com.dzo.announcerclock.data.local_source;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0016\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u000eJ\r\u0010\u000f\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\u0010J\r\u0010\u0011\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\u0010J\r\u0010\u0012\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\u0010J\r\u0010\u0013\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\u0010J\r\u0010\u0014\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\u0010J\r\u0010\u0015\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\u0010J\r\u0010\u0016\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\u0010J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\fJ\u000e\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\fJ\u000e\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\fJ\u000e\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\fJ\u000e\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020\u0004J\u000e\u0010!\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\fJ\u000e\u0010\"\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\fJ\u000e\u0010#\u001a\u00020\u00182\u0006\u0010$\u001a\u00020\u0006J\u000e\u0010%\u001a\u00020\u00182\u0006\u0010&\u001a\u00020\bJ\u000e\u0010\'\u001a\u00020\u00182\u0006\u0010(\u001a\u00020\nJ\u000e\u0010)\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\fJ\u000e\u0010*\u001a\u00020\u00182\u0006\u0010+\u001a\u00020\fJ\u000e\u0010,\u001a\u00020\u00182\u0006\u0010-\u001a\u00020\u000e\u00a8\u0006."}, d2 = {"Lcom/dzo/announcerclock/data/local_source/AppPreferences;", "", "()V", "getInterval", "", "getRepeatOption", "Lcom/dzo/announcerclock/presentation/fragments/repeat_option/model/RepeatOption;", "getSoundOption", "Lcom/dzo/announcerclock/presentation/fragments/sound_fragment/model/SoundOption;", "getStartTime", "", "getToggleState", "", "getTtsSettings", "Lcom/dzo/announcerclock/presentation/fragments/home_fragment/model/TtsSettings;", "isDisableDuringPhoneCalls", "()Ljava/lang/Boolean;", "isDisableWhilePlayingMusic", "isFirstLaunch", "isHindNotificationEnabled", "isNotificationEnabled", "isNotificationSoundEnabled", "isTimeSpeakingEnabled", "saveDisableDuringPhoneCalls", "", "disable", "saveDisableWhilePlayingMusic", "saveFirstLaunch", "firstLaunch", "saveHideNotificationEnabled", "enabled", "saveInterval", "intervalSeconds", "saveNotificationEnabled", "saveNotificationSoundEnabled", "saveRepeatOption", "repeatOption", "saveSoundOption", "soundOption", "saveStartTime", "startTime", "saveTimeSpeakingEnabled", "saveToggleState", "state", "saveTtsSettings", "settings", "app_debug"})
public final class AppPreferences {
    @org.jetbrains.annotations.NotNull()
    public static final com.dzo.announcerclock.data.local_source.AppPreferences INSTANCE = null;
    
    private AppPreferences() {
        super();
    }
    
    public final void saveFirstLaunch(boolean firstLaunch) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean isFirstLaunch() {
        return null;
    }
    
    public final void saveRepeatOption(@org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption repeatOption) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption getRepeatOption() {
        return null;
    }
    
    public final void saveSoundOption(@org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.presentation.fragments.sound_fragment.model.SoundOption soundOption) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.dzo.announcerclock.presentation.fragments.sound_fragment.model.SoundOption getSoundOption() {
        return null;
    }
    
    public final void saveStartTime(long startTime) {
    }
    
    public final long getStartTime() {
        return 0L;
    }
    
    public final void saveInterval(int intervalSeconds) {
    }
    
    public final int getInterval() {
        return 0;
    }
    
    public final void saveToggleState(boolean state) {
    }
    
    public final boolean getToggleState() {
        return false;
    }
    
    public final void saveTtsSettings(@org.jetbrains.annotations.NotNull()
    com.dzo.announcerclock.presentation.fragments.home_fragment.model.TtsSettings settings) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.dzo.announcerclock.presentation.fragments.home_fragment.model.TtsSettings getTtsSettings() {
        return null;
    }
    
    public final void saveNotificationEnabled(boolean enabled) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean isNotificationEnabled() {
        return null;
    }
    
    public final void saveNotificationSoundEnabled(boolean enabled) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean isNotificationSoundEnabled() {
        return null;
    }
    
    public final void saveTimeSpeakingEnabled(boolean enabled) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean isTimeSpeakingEnabled() {
        return null;
    }
    
    public final void saveHideNotificationEnabled(boolean enabled) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean isHindNotificationEnabled() {
        return null;
    }
    
    public final void saveDisableDuringPhoneCalls(boolean disable) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean isDisableDuringPhoneCalls() {
        return null;
    }
    
    public final void saveDisableWhilePlayingMusic(boolean disable) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean isDisableWhilePlayingMusic() {
        return null;
    }
}