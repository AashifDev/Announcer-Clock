package com.dzo.announcerclock.presentation.fragments.our_app_fragment.model

import com.dzo.announcerclock.R

data class OtherAppModel(
    val logo:Int,
    val appName:String,
    val packageName:String
)

val otherAppList = listOf(
    OtherAppModel(R.drawable.hanuman_chalisa,"Hanuman Chalisa","com.dzo.HanumanChalisaWithAudioAndAlarm"),
    OtherAppModel(R.drawable.aarti,"Aarti Sangrah","com.dzo.aarti"),
    OtherAppModel(R.drawable.gurbani_nitnem,"Nitnem Gurbani","com.dzo.gurbani"),
    OtherAppModel(R.drawable.hanuman_chalisa_multi,"Hanuman Chalisa Multilingual","com.dzo.HanumanChalisaMultilingual"),
    OtherAppModel(R.drawable.shri_krishna,"Shri Krishna","com.dzo.krishna"),
    OtherAppModel(R.drawable.sai_baba,"Sai Baba","com.dzo.saibaba"),
    OtherAppModel(R.drawable.quran,"Al-Quran","com.dzo.al_quran"),
)
