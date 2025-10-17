package com.dzo.announcerclock

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    init {
        instance = this
    }

    companion object{
        var instance: App? = null

        fun appContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

    }
}