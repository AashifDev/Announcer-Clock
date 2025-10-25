package com.dzo.announcerclock.utils.extension;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\"\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006\u001a?\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\n\u001aU\u0010\u000b\u001a\u00020\u0001*\u00020\f2\u0006\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\t\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00042\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0010H\u0007\u00a2\u0006\u0002\u0010\u0011\u00a8\u0006\u0012"}, d2 = {"showColoredToast", "", "Landroid/content/Context;", "message", "", "bgColor", "", "textColor", "iconRes", "duration", "(Landroid/content/Context;Ljava/lang/String;IILjava/lang/Integer;Ljava/lang/Integer;)V", "showCustomSnackBar", "Landroid/app/Activity;", "actionText", "colorString", "onActionClick", "Lkotlin/Function0;", "(Landroid/app/Activity;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;ILjava/lang/String;Lkotlin/jvm/functions/Function0;)V", "app_debug"})
public final class ExtensionKt {
    
    @android.annotation.SuppressLint(value = {"RestrictedApi"})
    public static final void showCustomSnackBar(@org.jetbrains.annotations.NotNull()
    android.app.Activity $this$showCustomSnackBar, @org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.Nullable()
    java.lang.Integer iconRes, @org.jetbrains.annotations.Nullable()
    java.lang.String actionText, int duration, @org.jetbrains.annotations.NotNull()
    java.lang.String colorString, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onActionClick) {
    }
    
    public static final void showColoredToast(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$showColoredToast, @org.jetbrains.annotations.NotNull()
    java.lang.String message, int bgColor, int textColor, @org.jetbrains.annotations.Nullable()
    java.lang.Integer iconRes, @org.jetbrains.annotations.Nullable()
    java.lang.Integer duration) {
    }
    
    public static final void showColoredToast(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$showColoredToast, @org.jetbrains.annotations.NotNull()
    java.lang.String message, int bgColor, int textColor) {
    }
}