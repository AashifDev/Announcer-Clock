package com.dzo.announcerclock.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\nJ\"\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\nJ\u0012\u0010\u000e\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\nJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\nJ\u000e\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\nJ\u000e\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\nJ\u000e\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\nJ\u000e\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\nJ\u000e\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\nJ\u0010\u0010\u001a\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\nJ\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\n0\u001cJ\u001a\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\"J\u0010\u0010#\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\nJ\u0010\u0010$\u001a\u00020\n2\u0006\u0010%\u001a\u00020\u0004H\u0002J\u0018\u0010&\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\"J\u0018\u0010\'\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010(\u001a\u00020\nJ\u000e\u0010)\u001a\u00020\n2\u0006\u0010*\u001a\u00020+J\u0016\u0010,\u001a\u00020-2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010.\u001a\u00020\nJ\u0010\u0010/\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\nJ\u001a\u00100\u001a\u00020-2\b\u00101\u001a\u0004\u0018\u0001022\b\u00103\u001a\u0004\u0018\u00010\nJ\u000e\u00104\u001a\u00020+2\u0006\u0010\u0010\u001a\u00020\nJ\u001a\u00105\u001a\u00020-2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u00103\u001a\u0004\u0018\u00010\nJ\u0012\u00106\u001a\u000207*\u00020 2\u0006\u0010\u0003\u001a\u000207J\u0014\u00108\u001a\u00020\u0004*\u00020\n2\b\b\u0002\u00109\u001a\u000207J\n\u0010:\u001a\u00020\u0004*\u00020 J\n\u0010;\u001a\u00020-*\u000202J\u0014\u0010<\u001a\u00020\u0004*\u00020\n2\b\b\u0002\u00109\u001a\u000207J\u0012\u0010=\u001a\u00020-*\u00020>2\u0006\u0010?\u001a\u00020\nJ\u001a\u0010@\u001a\u00020-*\u00020A2\u0006\u0010B\u001a\u00020\u00042\u0006\u0010C\u001a\u00020\u0004J\n\u0010D\u001a\u00020-*\u000202J\u0012\u0010E\u001a\u00020-*\u00020>2\u0006\u0010F\u001a\u00020\u0004R\u0015\u0010\u0003\u001a\u00020\u0004*\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0015\u0010\u0007\u001a\u00020\u0004*\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\u0006\u00a8\u0006G"}, d2 = {"Lcom/dzo/announcerclock/utils/Utils;", "", "()V", "dp", "", "getDp", "(I)I", "px", "getPx", "changeDateFormat", "", "date", "patternGiven", "patternRequired", "changeDateFormatForEvents", "changeTimeFormat", "time", "convertTime12HrTo24Hr", "convertTime24HrTo12Hr", "convertTime24HrTo12HrWithIST", "convertTo12HourFormat", "dateTime", "calendar", "Ljava/util/Calendar;", "desiredDateFormat", "input", "getDateFromCalender", "getHadithName", "", "getImageUriFromBitmap", "Landroid/net/Uri;", "context", "Landroid/content/Context;", "bitmap", "Landroid/graphics/Bitmap;", "getTimeFromCalender", "getURLForResource", "resourceId", "getUriFromFile", "loadJsonFromAssets", "fileName", "millisToTime", "millis", "", "openPlayStore", "", "packageName", "sdm", "showSnackBar", "view", "Landroid/view/View;", "msg", "timeToMillis", "toast", "convertDpToPixel", "", "darken", "factor", "getScreenWidth", "gone", "lighten", "loadImage", "Landroid/widget/ImageView;", "imageUrl", "setShowSideItems", "Landroidx/viewpager2/widget/ViewPager2;", "pageMarginPx", "offsetPx", "show", "showImage", "image", "app_release"})
public final class Utils {
    @org.jetbrains.annotations.NotNull()
    public static final com.dzo.announcerclock.utils.Utils INSTANCE = null;
    
    private Utils() {
        super();
    }
    
    public final void toast(@org.jetbrains.annotations.Nullable()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String msg) {
    }
    
    public final void showSnackBar(@org.jetbrains.annotations.Nullable()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    java.lang.String msg) {
    }
    
    public final int lighten(@org.jetbrains.annotations.NotNull()
    java.lang.String $this$lighten, float factor) {
        return 0;
    }
    
    public final int darken(@org.jetbrains.annotations.NotNull()
    java.lang.String $this$darken, float factor) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getHadithName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String changeDateFormat(@org.jetbrains.annotations.Nullable()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    java.lang.String patternGiven, @org.jetbrains.annotations.NotNull()
    java.lang.String patternRequired) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String changeDateFormat(@org.jetbrains.annotations.Nullable()
    java.lang.String date) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String changeDateFormatForEvents(@org.jetbrains.annotations.Nullable()
    java.lang.String date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String desiredDateFormat(@org.jetbrains.annotations.NotNull()
    java.lang.String input) {
        return null;
    }
    
    public final long timeToMillis(@org.jetbrains.annotations.NotNull()
    java.lang.String time) {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String millisToTime(long millis) {
        return null;
    }
    
    public final void openPlayStore(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String changeTimeFormat(@org.jetbrains.annotations.Nullable()
    java.lang.String time) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String dateTime(@org.jetbrains.annotations.NotNull()
    java.util.Calendar calendar) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTimeFromCalender(@org.jetbrains.annotations.Nullable()
    java.lang.String date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDateFromCalender(@org.jetbrains.annotations.Nullable()
    java.lang.String date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String sdm(@org.jetbrains.annotations.Nullable()
    java.lang.String date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.net.Uri getImageUriFromBitmap(@org.jetbrains.annotations.Nullable()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.graphics.Bitmap bitmap) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.net.Uri getUriFromFile(@org.jetbrains.annotations.Nullable()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap) {
        return null;
    }
    
    public final void loadImage(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView $this$loadImage, @org.jetbrains.annotations.NotNull()
    java.lang.String imageUrl) {
    }
    
    public final void showImage(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView $this$showImage, int image) {
    }
    
    public final void show(@org.jetbrains.annotations.NotNull()
    android.view.View $this$show) {
    }
    
    public final void gone(@org.jetbrains.annotations.NotNull()
    android.view.View $this$gone) {
    }
    
    public final int getScreenWidth(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$getScreenWidth) {
        return 0;
    }
    
    public final float convertDpToPixel(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$convertDpToPixel, float dp) {
        return 0.0F;
    }
    
    private final java.lang.String getURLForResource(int resourceId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String convertTime24HrTo12Hr(@org.jetbrains.annotations.NotNull()
    java.lang.String time) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String convertTo12HourFormat(@org.jetbrains.annotations.NotNull()
    java.lang.String time) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String convertTime24HrTo12HrWithIST(@org.jetbrains.annotations.NotNull()
    java.lang.String time) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String convertTime12HrTo24Hr(@org.jetbrains.annotations.NotNull()
    java.lang.String time) {
        return null;
    }
    
    public final void setShowSideItems(@org.jetbrains.annotations.NotNull()
    androidx.viewpager2.widget.ViewPager2 $this$setShowSideItems, int pageMarginPx, int offsetPx) {
    }
    
    public final int getDp(int $this$dp) {
        return 0;
    }
    
    public final int getPx(int $this$px) {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String loadJsonFromAssets(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String fileName) {
        return null;
    }
}