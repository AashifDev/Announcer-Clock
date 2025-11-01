package com.dzo.announcerclock.data.local_source

import android.net.Uri
import com.dzo.announcerclock.presentation.fragments.home_fragment.model.ScheduleTimerModel
import com.dzo.announcerclock.presentation.fragments.home_fragment.model.TtsSettings
import com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption
import com.dzo.announcerclock.presentation.fragments.sound_fragment.model.SoundOption
import com.dzo.announcerclock.utils.Constants
import com.dzo.announcerclock.utils.Constants.IS_ENABLE_DURING_PHONE_CALLS
import com.dzo.announcerclock.utils.Constants.IS_DISABLE_WHILE_PLAYING_MUSIC
import com.dzo.announcerclock.utils.Constants.IS_FIRST_LAUNCH
import com.dzo.announcerclock.utils.Constants.IS_HIDE_NOTIFICATION_ENABLED
import com.dzo.announcerclock.utils.Constants.IS_NOTIFICATION_ENABLED
import com.dzo.announcerclock.utils.Constants.IS_NOTIFICATION_SOUND_ENABLED
import com.dzo.announcerclock.utils.Constants.IS_TIME_SPEAKING_ENABLED
import com.dzo.announcerclock.utils.Constants.KEY_CUSTOM_TOGGLE_STATE
import com.dzo.announcerclock.utils.Constants.KEY_INTERVAL
import com.dzo.announcerclock.utils.Constants.KEY_REPEAT_OPTION
import com.dzo.announcerclock.utils.Constants.KEY_SCHEDULE_TIME
import com.dzo.announcerclock.utils.Constants.KEY_SOUND_OPTION
import com.dzo.announcerclock.utils.Constants.KEY_START_TIME
import com.dzo.announcerclock.utils.Constants.KEY_TOGGLE_COUNT
import com.dzo.announcerclock.utils.Constants.KEY_TOGGLE_STATE
import com.dzo.announcerclock.utils.Constants.KEY_TTS_SETTINGS
import com.dzo.announcerclock.utils.helper.PreferenceHelper
import com.dzo.announcerclock.utils.helper.PreferenceLiveDataManager
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.core.net.toUri
import com.google.gson.reflect.TypeToken

object AppPreferences {

    fun saveFirstLaunch(firstLaunch: Boolean) {
        PreferenceHelper.putBoolean(IS_FIRST_LAUNCH, firstLaunch)

    }
    fun isFirstLaunch(): Boolean? {
        return PreferenceHelper.getBoolean(IS_FIRST_LAUNCH,false)
    }

    fun saveRepeatOption(repeatOption: RepeatOption) {
        PreferenceHelper.putString(KEY_REPEAT_OPTION, Gson().toJson(repeatOption))
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


    /**
     * fun saveSoundOption(option: SoundOption?) {
     *         try {
     *             // Convert URI to string before saving
     *             val safeOption = option!!.copy(uri = option.uri?.toString()?.let { it.toUri() })
     *             val json = gson.toJson(
     *                 mapOf(
     *                     "id" to safeOption.id,
     *                     "title" to safeOption.title,
     *                     "soundResId" to safeOption.soundResId,
     *                     "uri" to safeOption.uri?.toString(),
     *                     "isSelected" to safeOption.isSelected
     *                 )
     *             )
     *             PreferenceHelper.putString(KEY_SOUND_OPTION, json)
     *         } catch (e: Exception) {
     *             e.printStackTrace()
     *         }
     *     }
     *
     *     fun getSoundOption(): SoundOption? {
     *         val json = PreferenceHelper.getString(KEY_SOUND_OPTION, "") ?: return null
     *         return try {
     *             val map = gson.fromJson(json, Map::class.java)
     *             SoundOption(
     *                 id = (map["id"] as? Double)?.toInt(),
     *                 title = map["title"] as? String ?: "",
     *                 soundResId = (map["soundResId"] as? Double)?.toInt(),
     *                 uri = (map["uri"] as? String)?.let { it.toUri() },
     *                 isSelected = map["isSelected"] as? Boolean ?: false,
     *                 isUserAdded = true
     *             )
     *         } catch (e: JsonSyntaxException) {
     *             Log.e("AppPreferences", "Failed to parse SoundOption", e)
     *             null
     *         }
     *     }
     */


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

    fun isHideNotificationEnabled(): Boolean? {
        return PreferenceHelper.getBoolean(IS_HIDE_NOTIFICATION_ENABLED,true)
    }

    fun saveEnableDuringPhoneCalls(disable: Boolean) {
        PreferenceHelper.putBoolean(IS_ENABLE_DURING_PHONE_CALLS, disable)

    }
    fun isEnableDuringPhoneCalls(): Boolean? {
        return PreferenceHelper.getBoolean(IS_ENABLE_DURING_PHONE_CALLS,false)
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
        PreferenceHelper.putString(KEY_SCHEDULE_TIME, Gson().toJson(schedule))
    }
    fun getScheduleTime(): ScheduleTimerModel? {
        return PreferenceHelper.getString(KEY_SCHEDULE_TIME, "")?.let {
            Gson().fromJson(it, ScheduleTimerModel::class.java)
        }
    }

    fun getToggleCount(): Int {
        return PreferenceHelper.getInt(KEY_TOGGLE_COUNT, 0)
    }

    fun incrementToggleCount() {
        val current = getToggleCount()
        PreferenceHelper.putInt(KEY_TOGGLE_COUNT, current + 1)
    }

    fun resetToggleCount() {
        PreferenceHelper.putInt(KEY_TOGGLE_COUNT, 0)
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
fun Uri?.toPersistableString(): String? = this?.toString()
fun String?.toUriOrNull(): Uri? = this?.let { it.toUri() }

val AppPreferences.scheduleTimeFlow: Flow<ScheduleTimerModel?>
    get() = PreferenceLiveDataManager.observe(KEY_SCHEDULE_TIME, "")
        .map { json ->
            if (json.isNotEmpty()) Gson().fromJson(json, ScheduleTimerModel::class.java)
            else null
        }

val isScheduleTimeExist : Flow<Boolean>
    get() = PreferenceLiveDataManager.observe(KEY_CUSTOM_TOGGLE_STATE,false)

