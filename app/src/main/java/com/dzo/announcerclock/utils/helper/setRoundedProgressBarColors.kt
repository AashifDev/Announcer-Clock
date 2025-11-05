package com.dzo.announcerclock.utils.helper

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.view.View
import android.widget.ProgressBar
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import com.dzo.announcerclock.utils.Utils.lighten

fun setRoundedProgressBarColors(
    view: ProgressBar,
    context: Context,
    trackColor: String,
    progressColor: String,
    cornerRadiusDp: Float
) {
    val cornerRadiusPx = cornerRadiusDp * context.resources.displayMetrics.density

    // Background (track)
    val backgroundDrawable = GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        cornerRadius = cornerRadiusPx
        setColor(trackColor.lighten(0.5f))
    }

    // Foreground (progress fill)
    val progressDrawable = GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        cornerRadius = cornerRadiusPx
        setColor(progressColor.toColorInt())
    }

    // Combine both into a LayerDrawable
    val layers = arrayOf(backgroundDrawable, progressDrawable)
    val layerDrawable = LayerDrawable(layers).apply {
        setId(0, android.R.id.background)
        setId(1, android.R.id.progress)
    }

    view.progressDrawable =  layerDrawable
}
