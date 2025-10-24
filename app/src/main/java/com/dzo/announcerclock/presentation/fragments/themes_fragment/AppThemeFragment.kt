package com.dzo.announcerclock.presentation.fragments.themes_fragment

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.graphics.toColorInt
import com.dzo.announcerclock.R
import com.dzo.announcerclock.data.local_source.AppPreferences
import com.dzo.announcerclock.databinding.FragmentAppThemeBinding
import com.dzo.announcerclock.utils.core.BaseFragment
import com.dzo.announcerclock.utils.extension.showCustomSnackBar
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetSequence
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch

class AppThemeFragment : BaseFragment<FragmentAppThemeBinding>(FragmentAppThemeBinding::inflate) {

    private val selectedColors = mutableListOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadColorsFromSession()
        displaySelectedColors()

        val darkMode = AppPreferences.isDarkThemeEnabled()
        binding.enableDarkMode.isChecked = darkMode == true
        binding.enableDarkMode.setOnCheckedChangeListener { _, isChecked ->
            AppPreferences.saveDarkThemeEnabled(isChecked)
            setThemeMode(isChecked)
        }


        binding.chooseAppColor.setOnClickListener {
            showColorPicker()
        }

        AppPreferences.ThemeManager.registerListener { colorHex ->
            safeExecute {
                binding.chooseAppColor.backgroundTintList =
                    ColorStateList.valueOf(colorHex.toColorInt())
                binding.enableDarkMode.thumbTintList = ColorStateList.valueOf(colorHex.toColorInt())
            }
        }
    }

    private fun showIntroGuide() {
        TapTargetSequence(requireActivity())
            .targets(
                TapTarget.forView(
                    binding.enableDarkMode,
                    "Dark mode",
                    "Enable this toggle for dark mode theme."
                )
                    .outerCircleColor(R.color.light_blue)
                    .targetCircleColor(R.color.white)
                    .titleTextColor(R.color.black)
                    .descriptionTextColor(R.color.black)
                    .tintTarget(true)
                    .cancelable(false)
                    .transparentTarget(true),

                TapTarget.forView(binding.chooseAppColor, "App color", "Choose app color theme. Also can choose from recent color.")
                    .targetCircleColor(R.color.white)
                    .outerCircleColor(R.color.light_blue)
                    .titleTextColor(R.color.black)
                    .descriptionTextColor(R.color.black)
                    .tintTarget(true)
                    .cancelable(false)
                    .transparentTarget(true),
            )
            .listener(object : TapTargetSequence.Listener {
                override fun onSequenceFinish() {
                    AppPreferences.saveFirstLaunch(true)
                }

                override fun onSequenceStep(lastTarget: TapTarget, targetClicked: Boolean) {}
                override fun onSequenceCanceled(lastTarget: TapTarget) {}
            })
            .start()
    }

    private fun setThemeMode(isDark: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (isDark) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }

    private fun showColorPicker() {
        MaterialColorPickerDialog.Builder(requireContext())
            .setTitle("Pick App Theme Color")
            .setColorShape(ColorShape.SQAURE)
            .setColorSwatch(ColorSwatch._300)
            .setDefaultColor(R.color.blue)
            .setColorListener { _, colorHex ->
                if (!selectedColors.contains(colorHex)) {
                    selectedColors.add(colorHex)
                    // Save active color
                    AppPreferences.ThemeManager.setActiveThemeColor(colorHex)
                    // Save full list
                    AppPreferences.ThemeManager.setThemeColorList(selectedColors.joinToString(","))
                    displaySelectedColors()
                    requireActivity().showCustomSnackBar(
                        "App theme color updated.", iconRes = R.drawable.themes,
                        colorString = colorHex
                    )
                }
            }
            .show()
    }

    private fun loadColorsFromSession() {
        val colorsStr = AppPreferences.ThemeManager.getThemeColorList()
        selectedColors.clear()
        if (colorsStr!!.isNotEmpty()) {
            selectedColors.addAll(colorsStr.split(","))
        }
    }


    private fun displaySelectedColors() {
        val inflater = LayoutInflater.from(requireContext())
        binding.colorContainer.removeAllViews()

        val marginInPx = (8 * resources.displayMetrics.density).toInt()

        for (colorHex in selectedColors) {
            val colorView = inflater.inflate(R.layout.item_color_box, binding.colorContainer, false)
            val box = colorView.findViewById<View>(R.id.colorBox)

            box.background.setTint(colorHex.toColorInt())

            // Add margin without changing size
            val params = colorView.layoutParams as ViewGroup.MarginLayoutParams
            params.setMargins(marginInPx, marginInPx, marginInPx, marginInPx)
            colorView.layoutParams = params

            box.setOnClickListener {
                AppPreferences.ThemeManager.setActiveThemeColor(colorHex)
                AppPreferences.ThemeManager.setThemeColorList(selectedColors.joinToString(","))
                displaySelectedColors()
                requireActivity().showCustomSnackBar(
                    "App theme color updated.", iconRes = R.drawable.themes,
                    colorString = colorHex
                )
            }
            box.setOnLongClickListener {
                selectedColors.remove(colorHex)
                AppPreferences.ThemeManager.setThemeColorList(selectedColors.joinToString(","))
                if (selectedColors.isNotEmpty()) {
                    AppPreferences.ThemeManager.setActiveThemeColor(selectedColors.last())
                }
                displaySelectedColors()
                true
            }

            binding.colorContainer.addView(colorView)
        }
    }

   /* override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val isSystemDark =
            (newConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
        if (AppPreferences.isDarkThemeEnabled() == null) {
            binding.enableDarkMode.isChecked = isSystemDark
        }
    }*/
}
