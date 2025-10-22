package com.dzo.announcerclock.utils.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.dzo.announcerclock.App
import com.dzo.announcerclock.R
import com.google.android.material.snackbar.Snackbar
import com.dzo.announcerclock.utils.Utils.lighten
import androidx.core.graphics.toColorInt

@SuppressLint("RestrictedApi")
fun Activity.showCustomSnackbar(
    message: String,
    iconRes: Int? = null,
    actionText: String? = null,
    duration: Int = Snackbar.LENGTH_SHORT,
    colorString: String,
    onActionClick: (() -> Unit)? = null
) {

    val rootView = this.findViewById<View>(android.R.id.content)
    val snackbar = Snackbar.make(rootView, "", duration)

    // Inflate custom layout
    val customView = LayoutInflater.from(this).inflate(R.layout.custom_snackbar, null)
    val textView = customView.findViewById<AppCompatTextView>(R.id.snackbar_text)
    val iconView = customView.findViewById<AppCompatImageView>(R.id.snackbar_icon)
    val actionButton = customView.findViewById<AppCompatTextView>(R.id.snackbar_action)
    val snackbarContainer = customView.findViewById<ConstraintLayout>(R.id.snackbar_container)

    val radius = 28f
    val drawable = GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        cornerRadius = radius
        setColor(colorString.toColorInt())
    }
    snackbarContainer.background = drawable

    val drawableRestart = GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        cornerRadius = radius
        setColor(colorString.lighten(0.2f))
    }

    actionButton.background = drawableRestart

    textView.text = message

    // Icon setup
    if (iconRes != null) {
        iconView.setImageResource(iconRes)
        iconView.visibility = View.VISIBLE
    } else {
        iconView.visibility = View.GONE
    }


    // Action setup
    if (actionText != null && onActionClick != null) {
        actionButton.text = actionText
        actionButton.visibility = View.VISIBLE
        actionButton.setOnClickListener {
            onActionClick()
            snackbar.dismiss()
        }
    } else {
        actionButton.visibility = View.GONE
    }

    // Replace default layout
    val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout
    snackbarLayout.setPadding(0, 0, 0, 0)
    snackbarLayout.setBackgroundColor(android.graphics.Color.TRANSPARENT)
    snackbarLayout.addView(customView, 0)

    // iOS-style slide-up animation
    customView.alpha = 0f
    customView.translationY = 100f
    customView.animate().alpha(1f).translationY(0f).setDuration(300).start()

    snackbar.show()
}
