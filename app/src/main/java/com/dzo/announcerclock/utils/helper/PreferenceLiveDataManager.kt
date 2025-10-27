package com.dzo.announcerclock.utils.helper

import android.content.SharedPreferences
import com.dzo.announcerclock.App
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged

object PreferenceLiveDataManager {

    /*private val prefs: SharedPreferences
        get() = PreferenceHelper.prefs*/

    private val prefs: SharedPreferences
        get() = App.appContext().getSharedPreferences(
            "talk_time_app_preferences",
            android.content.Context.MODE_PRIVATE
        )

    /**
     * Generic observer for any SharedPreference key.
     * Returns Flow<T> that emits value whenever key changes.
     */
    fun <T> observe(key: String, defaultValue: T): Flow<T> = callbackFlow {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, changedKey ->
            if (changedKey == key) {
                trySend(getValue(key, defaultValue))
            }
        }

        // Register listener
        prefs.registerOnSharedPreferenceChangeListener(listener)

        // Emit initial value
        trySend(getValue(key, defaultValue))

        // Unregister on close
        awaitClose { prefs.unregisterOnSharedPreferenceChangeListener(listener) }
    }.distinctUntilChanged()

    /**
     * Internal helper to return value type-safely
     */
    @Suppress("UNCHECKED_CAST")
    private fun <T> getValue(key: String, defaultValue: T): T {
        return try {
            when (defaultValue) {
                is String -> prefs.getString(key, defaultValue) as T
                is Boolean -> {
                    val value = prefs.all[key]
                    when (value) {
                        is Boolean -> value as T
                        is String -> (value.toBooleanStrictOrNull() ?: defaultValue) as T
                        else -> defaultValue
                    }
                }
                is Int -> prefs.getInt(key, defaultValue) as T
                is Long -> prefs.getLong(key, defaultValue) as T
                is Float -> prefs.getFloat(key, defaultValue) as T
                else -> throw IllegalArgumentException("Unsupported type: ${defaultValue!!::class.java}")
            }
        } catch (e: Exception) {
            defaultValue
        }
    }

}
