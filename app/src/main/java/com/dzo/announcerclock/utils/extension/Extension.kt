package com.dzo.announcerclock.utils.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
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

    val bottomSheet = this.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
    bottomSheet?.let {
        snackbar.view.translationY = -it.height.toFloat()
    }

    snackbar.show()
}
@SuppressLint("InflateParams")
fun Context.showOverlayToast(
    message: String,
    iconRes: Int? = null,
    actionText: String? = null,
    colorString: String,
    duration: Long = 2000L,
    onActionClick: (() -> Unit)? = null
) {
    val activity = this as? Activity ?: return
    val inflater = LayoutInflater.from(activity)
    val customView = inflater.inflate(R.layout.custom_snackbar, null)

    val textView = customView.findViewById<AppCompatTextView>(R.id.snackbar_text)
    val iconView = customView.findViewById<AppCompatImageView>(R.id.snackbar_icon)
    val actionButton = customView.findViewById<AppCompatTextView>(R.id.snackbar_action)
    val container = customView.findViewById<ConstraintLayout>(R.id.snackbar_container)

    val radius = 28f
    val bgDrawable = GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        cornerRadius = radius
        setColor(colorString.toColorInt())
    }
    container.background = bgDrawable

    val actionDrawable = GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        cornerRadius = radius
        setColor(colorString.lighten(0.2f))
    }
    actionButton.background = actionDrawable

    textView.text = message

    if (iconRes != null) {
        iconView.setImageResource(iconRes)
        iconView.visibility = View.VISIBLE
    } else {
        iconView.visibility = View.GONE
    }

    if (actionText != null && onActionClick != null) {
        actionButton.text = actionText
        actionButton.visibility = View.VISIBLE
        actionButton.setOnClickListener {
            onActionClick()
        }
    } else {
        actionButton.visibility = View.GONE
    }

    // Add overlay to root layout
    val decorView = activity.window.decorView as ViewGroup
    val rootView = decorView.findViewById<ViewGroup>(android.R.id.content)

    val params = FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.MATCH_PARENT,
        FrameLayout.LayoutParams.WRAP_CONTENT
    ).apply {
        gravity = Gravity.BOTTOM
        bottomMargin = 180 // 👈 BottomSheet ke upar dikhane ke liye space
    }

    customView.alpha = 0f
    customView.translationY = 200f
    rootView.addView(customView, params)

    // Animate in
    customView.animate()
        .alpha(1f)
        .translationY(0f)
        .setDuration(300)
        .start()

    // Auto dismiss
    Handler(Looper.getMainLooper()).postDelayed({
        customView.animate()
            .alpha(0f)
            .translationY(100f)
            .setDuration(300)
            .withEndAction {
                rootView.removeView(customView)
            }
            .start()
    }, duration)
}
