package com.dzo.announcerclock.utils.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.dzo.announcerclock.R
import com.google.android.material.snackbar.Snackbar
import com.dzo.announcerclock.utils.Utils.lighten
import androidx.core.graphics.toColorInt

@SuppressLint("RestrictedApi")
fun Activity.showCustomSnackBar(
    message: String,
    iconRes: Int? = null,
    actionText: String? = null,
    duration: Int = Snackbar.LENGTH_SHORT,
    colorString: String,
    onActionClick: (() -> Unit)? = null
) {
    val safeColor = try {
        colorString.ifBlank { "#2196F3" }
    } catch (e: Exception) {
        "#2196F3"
    }

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
        setColor(safeColor.toColorInt())
    }
    snackbarContainer.background = drawable

    val drawableRestart = GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        cornerRadius = radius
        setColor(safeColor.lighten(0.2f))
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

    val bottomSheet = this.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
    bottomSheet?.let {
        snackbar.view.translationY = -it.height.toFloat()
    }

    snackbar.show()
}

fun Context.showColoredToast(
    message: String,
    bgColor: Int,
    textColor: Int,
    iconRes: Int? = null,
    duration: Int? = Toast.LENGTH_SHORT
) {

    val layout = LayoutInflater.from(this).inflate(R.layout.custom_toast, null)
    val textView = layout.findViewById<TextView>(R.id.toast_text)
    val root = layout.findViewById<LinearLayout>(R.id.toast_root)

    textView.text = message
    textView.setTextColor(textColor)
    root.backgroundTintList = ColorStateList.valueOf(bgColor)

    Toast(this).apply {
        view = layout
        duration
        setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 150)
        show()
    }
}

fun Context.showColoredToast(message: String, bgColor: Int, textColor: Int) {
    val inflater = LayoutInflater.from(this)
    val layout = inflater.inflate(R.layout.custom_toast, null)

    val textView = layout.findViewById<TextView>(R.id.toast_text)
    val root = layout.findViewById<LinearLayout>(R.id.toast_root)

    textView.text = message
    textView.setTextColor(textColor)
    root.backgroundTintList = ColorStateList.valueOf(bgColor)

    // Apply initial iOS-style animation state
    layout.alpha = 0f
    layout.scaleX = 0.8f
    layout.scaleY = 0.8f

    Toast(this).apply {
        view = layout
        duration = Toast.LENGTH_SHORT
        show()
    }

    // --- Animate In ---
    layout.animate()
        .alpha(1f)
        .scaleX(1f)
        .scaleY(1f)
        .setDuration(250)
        .start()

    // --- Animate Out ---
    // Schedule fade-out after toast duration (LENGTH_SHORT ≈ 2s)
    Handler(Looper.getMainLooper()).postDelayed({
        layout.animate()
            .alpha(0f)
            .scaleX(0.8f)
            .scaleY(0.8f)
            .setDuration(250)
            .start()
    }, 1800)
}

