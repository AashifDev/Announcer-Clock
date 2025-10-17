package com.dzo.announcerclock.presentation.fragments.tts_fragment.viewmodel

import android.app.Application
import android.speech.tts.TextToSpeech
import android.speech.tts.Voice
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dzo.announcerclock.domain.tts_usecase.GetTtsSettingsUseCase
import com.dzo.announcerclock.domain.tts_usecase.IsDisableDuringPhoneCallsUseCase
import com.dzo.announcerclock.domain.tts_usecase.IsDisableWhilePlayingMusicUseCase
import com.dzo.announcerclock.domain.tts_usecase.IsTimeSpeakingEnabledUseCase
import com.dzo.announcerclock.domain.tts_usecase.SaveDisableDuringPhoneCallsUseCase
import com.dzo.announcerclock.domain.tts_usecase.SaveDisableWhilePlayingMusicUseCase
import com.dzo.announcerclock.domain.tts_usecase.SaveTimeSpeakingEnabledUseCase
import com.dzo.announcerclock.domain.tts_usecase.SaveTtsSettingsUseCase
import com.dzo.announcerclock.presentation.fragments.home_fragment.model.TtsSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

data class TtsUiState(
    val settings: TtsSettings = TtsSettings(),
    val languages: List<Locale> = emptyList(),
    val voices: List<Voice> = emptyList(),
    val timeSpeakingEnabled: Boolean = true,
    val ttsReady: Boolean = false,
    val disableDuringPhoneCalls: Boolean = false,
    val disableWhilePlayingMusic: Boolean = false
)

