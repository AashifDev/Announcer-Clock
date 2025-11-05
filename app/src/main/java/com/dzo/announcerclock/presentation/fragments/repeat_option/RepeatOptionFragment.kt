package com.dzo.announcerclock.presentation.fragments.repeat_option

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.NumberPicker
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.TooltipCompat
import androidx.compose.ui.window.Dialog
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.toColorInt
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.dzo.announcerclock.R
import com.dzo.announcerclock.data.local_source.AppPreferences
import com.dzo.announcerclock.presentation.fragments.repeat_option.adapter.RepeatOptionAdapter
import com.dzo.announcerclock.databinding.FragmentRepeatOptionBinding
import com.dzo.announcerclock.presentation.fragments.home_fragment.viewmodel.TimerViewModel
import com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption
import com.dzo.announcerclock.presentation.fragments.repeat_option.viewmodel.RepeatOptionViewModel
import com.dzo.announcerclock.utils.Utils.lighten
import com.dzo.announcerclock.utils.core.BaseFragment
import com.dzo.announcerclock.utils.extension.showColoredToast
import com.dzo.announcerclock.utils.extension.showCustomSnackBar
import com.google.android.material.card.MaterialCardView
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonAnimation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.getValue

@AndroidEntryPoint
class RepeatOptionFragment :
    BaseFragment<FragmentRepeatOptionBinding>(FragmentRepeatOptionBinding::inflate) {

    @Inject
    lateinit var repeatOptionAdapter: RepeatOptionAdapter
    private val timerViewModel: TimerViewModel by viewModels()
    private var colorHexx = ""

    private val viewModel: RepeatOptionViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.repeatOptionRecyclerView.adapter = repeatOptionAdapter

        repeatOptionAdapter.onItemClick = { repeatOption ->
            if (AppPreferences.getToggleState() == true) {
                if (repeatOption.id == 8) {
                    showCustomRepeatDialog(repeatOption)
                } else {
                    // Normal option
                    viewModel.selectOption(repeatOption)
                    //findNavController().popBackStack()
                    timerViewModel.startTimer(repeatOption.valueInMinute!!)

                }
            } else {
                requireActivity().showCustomSnackBar(
                    "Please enable toggle first!",
                    iconRes = R.drawable.toggle_button,
                    colorString = colorHexx
                )
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.repeatOptions.collect { list ->
                    val updatedList = list.map {
                        it.copy(isSelected = it.title == AppPreferences.getRepeatOption()?.title)
                    }
                    repeatOptionAdapter.submitList(updatedList)
                }
            }
        }

        AppPreferences.ThemeManager.registerListener { colorHex ->
            safeExecute { binding ->
                colorHexx = colorHex
            }
        }
    }

    @SuppressLint("ResourceType")
    private fun showCustomRepeatDialog(option: RepeatOption) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_custom_repeat_option)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val etInterval = dialog.findViewById<NumberPicker>(R.id.etInterval)
        val etTotal = dialog.findViewById<NumberPicker>(R.id.etTotal)
        val btnSave = dialog.findViewById<Button>(R.id.btnSave)
        val tvTitle = dialog.findViewById<TextView>(R.id.tvTitle)
        val themeCard = dialog.findViewById<MaterialCardView>(R.id.themeCard)
        val txtInterval = dialog.findViewById<TextView>(R.id.txtIntervalMinute)
        val txtTotal = dialog.findViewById<TextView>(R.id.txtTotalMinute)
        val hint = dialog.findViewById<AppCompatImageView>(R.id.hint)
        val clos = dialog.findViewById<AppCompatImageView>(R.id.close)

        val colorInt = colorHexx.toColorInt()
        txtInterval?.setTextColor(colorInt)
        txtTotal?.setTextColor(colorInt)
        tvTitle?.setTextColor(colorInt)
        btnSave?.setBackgroundColor(colorInt)
        themeCard?.background?.setTint(colorInt)
        hint?.setColorFilter(colorInt)
        clos?.setColorFilter(colorInt)

        // 🧭 Setup pickers
        setupPicker(etInterval!!, 1, 60)
        setupPicker(etTotal!!, 1, 24)

        // 💾 Save button click
        btnSave?.setOnClickListener {
            val interval = etInterval.value.toLong()
            val total = etTotal.value.toLong()

            if (interval > total) {
                requireActivity().showColoredToast(
                    "Interval minute should be less than total minute!",
                    colorHexx.lighten(0.8f),
                    colorInt
                )
            } else {
                val customPair = Pair(interval, total)

                // Update ViewModel and Preferences
                viewModel.selectCustomOption(option, customPair)

                dialog.dismiss()
                findNavController().popBackStack()

                // Start Timer
                timerViewModel.startCustomTimer(customPair.first, customPair.second)
            }
        }

        clos.setOnClickListener {
            dialog.dismiss()
        }

        // 💡 Tooltip hint
        hint?.setOnClickListener {
            val inflater = LayoutInflater.from(context)
            val tooltipView = inflater.inflate(R.layout.tooltip_layout, null)
            val popupWindow = PopupWindow(
                tooltipView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
            )

            val root = tooltipView.findViewById<LinearLayout>(R.id.root)
            val tvHint = tooltipView.findViewById<TextView>(R.id.tvHint)
            "Set interval minute for text to speech and total minute for timer.".apply {
                tvHint.text = it.toString()
            }
            tvHint.setTextColor(colorInt)
            root.background.setTint(colorHexx.lighten(0.9f))

            popupWindow.elevation = 8f
            popupWindow.showAsDropDown(hint, 0, 10)
        }

        dialog.setCancelable(false)

        dialog.show()
    }


    private fun setupPickerIntervalMinuteWithStep(
        picker: NumberPicker,
        step: Int,
        min: Int,
        max: Int
    ) {
        val values = (min..max step step).map { it.toString() }.toTypedArray()
        picker.minValue = 0
        picker.maxValue = values.size - 1
        picker.displayedValues = values
        picker.wrapSelectorWheel = true
        picker.setFormatter {
            //String.format("%02d", values[it].toInt())
            "%02d".format(values[it].toInt())
        }
    }

    private fun setupPickerIntervalTotalMinuteWithStep(
        picker: NumberPicker,
        step: Int,
        min: Int,
        max: Int
    ) {
        val values = (min..max step step).map { it.toString() }.toTypedArray()
        picker.minValue = 0
        picker.maxValue = values.size - 1
        picker.displayedValues = values
        picker.wrapSelectorWheel = true
        picker.setFormatter { String.format("%02d", values[it].toInt()) }
    }

    private fun setupPicker(picker: NumberPicker, min: Int, max: Int) {
        setNumberPickerDividerColor(picker,colorHexx.toColorInt())
        picker.minValue = min
        picker.maxValue = max
        picker.wrapSelectorWheel = true
        picker.setFormatter {
            //String.format("%02d", it)
            "%02d".format(it)
        }
    }

    private fun setNumberPickerDividerColor(numberPicker: NumberPicker, color: Int) {
        try {
            val fields = numberPicker.javaClass.declaredFields
            for (field in fields) {
                if (field.name == "mSelectionDivider") {
                    field.isAccessible = true
                    val colorDrawable = color.toDrawable()
                    field.set(numberPicker, colorDrawable) // set custom color
                    break
                }
            }
            numberPicker.invalidate()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}