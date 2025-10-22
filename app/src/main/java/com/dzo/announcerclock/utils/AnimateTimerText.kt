package com.dzo.announcerclock.utils

import android.content.Context
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.view.animation.OvershootInterpolator
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.dzo.announcerclock.R

fun animateTimerText(
    textView: TextView,
    context: Context,
    value: String,
    colorInt: Int,
    animationType: AnimationType = AnimationType.SMOOTH
) {
    when (animationType) {

        // 🔹 Option 1 — Fade animation
        AnimationType.FADE -> {
            textView.animate()
                .alpha(0f)
                .setDuration(100)
                .withEndAction {
                    textView.text = styledTimerText(context, value, colorInt)
                    textView.animate()
                        .alpha(1f)
                        .setDuration(100)
                        .start()
                }
                .start()
        }

        // 🔹 Option 2 — Pop (Scale) animation
        AnimationType.POP -> {
            textView.animate()
                .scaleX(0f)
                .scaleY(0f)
                .setDuration(100)
                .withEndAction {
                    textView.text = styledTimerText(context, value,colorInt)
                    textView.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(150)
                        .setInterpolator(OvershootInterpolator())
                        .start()
                }
                .start()
        }

        // 🔹 Option 3 — Smooth (Scale + Fade combo)
        AnimationType.SMOOTH -> {
            textView.animate()
                .scaleX(0.8f)
                .scaleY(0.8f)
                .alpha(0f)
                .setDuration(120)
                .withEndAction {
                    textView.text = styledTimerText(context, value,colorInt)
                    textView.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .alpha(1f)
                        .setDuration(180)
                        .setInterpolator(OvershootInterpolator())
                        .start()
                }
                .start()
        }
    }
}

// 🔸 Helper: creates your styled text ("reset in\nXX")
private fun styledTimerText(context: Context, value: String,colorInt: Int): CharSequence {
    return SpannableStringBuilder().apply {
        val firstPart = "reset in\n"
        append(firstPart)
        setSpan(
            ForegroundColorSpan(colorInt),
            0,
            firstPart.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        setSpan(
            AbsoluteSizeSpan(15, true),
            0,
            firstPart.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        append(value)
    }
}

// 🔸 Enum to choose animation type
enum class AnimationType {
    FADE,
    POP,
    SMOOTH
}