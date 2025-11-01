package com.dzo.announcerclock.data.sound_repository

import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore
import com.dzo.announcerclock.R
import com.dzo.announcerclock.presentation.fragments.sound_fragment.model.SoundOption
import com.dzo.announcerclock.presentation.fragments.sound_fragment.model.capitalizeWords
import com.dzo.announcerclock.presentation.fragments.sound_fragment.model.toPrettyTitle
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SoundOptionRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SoundOptionRepository {

    override fun getSoundOptions(): List<SoundOption> {
        val soundList = mutableListOf<SoundOption>()

        val rawSounds = listOf(
            R.raw.chime_alert,
            R.raw.level_up,
            R.raw.level_up_1,
            R.raw.positive_notification,
            R.raw.balloon_get,
            R.raw.super_mario_world_14,
            R.raw.ring_bell,
            R.raw.super_mario_jump,
            R.raw.base_boom
        )

        rawSounds.forEachIndexed { index, resId ->
            soundList.add(
                SoundOption(
                    id = index + 1,
                    title = context.resources.getResourceEntryName(resId)
                        .replace("_", " ")
                        .capitalizeWords(),
                    soundResId = resId,
                )
            )
        }

       /* val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.DISPLAY_NAME
        )
        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"
        val sortOrder = "${MediaStore.Audio.Media.DISPLAY_NAME} ASC"

        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val cursor = context.contentResolver.query(uri, projection, selection, null, sortOrder)

        cursor?.use {
            val idCol = it.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val nameCol = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)
            var nextId = soundList.size + 1

            while (it.moveToNext()) {
                val id = it.getLong(idCol)
                val name = it.getString(nameCol)
                val contentUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)

                soundList.add(
                    SoundOption(
                        id = nextId++,
                        title = name.toPrettyTitle(20),
                        uri = contentUri
                    )
                )
            }
        }*/

        return soundList
    }
}