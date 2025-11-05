package com.dzo.announcerclock.utils.helper

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.toColorInt
import com.dzo.announcerclock.R
import com.dzo.announcerclock.data.local_source.AppPreferences
import com.dzo.announcerclock.databinding.SchedultTimerBottomSheetBinding
import com.dzo.announcerclock.presentation.fragments.home_fragment.model.ScheduleTimerModel
import com.dzo.announcerclock.utils.Utils
import com.dzo.announcerclock.utils.Utils.formattedTime
import com.dzo.announcerclock.utils.Utils.lighten
import com.dzo.announcerclock.utils.Utils.milliSecondToTime
import com.dzo.announcerclock.utils.extension.getRippleResource
import com.dzo.announcerclock.utils.extension.showColoredToast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ScheduleTimerBottomSheet(
    private val colorHex: String,
    private val onDataUpdated: () -> Unit
) : BottomSheetDialogFragment() {

    private var _binding: SchedultTimerBottomSheetBinding? = null
    private val binding get() = _binding!!
    private var startTime: Long? = null
    private var endTime: Long? = null
    private var repeatEvery: Long? = null
    private var startCal: Calendar? = null
    private var endCal: Calendar? = null

    private val rippleTypedValue by lazy {
        TypedValue().apply {
            requireContext().theme.resolveAttribute(
                android.R.attr.selectableItemBackgroundBorderless, this, true
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SchedultTimerBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    /*override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), R.style.CustomBottomSheetDialogTheme)
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startCal = Calendar.getInstance()
        endCal = Calendar.getInstance()

        loadExistingSchedule()
        setupListeners()

        binding.imgStart.setColorFilter(colorHex.toColorInt())
        binding.imgEnd.setColorFilter(colorHex.toColorInt())
        binding.txtStartTime.setTextColor(colorHex.toColorInt())
        binding.txtEndTime.setTextColor(colorHex.toColorInt())
        binding.txtRepeatEvery.setTextColor(colorHex.toColorInt())
        binding.upArrow.setColorFilter(colorHex.toColorInt())
        binding.txtStart.setTextColor(colorHex.toColorInt())
        binding.txtEnd.setTextColor(colorHex.toColorInt())

        binding.saveSchedule.backgroundTintList = ColorStateList.valueOf(colorHex.toColorInt())

        if (AppPreferences.isDarkThemeEnabled() != true) {
            binding.bgStart.background.setTint(colorHex.lighten(0.9f))
            binding.bgEnd.background.setTint(colorHex.lighten(0.9f))
        } else {
            binding.txtSetRepeatEveryMinute.setTextColor(colorHex.toColorInt())
        }


    }

    private fun setupListeners() = with(binding) {
        setStartTime.setBackgroundResource(requireContext().getRippleResource(true))
        setEndTime.setBackgroundResource(requireContext().getRippleResource(true))
        txtSetRepeatEveryMinute.setBackgroundResource(rippleTypedValue.resourceId)

        setStartTime.setOnClickListener {
            showStartTimePicker(binding.txtSetStartTime)
        }

        setEndTime.setOnClickListener {
            showEndTimePicker(
                binding.txtSetEndTime,
                binding.txtSetRepeatEveryMinute
            )
        }

        txtSetRepeatEveryMinute.setOnClickListener { showMinutePickerDialog(binding.txtSetRepeatEveryMinute) }

        saveSchedule.setOnClickListener {
            if (startTime != null && endTime != null && repeatEvery != null) {
                val newSchedule = ScheduleTimerModel(true, startTime!!, endTime!!, repeatEvery!!)
                AppPreferences.saveScheduleTime(newSchedule)
                onDataUpdated.invoke()
                dismiss()
            } else {
                requireContext().showColoredToast(
                    "Please set start and end time first",
                    colorHex.lighten(0.9f),
                    colorHex.toColorInt()
                )
            }
        }
    }

    private fun loadExistingSchedule() {
        val existing = AppPreferences.getScheduleTime()
        if (existing != null) {
            startTime = existing.startTimeMillis
            endTime = existing.endTimeMillis
            repeatEvery = existing.intervalMillis

            binding.txtSetStartTime.text = milliSecondToTime(startTime!!)
            binding.txtSetEndTime.text = milliSecondToTime(endTime!!)
            "$repeatEvery minute".also { binding.txtSetRepeatEveryMinute.text = it }
        }else{
           /* val currentTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())

            binding.txtSetStartTime.apply {
                text = currentTime
            }
            binding.txtSetEndTime.apply {
                text = currentTime
            }*/
        }
    }

    private fun showStartTimePicker(txtSetStartTime: AppCompatTextView?) {
        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H) // 12-hour format with AM/PM
            .setHour(startCal!!.get(Calendar.HOUR_OF_DAY))
            .setMinute(startCal!!.get(Calendar.MINUTE))
            .setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD)
            .setTitleText("Select Start Time".toUpperCase()).build()

        picker.addOnPositiveButtonClickListener {
            val hour = picker.hour
            val minute = picker.minute

            startCal = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
            startTime = startCal!!.timeInMillis
            val formattedTime = formattedTime(startCal)
            txtSetStartTime!!.text = formattedTime
        }

        picker.show(parentFragmentManager, "start_time_picker")
    }

    private fun showEndTimePicker(
        txtSetEndTime: AppCompatTextView?, txtSetRepeatEveryMinute: AppCompatTextView?
    ) {
        val picker = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(endCal!!.get(Calendar.HOUR_OF_DAY)).setMinute(endCal!!.get(Calendar.MINUTE))
            .setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD)
            .setTitleText("Select End Time".toUpperCase()).build()

        picker.addOnPositiveButtonClickListener {
            val hour = picker.hour
            val minute = picker.minute

            endCal = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
            endTime = endCal!!.timeInMillis

            val formattedTime = formattedTime(endCal)
            txtSetEndTime!!.text = formattedTime

            showMinutePickerDialog(txtSetRepeatEveryMinute)
        }

        picker.show(parentFragmentManager, "end_time_picker")
    }


    private fun showMinutePickerDialog(txtSetRepeatEveryMinute: AppCompatTextView?) {
        val evenNumbers = (2..60 step 2).map { it.toString() }.toTypedArray()

        val numberPicker = NumberPicker(requireContext()).apply {
            minValue = 0
            maxValue = evenNumbers.size - 1
            displayedValues = evenNumbers
            wrapSelectorWheel = true
        }
        setNumberPickerDividerColor(numberPicker, colorHex.toColorInt())

        AlertDialog.Builder(requireContext()).setTitle("SELECT MINUTES").setView(numberPicker)
            .setPositiveButton("OK") { _, _ ->
                val selectedValue = evenNumbers[numberPicker.value].toInt()
                repeatEvery = selectedValue.toLong()
                "$selectedValue minute".also {
                    txtSetRepeatEveryMinute!!.text = it
                }
            }.setNegativeButton("Cancel") { dialog, _ ->
                repeatEvery = 1
            }.show()
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
