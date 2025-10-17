package com.dzo.announcerclock.presentation.fragments.repeat_option

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
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

    private val viewModel: RepeatOptionViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.repeatOptionRecyclerView.adapter = repeatOptionAdapter

        repeatOptionAdapter.onItemClick = { repeatOption ->
            if (repeatOption.id == 8) {
                showCustomRepeatDialog(repeatOption)
            } else {
                // Normal option
                viewModel.selectOption(repeatOption)
                //findNavController().popBackStack()

                timerViewModel.startTimer(repeatOption.valueInMinute!!)

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

    }

    private fun showCustomRepeatDialog(option: RepeatOption) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_custom_repeat_option, null)
        val dialog = android.app.AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setCancelable(true)
            .create()

        val etInterval = dialogView.findViewById<NumberPicker>(R.id.etInterval)
        val etTotal = dialogView.findViewById<NumberPicker>(R.id.etTotal)
        val btnSave = dialogView.findViewById<Button>(R.id.btnSave)

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

    private fun setupPicker(picker: NumberPicker, min: Int, max: Int) {
        picker.minValue = min
        picker.maxValue = max
        picker.wrapSelectorWheel = true
        picker.setFormatter { String.format("%02d", it) }
    }

}