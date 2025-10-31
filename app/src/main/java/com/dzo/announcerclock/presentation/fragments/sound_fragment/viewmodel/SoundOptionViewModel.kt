package com.dzo.announcerclock.presentation.fragments.sound_fragment.viewmodel

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzo.announcerclock.data.local_source.AppPreferences
import com.dzo.announcerclock.domain.sound_option_usecase.GetSoundOptionsUseCase
import com.dzo.announcerclock.presentation.fragments.sound_fragment.model.SoundOption
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SoundOptionViewModel @Inject constructor(
    private val getSoundOptionsUseCase: GetSoundOptionsUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _soundOptions = MutableStateFlow<List<SoundOption>>(emptyList())
    val soundOptions = _soundOptions.asStateFlow()

    private var mediaPlayer: MediaPlayer? = null

    init {
        loadSoundOptions()
    }

    private fun loadSoundOptions() {
        val list = getSoundOptionsUseCase()
        _soundOptions.value = list
    }

    fun selectOption(selectedItem: SoundOption) = viewModelScope.launch {
        val updatedList = _soundOptions.value.map {
            it.copy(isSelected = it.id == selectedItem.id)
        }
        _soundOptions.value = updatedList

        // Save selected option
        val selected = updatedList.find { it.isSelected }
        selected?.let { AppPreferences.saveSoundOption(it) }

        playSound(selectedItem)
    }

    fun addCustomSound(sound: SoundOption) {
        val updatedList = _soundOptions.value.toMutableList().apply {
            add(sound)
        }
        _soundOptions.value = updatedList
    }

    private fun playSound(soundOption: SoundOption) {
        try {
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer()

            if (soundOption.uri != null) {
                // External sound
                mediaPlayer?.setDataSource(context, soundOption.uri)
                mediaPlayer?.prepare()
            } else {
                // Raw resource sound
                val uri = "android.resource://${context.packageName}/${soundOption.soundResId}".toUri()
                mediaPlayer?.setDataSource(context, uri)
                mediaPlayer?.prepare()
            }

            mediaPlayer?.apply {
                setOnCompletionListener {
                    release()
                    mediaPlayer = null
                }
                start()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
