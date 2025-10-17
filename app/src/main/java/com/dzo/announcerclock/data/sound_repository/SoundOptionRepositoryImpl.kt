package com.dzo.announcerclock.data.sound_repository

import android.content.Context
import com.dzo.announcerclock.R
import com.dzo.announcerclock.presentation.fragments.sound_fragment.model.SoundOption
import com.dzo.announcerclock.presentation.fragments.sound_fragment.model.capitalizeWords
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.collections.listOf

class SoundOptionRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SoundOptionRepository {

    override fun getSoundOptions(): List<SoundOption> {
        val rawList = listOf(
            R.raw.smw_power_up,
            R.raw.mario_starwarp,
            R.raw.smbw_yoshi,
            R.raw.door_opens,
            R.raw.balloon_get,
            R.raw.super_mario_world_14,
            R.raw.super_mario_world,
            R.raw.super_mario_jump
        )
        return rawList.mapIndexed { index, resId ->
            SoundOption(
                id = index + 1,
                title = context.resources.getResourceEntryName(resId).replace("_", " ")
                    .capitalizeWords(),
                soundResId = resId
            )
        }
    }
}