@HiltViewModel
class TtsViewModel @Inject constructor(
    application: Application,
    private val getTtsSettings: GetTtsSettingsUseCase,
    private val saveTtsSettings: SaveTtsSettingsUseCase,
    private val isTimeSpeakingEnabled: IsTimeSpeakingEnabledUseCase,
    private val saveTimeSpeakingEnabled: SaveTimeSpeakingEnabledUseCase,
    private val saveDisableDuringPhoneCalls: SaveDisableDuringPhoneCallsUseCase,
    private val isDisableDuringPhoneCalls: IsDisableDuringPhoneCallsUseCase,
    private val saveDisableWhilePlayingMusic: SaveDisableWhilePlayingMusicUseCase,
    private val isDisableWhilePlayingMusic: IsDisableWhilePlayingMusicUseCase
) : AndroidViewModel(application), TextToSpeech.OnInitListener {

    private val _state = MutableStateFlow(TtsUiState())
    val state: StateFlow<TtsUiState> = _state.asStateFlow()

    private var tts: TextToSpeech? = null

    init {
        tts = TextToSpeech(application, this)
        viewModelScope.launch {
            // load saved settings and time toggle from use-cases (session)
            val settings = getTtsSettings()
            val timeEnabled = isTimeSpeakingEnabled()
            val disableDuringPhoneCalls = isDisableDuringPhoneCalls()
            val disableWhilePlayingMusic = isDisableWhilePlayingMusic()
            _state.update {
                it.copy(
                    settings = settings,
                    timeSpeakingEnabled = timeEnabled,
                    disableDuringPhoneCalls = disableDuringPhoneCalls,
                    disableWhilePlayingMusic = disableWhilePlayingMusic

                )
            }
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            _state.update { it.copy(ttsReady = true) }
            loadLanguages()
        }
    }

    /*private fun loadLanguages() {
        viewModelScope.launch {
            val locales = try {
                val method = TextToSpeech::class.java.getMethod("getAvailableLanguages")
                (method.invoke(tts) as? Set<Locale>) ?: Locale.getAvailableLocales().toSet()
            } catch (e: Exception) {
                Locale.getAvailableLocales().toSet()
            }

            val filtered = locales
                .groupBy { it.language }
                .map { entry ->
                    entry.value.find { it.country.isNotEmpty() } ?: entry.value.first()
                }
                .sortedBy { it.displayLanguage }

            _state.update { it.copy(languages = filtered) }

            // After languages are loaded, load voices for saved language (if any)
            val savedLang = _state.value.settings.language
            val localeParts = savedLang.split("_")
            val savedLocale = if (localeParts.size == 2) Locale(
                localeParts[0],
                localeParts[1]
            ) else Locale.getDefault()
            // use init=true so we don't re-save unnecessary intermediate states
            selectLanguage(savedLocale, init = true)
        }
    }*/

    private fun loadLanguages() {
        viewModelScope.launch {
            val locales = try {
                val method = TextToSpeech::class.java.getMethod("getAvailableLanguages")
                (method.invoke(tts) as? Set<Locale>) ?: Locale.getAvailableLocales().toSet()
            } catch (e: Exception) {
                Locale.getAvailableLocales().toSet()
            }

            val filtered = locales
                .groupBy { it.language }
                .map { entry ->
                    entry.value.find { it.country.isNotEmpty() } ?: entry.value.first()
                }
                .sortedBy { it.displayLanguage }

            _state.update { it.copy(languages = filtered) }

            // ✅ Determine saved language OR set English by default
            val savedLang = _state.value.settings.language
            val defaultLocale = Locale("en", "US")

            val selectedLocale = if (savedLang.isBlank()) {
                // No saved language → use English
                defaultLocale
            } else {
                val localeParts = savedLang.split("_")
                if (localeParts.size == 2) Locale(localeParts[0], localeParts[1])
                else Locale.getDefault()
            }

            // ✅ use init=true so we don't re-save unnecessary intermediate states
            selectLanguage(selectedLocale, init = true)
        }
    }


    fun selectLanguage(locale: Locale, init: Boolean = false) {
        viewModelScope.launch {
            // Update TtsSettings in state
            val newSettings =
                _state.value.settings.copy(language = "${locale.language}_${locale.country}")
            _state.update { it.copy(settings = newSettings) }
            if (!init) {
                // persist the language choice
                saveTtsSettings(newSettings)
            }

            // Update tts engine language
            tts?.language = locale

            // load voices for the chosen language
            val voicesForLang =
                tts?.voices?.filter { it.locale.language == locale.language } ?: emptyList()
            _state.update { it.copy(voices = voicesForLang) }

            // Try to restore previously saved voice if it belongs to this language
            val savedVoiceName = newSettings.genderVoice
            val selectedVoice = voicesForLang.firstOrNull { it.name == savedVoiceName }
                ?: voicesForLang.firstOrNull()
            selectedVoice?.let { tts?.voice = it }

            // If we found a voice and not init, ensure settings contains that voice and persist
            if (!init && selectedVoice != null) {
                val updatedSettings = _state.value.settings.copy(genderVoice = selectedVoice.name)
                _state.update { it.copy(settings = updatedSettings) }
                saveTtsSettings(updatedSettings)
            }
        }
    }

    fun selectVoice(voice: Voice) {
        viewModelScope.launch {
            tts?.voice = voice
            val newSettings = _state.value.settings.copy(genderVoice = voice.name)
            _state.update { it.copy(settings = newSettings) }
            // persist updated settings
            saveTtsSettings(newSettings)
        }
    }

    fun toggleTimeSpeaking(enabled: Boolean) {
        _state.update { it.copy(timeSpeakingEnabled = enabled) }
        viewModelScope.launch { saveTimeSpeakingEnabled(enabled) }
    }

    fun toggleDisableDuringPhoneCalls(disable: Boolean) {
        _state.update { it.copy(disableDuringPhoneCalls = disable) }
        viewModelScope.launch { saveDisableDuringPhoneCalls(disable) }
    }

    fun toggleDisableWhilePlayingMusic(disable: Boolean) {
        _state.update { it.copy(disableWhilePlayingMusic = disable) }
        viewModelScope.launch { saveDisableWhilePlayingMusic(disable) }
    }

    fun speakCurrentTime() {
        val settings = _state.value.settings
        val localeParts = settings.language.split("_")
        val locale = if (localeParts.size == 2) Locale(
            localeParts[0],
            localeParts[1]
        ) else Locale.getDefault()

        // prefer a voice saved in settings (if available)
        val selectedVoice = tts?.voices?.firstOrNull { it.name == settings.genderVoice }
        if (selectedVoice != null) {
            try {
                tts?.voice = selectedVoice
                tts?.language = selectedVoice.locale
            } catch (e: Exception) {
                tts?.language = selectedVoice.locale
            }
        } else {
            tts?.language = locale
        }

        tts?.setPitch(settings.pitch)
        tts?.setSpeechRate(settings.rate)

        val formatter = SimpleDateFormat("hh:mm a", tts?.voice?.locale ?: locale)
        tts?.speak(formatter.format(Date()), TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onCleared() {
        super.onCleared()
        tts?.stop()
        tts?.shutdown()
    }
}
