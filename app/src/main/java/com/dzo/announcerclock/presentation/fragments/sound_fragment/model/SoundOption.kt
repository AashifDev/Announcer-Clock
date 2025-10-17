package com.dzo.announcerclock.presentation.fragments.sound_fragment.model

import android.content.Context

data class SoundOption(
    val id:Int? = null,
    val title: String,
    val soundResId: Int,
    var isSelected:Boolean = false
)

fun getSoundTitleFromRes(context: Context, soundResId: Int): String {
    return try {
        context.resources.getResourceEntryName(soundResId) // Returns e.g., "smw_power_up"
    } catch (e: Exception) {
        "Unknown"
    }
}




fun String.capitalizeWords(): String =
    split(" ").joinToString(" ") { it.replaceFirstChar { c -> c.uppercaseChar() } }


/*


val getSoundOption = listOf(
    SoundOption(id = 1, title = "Default", soundResId = R.raw.smw_power_up),
    SoundOption(id = 2, title = "Alarm", soundResId = R.raw.mario_starwarp),
    SoundOption(id = 3, title = "Bell", soundResId = R.raw.smbw_yoshi),
    SoundOption(id = 4, title = "Blip", soundResId = R.raw.door_opens),
    SoundOption(id = 5, title = "Click", soundResId = R.raw.balloon_get),
    SoundOption(id = 6, title = "Coin", soundResId = R.raw.super_mario_world_14),
    SoundOption(id = 7, title = "Coin", soundResId = R.raw.super_mario_world),
    SoundOption(id = 8, title = "Coin2", soundResId = R.raw.super_mario_jump),
)*/
