package com.dzo.announcerclock.presentation.fragments.themes

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dzo.announcerclock.R
import com.dzo.announcerclock.data.local_source.AppPreferences
import com.dzo.announcerclock.databinding.FragmentAppThemeBinding
import com.dzo.announcerclock.utils.core.BaseFragment
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import androidx.core.graphics.toColorInt

class AppThemeFragment : BaseFragment<FragmentAppThemeBinding>(FragmentAppThemeBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.enableNotification.setOnCheckedChangeListener { _,isChecked->
            if (isChecked)
                showDialog()
        }
    }

    private fun showDialog() {
        MaterialColorPickerDialog
            .Builder(requireContext())
            .setTitle("Pick App Theme Color")
            .setColorShape(ColorShape.CIRCLE)
            .setColorSwatch(ColorSwatch._300)
            .setDefaultColor(R.color.blue)
            .setColorListener { color, colorHex ->
                // Handle Color Selection
                AppPreferences.saveThemeColor(colorHex)
            }
            .show()
    }

}