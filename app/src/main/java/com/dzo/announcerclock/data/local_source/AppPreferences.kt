package com.dzo.announcerclock.data.local_source

import com.dzo.announcerclock.presentation.fragments.home_fragment.model.ScheduleTimerModel
import com.dzo.announcerclock.presentation.fragments.home_fragment.model.TtsSettings
import com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption
import com.dzo.announcerclock.presentation.fragments.sound_fragment.model.SoundOption
import com.dzo.announcerclock.utils.Constants
import com.dzo.announcerclock.utils.Constants.IS_DISABLE_DURING_PHONE_CALLS
import com.dzo.announcerclock.utils.Constants.IS_DISABLE_WHILE_PLAYING_MUSIC
import com.dzo.announcerclock.utils.Constants.IS_FIRST_LAUNCH
import com.dzo.announcerclock.utils.Constants.IS_HIDE_NOTIFICATION_ENABLED
import com.dzo.announcerclock.utils.Constants.IS_NOTIFICATION_ENABLED
import com.dzo.announcerclock.utils.Constants.IS_NOTIFICATION_SOUND_ENABLED
import com.dzo.announcerclock.utils.Constants.IS_TIME_SPEAKING_ENABLED
import com.dzo.announcerclock.utils.Constants.KEY_CUSTOM_TOGGLE_STATE
import com.dzo.announcerclock.utils.Constants.KEY_INTERVAL
import com.dzo.announcerclock.utils.Constants.KEY_REPEAT_OPTION
import com.dzo.announcerclock.utils.Constants.KEY_SOUND_OPTION
import com.dzo.announcerclock.utils.Constants.KEY_START_TIME
import com.dzo.announcerclock.utils.Constants.KEY_TOGGLE_STATE
import com.dzo.announcerclock.utils.Constants.KEY_TTS_SETTINGS
import com.dzo.announcerclock.utils.PreferenceHelper
import com.google.gson.Gson

object AppPreferences {

    fun saveFirstLaunch(firstLaunch: Boolean) {
        PreferenceHelper.putBoolean(IS_FIRST_LAUNCH, firstLaunch)

    }
    fun isFirstLaunch(): Boolean? {
        return PreferenceHelper.getBoolean(IS_FIRST_LAUNCH,false)
    }

    fun saveRepeatOption(repeatOption: RepeatOption) {
        PreferenceHelper.putString(Constants.KEY_REPEAT_OPTION, Gson().toJson(repeatOption))
    }

    fun getRepeatOption(): RepeatOption? {
        return PreferenceHelper.getString(KEY_REPEAT_OPTION, "")?.let {
            Gson().fromJson(it, RepeatOption::class.java)
        }
    }

    fun saveSoundOption(soundOption: SoundOption) {
        PreferenceHelper.putString(KEY_SOUND_OPTION, Gson().toJson(soundOption))
    }

    fun getSoundOption(): SoundOption? {
        return PreferenceHelper.getString(KEY_SOUND_OPTION, "")?.let {
            Gson().fromJson(it, SoundOption::class.java)
        }
    }

    fun saveStartTime(startTime: Long) {
        PreferenceHelper.putLong(KEY_START_TIME,startTime)
    }

    fun getStartTime(): Long = PreferenceHelper.getLong(KEY_START_TIME, 0L)

    fun saveInterval(intervalSeconds: Int) {
        PreferenceHelper.putInt(KEY_INTERVAL, intervalSeconds)
    }

    fun getInterval(): Int = PreferenceHelper.getInt(KEY_INTERVAL, 60)

    fun saveToggleState(state: Boolean) = PreferenceHelper.putBoolean(KEY_TOGGLE_STATE, state)

    fun getToggleState(): Boolean = PreferenceHelper.getBoolean(KEY_TOGGLE_STATE, false)

    fun saveCustomToggleState(state: Boolean) = PreferenceHelper.putBoolean(KEY_CUSTOM_TOGGLE_STATE, state)

    fun getCustomToggleState(): Boolean = PreferenceHelper.getBoolean(KEY_CUSTOM_TOGGLE_STATE, false)


    fun saveTtsSettings(settings: TtsSettings) {
        PreferenceHelper.putString(KEY_TTS_SETTINGS, Gson().toJson(settings))
    }

    fun getTtsSettings(): TtsSettings {
        return PreferenceHelper.getString(KEY_TTS_SETTINGS, "")?.let {
            Gson().fromJson(it, TtsSettings::class.java)
        } ?: TtsSettings()
    }

