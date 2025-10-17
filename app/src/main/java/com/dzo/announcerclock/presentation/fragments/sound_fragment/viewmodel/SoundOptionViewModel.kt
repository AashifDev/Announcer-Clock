/*
package com.dzo.talktime.presentation.fragments.sound_fragment.viewmodel

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzo.talktime.presentation.fragments.sound_fragment.model.SoundOption
import com.dzo.talktime.data.local_source.AppPreferences
import com.dzo.talktime.domain.sound_option_usecase.GetSoundOptionsUseCase
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
): ViewModel() {

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

    // Handle single selection
    fun selectOption(selectedItem: SoundOption) = viewModelScope.launch {
        val updatedList = _soundOptions.value.map {
            if (it.id == selectedItem.id) {
                it.copy(isSelected = true)
            } else {
                it.copy(isSelected = false)
            }
        }
        _soundOptions.value = updatedList

        // Save selected option in preferences
        val selected = updatedList.find { it.isSelected }
        selected?.let {
            AppPreferences.saveSoundOption(it)
        }

        playSound(selectedItem.soundResId)

    }

    private fun playSound(resId: Int) {
        // Release any existing MediaPlayer
        mediaPlayer?.release()

        // Create new MediaPlayer for selected resource
        mediaPlayer = MediaPlayer.create(context, resId)
        mediaPlayer?.apply {
            setOnCompletionListener {
                release() // Release when done
                mediaPlayer = null
            }
            start()
        }
    }

    override fun onCleared() {
        super.onCleared()
        // Release MediaPlayer when ViewModel is destroyed
        mediaPlayer?.release()
        mediaPlayer = null
    }

}
*/

package com.dzo.announcerclock.presentation.fragments.sound_fragment.viewmodel

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzo.announcerclock.presentation.fragments.sound_fragment.model.SoundOption
import com.dzo.announcerclock.data.local_source.AppPreferences
import com.dzo.announcerclock.domain.sound_option_usecase.GetSoundOptionsUseCase
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

    // Handle single selection
    fun selectOption(selectedItem: SoundOption) = viewModelScope.launch {
        val updatedList = _soundOptions.value.map {
            //it.copy(isSelected = it.id == selectedItem.id)
            if (it.id == selectedItem.id) {
                it.copy(isSelected = true)
            } else {
                it.copy(isSelected = false)
            }
        }
        _soundOptions.value = updatedList

        // Save selected option
        // Save selected option in preferences
        val selected = updatedList.find { it.isSelected }
        selected?.let {
            AppPreferences.saveSoundOption(it)
        }
        playSound(selectedItem.soundResId)
    }

    private fun playSound(resId: Int) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(context, resId)
        mediaPlayer?.apply {
            setOnCompletionListener {
                release()
                mediaPlayer = null
            }
            start()
        }
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
