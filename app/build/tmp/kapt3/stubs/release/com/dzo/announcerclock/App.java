package com.dzo.announcerclock;

@dagger.hilt.android.HiltAndroidApp()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016\u00a8\u0006\u0006"}, d2 = {"Lcom/dzo/announcerclock/App;", "Landroid/app/Application;", "()V", "onCreate", "", "Companion", "app_release"})
public final class App extends android.app.Application {
    @org.jetbrains.annotations.Nullable()
    private static com.dzo.announcerclock.App instance;
    @org.jetbrains.annotations.NotNull()
    public static final com.dzo.announcerclock.App.Companion Companion = null;
    
    public App() {
        super();
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u000b"}, d2 = {"Lcom/dzo/announcerclock/App$Companion;", "", "()V", "instance", "Lcom/dzo/announcerclock/App;", "getInstance", "()Lcom/dzo/announcerclock/App;", "setInstance", "(Lcom/dzo/announcerclock/App;)V", "appContext", "Landroid/content/Context;", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.dzo.announcerclock.App getInstance() {
            return null;
        }
        
        public final void setInstance(@org.jetbrains.annotations.Nullable()
        com.dzo.announcerclock.App p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.content.Context appContext() {
            return null;
        }
    }
}