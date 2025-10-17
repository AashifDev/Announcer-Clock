package com.dzo.announcerclock.presentation.fragments.home_fragment.model

import java.util.Locale

data class TtsSettings(
    val language: String = Locale.getDefault().toString(), // ex: "en_US"
    val pitch: Float = 1.0f,
    val rate: Float = 1.0f,
    val genderVoice: String = "" // "male" or "female"
)

