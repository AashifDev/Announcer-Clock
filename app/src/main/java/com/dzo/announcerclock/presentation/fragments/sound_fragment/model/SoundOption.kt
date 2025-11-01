package com.dzo.announcerclock.presentation.fragments.sound_fragment.model

import android.content.Context
import android.net.Uri

/*data class SoundOption(
    val id: Int? = null,
    val title: String,
    val soundResId: Int? = null, // nullable for external files
    val uri: Uri? = null,        // external sound URI
    var isSelected: Boolean = false
)*/
data class SoundOption(
    val id: Int?=null,
    val title: String,
    val soundResId: Int? = null,
    //val uri: Uri? = null,
    val isSelected: Boolean = false,
    //val isUserAdded: Boolean = false // 👈 Add this
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

fun String.toPrettyTitle(maxLength: Int = 10): String {
    // Step 1: Replace underscores with spaces
    var cleaned = this.replace("_", " ")

    // Step 2: Remove digits
    cleaned = cleaned.replace(Regex("\\d+"), "")

    // Step 3: Trim and capitalize each word
    cleaned = cleaned.trim()
        .split(" ")
        .filter { it.isNotBlank() }
        .joinToString(" ") { word ->
            word.replaceFirstChar { it.uppercaseChar() }
        }

    // Step 4: Truncate to maxLength safely
    return if (cleaned.length > maxLength) {
        cleaned.take(maxLength).trimEnd() + "…"
    } else {
        cleaned
    }
}

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
