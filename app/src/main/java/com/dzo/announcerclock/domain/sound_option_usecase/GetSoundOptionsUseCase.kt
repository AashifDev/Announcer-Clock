package com.dzo.announcerclock.domain.sound_option_usecase

import com.dzo.announcerclock.presentation.fragments.sound_fragment.model.SoundOption
import com.dzo.announcerclock.data.sound_repository.SoundOptionRepository
import javax.inject.Inject

class GetSoundOptionsUseCase @Inject constructor(
    private val repository: SoundOptionRepository
) {
    operator fun invoke(): List<SoundOption> {
        return repository.getSoundOptions()
    }
}