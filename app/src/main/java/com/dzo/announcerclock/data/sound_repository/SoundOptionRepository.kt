package com.dzo.announcerclock.data.sound_repository

import com.dzo.announcerclock.presentation.fragments.sound_fragment.model.SoundOption

interface SoundOptionRepository {
    fun getSoundOptions(): List<SoundOption>
}