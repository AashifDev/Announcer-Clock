package com.dzo.announcerclock.data.local_source

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.dzo.announcerclock.App
import com.dzo.announcerclock.presentation.fragments.home_fragment.model.TtsSettings
import com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption
import com.dzo.announcerclock.presentation.fragments.sound_fragment.model.SoundOption
import com.google.gson.Gson
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore(name = "talk_time_app_prefs")
private val gson = Gson()

object PreferenceManager {
    private val appContext get() = App.appContext()

    private val KEY_TTS_SETTINGS = stringPreferencesKey("tts_settings")
    private val KEY_REPEAT_OPTION = stringPreferencesKey("repeat_option")
    private val KEY_SOUND_OPTION = stringPreferencesKey("sound_option")
    private val KEY_START_TIME = longPreferencesKey("start_time")
    private val KEY_INTERVAL = intPreferencesKey("time_interval")
    private val KEY_TOGGLE_STATE = booleanPreferencesKey("toggle_state")

    // ------------------- SAVE -------------------

    suspend fun saveRepeatOption(repeatOption: RepeatOption) {
        appContext.dataStore.edit { prefs ->
            prefs[KEY_REPEAT_OPTION] = gson.toJson(repeatOption)
        }
    }

    suspend fun saveSoundOption(soundOption: SoundOption) {
        appContext.dataStore.edit { prefs ->
            prefs[KEY_SOUND_OPTION] = gson.toJson(soundOption)
        }
    }

    suspend fun saveStartTime(startTime: Long) {
        appContext.dataStore.edit { prefs -> prefs[KEY_START_TIME] = startTime }
    }

    suspend fun saveInterval(intervalSeconds: Int) {
        appContext.dataStore.edit { prefs -> prefs[KEY_INTERVAL] = intervalSeconds }
    }

    suspend fun saveToggleState(state: Boolean) {
        appContext.dataStore.edit { prefs -> prefs[KEY_TOGGLE_STATE] = state }
    }

    suspend fun saveTtsSettings(settings: TtsSettings) {
        appContext.dataStore.edit { prefs -> prefs[KEY_TTS_SETTINGS] = gson.toJson(settings) }
    }

    // ------------------- GET -------------------

    suspend fun getRepeatOption(): RepeatOption? {
        val json = appContext.dataStore.data.first()[KEY_REPEAT_OPTION]
        return json?.let { gson.fromJson(it, RepeatOption::class.java) }
    }

    suspend fun getSoundOption(): SoundOption? {
        val json = appContext.dataStore.data.first()[KEY_SOUND_OPTION]
        return json?.let { gson.fromJson(it, SoundOption::class.java) }
    }

    suspend fun getStartTime(): Long = appContext.dataStore.data.first()[KEY_START_TIME] ?: 0L
    suspend fun getInterval(): Int = appContext.dataStore.data.first()[KEY_INTERVAL] ?: 60
    suspend fun getToggleState(): Boolean = appContext.dataStore.data.first()[KEY_TOGGLE_STATE] ?: false
    suspend fun getTtsSettings(): TtsSettings {
        val json = appContext.dataStore.data.first()[KEY_TTS_SETTINGS]
        return json?.let { gson.fromJson(it, TtsSettings::class.java) } ?: TtsSettings()
    }

    suspend fun clear() = appContext.dataStore.edit { it.clear() }
}