    /*fun getTime(): List<AlarmItemEntity> {
        val jsonString = PreferenceHelper.getString(TalkApp.instance, "time", "")
        if (jsonString!!.isEmpty()) {
            return emptyList()
        }
        val type = object : TypeToken<List<AlarmItemEntity>>() {}.type
        return Gson().fromJson(jsonString, type)
    }*/

    fun saveNotificationEnabled(enabled: Boolean) {
        PreferenceHelper.putBoolean(IS_NOTIFICATION_ENABLED, enabled)
    }

    fun isNotificationEnabled(): Boolean? {
        return PreferenceHelper.getBoolean(IS_NOTIFICATION_ENABLED,false)
    }
    fun saveNotificationSoundEnabled(enabled: Boolean) {
        PreferenceHelper.putBoolean(IS_NOTIFICATION_SOUND_ENABLED, enabled)
    }

    fun isNotificationSoundEnabled(): Boolean? {
        return PreferenceHelper.getBoolean(IS_NOTIFICATION_SOUND_ENABLED,false)
    }


    fun saveTimeSpeakingEnabled(enabled: Boolean) {
        PreferenceHelper.putBoolean(IS_TIME_SPEAKING_ENABLED, enabled)
    }

    fun isTimeSpeakingEnabled(): Boolean? {
        return PreferenceHelper.getBoolean(IS_TIME_SPEAKING_ENABLED,true)
    }

    fun saveHideNotificationEnabled(enabled: Boolean) {
        PreferenceHelper.putBoolean(IS_HIDE_NOTIFICATION_ENABLED, enabled)
    }

    fun isHindNotificationEnabled(): Boolean? {
        return PreferenceHelper.getBoolean(IS_HIDE_NOTIFICATION_ENABLED,true)
    }

    fun saveDisableDuringPhoneCalls(disable: Boolean) {
        PreferenceHelper.putBoolean(IS_DISABLE_DURING_PHONE_CALLS, disable)

    }
    fun isDisableDuringPhoneCalls(): Boolean? {
        return PreferenceHelper.getBoolean(IS_DISABLE_DURING_PHONE_CALLS,false)
    }

    fun saveDisableWhilePlayingMusic(disable: Boolean) {
        PreferenceHelper.putBoolean(IS_DISABLE_WHILE_PLAYING_MUSIC, disable)
    }
    fun isDisableWhilePlayingMusic(): Boolean? {
        return PreferenceHelper.getBoolean(IS_DISABLE_WHILE_PLAYING_MUSIC,false)
    }

    fun saveThemeColor(colorHex: String) {
        PreferenceHelper.putString(Constants.KEY_THEME_COLOR, colorHex)
    }

    fun getThemeColor(): String? {
        return PreferenceHelper.getString(Constants.KEY_THEME_COLOR,"")
    }

    fun saveDarkThemeEnabled(boolean: Boolean){
        PreferenceHelper.putBoolean(Constants.KEY_DARK_THEME_ENABLED,boolean)
    }
    fun isDarkThemeEnabled(): Boolean?{
        return PreferenceHelper.getBoolean(Constants.KEY_DARK_THEME_ENABLED,false)
    }

    fun saveScheduleTime(schedule: ScheduleTimerModel) {
        PreferenceHelper.putString(Constants.KEY_SCHEDULE_TIME, Gson().toJson(schedule))
    }
    fun getScheduleTime(): ScheduleTimerModel? {
        return PreferenceHelper.getString(Constants.KEY_SCHEDULE_TIME, "")?.let {
            Gson().fromJson(it, ScheduleTimerModel::class.java)
        }
    }
    object ThemeManager {
        private const val PREF_THEME_COLOR = "pref_theme_color" // Active color
        private const val PREF_THEME_COLOR_LIST = "pref_theme_color_list" // List of colors

        private val listeners = mutableListOf<(String) -> Unit>()

        fun setActiveThemeColor(colorHex: String) {
            PreferenceHelper.putString(PREF_THEME_COLOR, colorHex)
            notifyListeners(colorHex)
        }

        fun getActiveThemeColor(): String? {
            return PreferenceHelper.getString(PREF_THEME_COLOR, "#64b5f6") // default
        }

        fun setThemeColorList(colorList: String) {
            PreferenceHelper.putString(PREF_THEME_COLOR_LIST, colorList)
        }

        fun getThemeColorList(): String? {
            return PreferenceHelper.getString(PREF_THEME_COLOR_LIST, "")
        }

        fun registerListener(listener: (String) -> Unit) {
            if (!listeners.contains(listener)) listeners.add(listener)
            listener(getActiveThemeColor()!!)
        }

        fun notifyListeners(colorHex: String) {
            for (listener in listeners) {
                listener(colorHex)
            }
        }
    }


}