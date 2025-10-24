package com.dzo.announcerclock.presentation.fragments.repeat_option

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
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
import com.dzo.announcerclock.utils.core.BaseFragment
import com.dzo.announcerclock.utils.extension.showCustomSnackBar
import com.google.android.material.card.MaterialCardView
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

    private fun showCustomRepeatDialog(option: RepeatOption) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_custom_repeat_option, null)
        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setCancelable(true)
            .create()
        dialog.window?.setBackgroundDrawableResource(R.drawable.bg_dialog_round)
        val etInterval = dialogView.findViewById<NumberPicker>(R.id.etInterval)
        val etTotal = dialogView.findViewById<NumberPicker>(R.id.etTotal)
        val btnSave = dialogView.findViewById<Button>(R.id.btnSave)
        val tvTitle = dialogView.findViewById<TextView>(R.id.tvTitle)
        val themeCard = dialogView.findViewById<MaterialCardView>(R.id.themeCard)
        val txtInterval = dialogView.findViewById<TextView>(R.id.txtIntervalMinute)
        val txtTotal = dialogView.findViewById<TextView>(R.id.txtTotalMinute)

        txtInterval.setTextColor(colorHexx.toColorInt())
        txtTotal.setTextColor(colorHexx.toColorInt())
        tvTitle.setTextColor(colorHexx.toColorInt())
        btnSave.setBackgroundColor(colorHexx.toColorInt())
        //setupPickerIntervalMinuteWithStep(etInterval, 5,5, 30)
        //setupPickerIntervalTotalMinuteWithStep(etTotal, 10, 10,60)



        setupPicker(etInterval, 1, 60)
        setupPicker(etTotal, 1, 24)

        btnSave.setOnClickListener {

            val interval = etInterval.value.toLong()
            val total = etTotal.value.toLong()

            val customPair = Pair(interval, total)

            // Update ViewModel and Preferences
            viewModel.selectCustomOption(option, interval to total)

            dialog.dismiss()
            findNavController().popBackStack()

            // Start Timer with custom values
            timerViewModel.startCustomTimer(
                customPair.first,
                customPair.second
            )
        }

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
        picker.setFormatter { String.format("%02d", values[it].toInt()) }
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
        picker.minValue = min
        picker.maxValue = max
        picker.wrapSelectorWheel = true
        picker.setFormatter { String.format("%02d", it) }
    }

}