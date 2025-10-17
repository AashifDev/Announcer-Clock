package com.dzo.announcerclock.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.dzo.announcerclock.App

object PreferenceHelper {

    private val prefs: SharedPreferences by lazy {
        App.appContext().getSharedPreferences(
            "talk_time_app_preferences",
            Context.MODE_PRIVATE
        )
    }

    fun getInt(key: String, defaultValue: Int): Int =
        prefs.getInt(key, defaultValue)

    fun getLong(key: String, defaultValue: Long): Long =
        prefs.getLong(key, defaultValue)

    fun putInt(key: String, value: Int) {
        prefs.edit { putInt(key, value) }
    }

    fun putLong(key: String, value: Long) {
        prefs.edit { putLong(key, value) }
    }

    fun getString(key: String, defaultValue: String): String? =
        prefs.getString(key, defaultValue)

    fun putString(key: String, value: String) {
        prefs.edit { putString(key, value) }
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean =
        prefs.getBoolean(key, defaultValue)

    fun putBoolean(key: String, value: Boolean) {
        prefs.edit { putBoolean(key, value) }
    }

    fun remove(key: String) {
        prefs.edit { remove(key) }
    }

    fun clear() {
        prefs.edit { clear() }
    }
}
