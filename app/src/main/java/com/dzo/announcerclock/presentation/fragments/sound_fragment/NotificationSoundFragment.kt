package com.dzo.announcerclock.presentation.fragments.sound_fragment

import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.OpenableColumns
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.toColorInt
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dzo.announcerclock.R
import com.dzo.announcerclock.data.local_source.AppPreferences
import com.dzo.announcerclock.databinding.FragmentNotificationSoundBinding
import com.dzo.announcerclock.presentation.fragments.sound_fragment.adapter.SoundOptionAdapter
import com.dzo.announcerclock.presentation.fragments.sound_fragment.model.SoundOption
import com.dzo.announcerclock.presentation.fragments.sound_fragment.viewmodel.SoundOptionViewModel
import com.dzo.announcerclock.utils.Utils.lighten
import com.dzo.announcerclock.utils.core.BaseFragment
import com.dzo.announcerclock.utils.extension.showColoredToast
import com.dzo.announcerclock.utils.extension.showCustomSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NotificationSoundFragment :
    BaseFragment<FragmentNotificationSoundBinding>(FragmentNotificationSoundBinding::inflate) {

    @Inject
    lateinit var soundOptionAdapter: SoundOptionAdapter

    private val viewModel: SoundOptionViewModel by viewModels()
    private var colorHex = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupToggles()
        collectSoundOptions()
        restorePreviousState()
        observeThemeChanges()

        binding.llCompatNotification.setOnClickListener { }
        binding.llNotificationSound.setOnClickListener { }


    }

    private fun setupRecyclerView() {
        binding.soundOptionRecyclerView.adapter = soundOptionAdapter

        soundOptionAdapter.onItemClick = { soundOption ->
            if (binding.soundOptionRecyclerView.isEnabled) {
                viewModel.selectOption(soundOption)
            } else {
                requireActivity().showCustomSnackBar(
                    message = "Please enable notification sound!",
                    iconRes = R.drawable.notification,
                    colorString = colorHex
                )
            }
        }

        /*soundOptionAdapter.onDeleteClick = { soundOption ->
            viewModel.removeSound(soundOption)
            requireActivity().showColoredToast(
                "Removed: ${soundOption.title}",
                colorHex.lighten(0.5f),
                colorHex.toColorInt()
            )
        }*/

    }

    private fun setupToggles() {
        // Notification toggle
        binding.enableNotification.setOnCheckedChangeListener { _, isChecked ->
            AppPreferences.saveNotificationEnabled(isChecked)
            binding.enableNotificationSound.apply {
                isEnabled = isChecked
                if (!isChecked) this.isChecked = false
            }
            if (isChecked)
                enableNotificationRipple()
            else
                enableNotificationRipple()
        }

        // Notification sound toggle
        binding.enableNotificationSound.setOnCheckedChangeListener { toggle, isChecked ->
            AppPreferences.saveNotificationSoundEnabled(isChecked)
            binding.soundOptionRecyclerView.apply {
                isEnabled = isChecked
                //alpha = if (isChecked) 1f else 0.5f
            }
            binding.soundCardView.alpha = if (isChecked) 1f else 0.5f

            if (isChecked)
                enableNotificationSoundRipple()
            else
                enableNotificationSoundRipple()
        }
    }

    // ---------------------- Collect Data ----------------------

    private fun collectSoundOptions() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.soundOptions.collect { list ->
                    val selectedSound = AppPreferences.getSoundOption()
                    val updatedList = list.map {
                        it.copy(isSelected = it.id == selectedSound?.id)
                    }
                    soundOptionAdapter.submitList(updatedList)
                }
            }
        }
    }

    // ---------------------- Restore Saved State ----------------------

    private fun restorePreviousState() {
        val isNotificationEnabled = AppPreferences.isNotificationEnabled() ?: false
        val isSoundEnabled = AppPreferences.isNotificationSoundEnabled() ?: false

        binding.enableNotification.isChecked = isNotificationEnabled
        binding.enableNotificationSound.isChecked = isSoundEnabled
        binding.enableNotificationSound.isEnabled = isNotificationEnabled

        binding.soundCardView.alpha = if (isSoundEnabled) 1f else 0.5f
        binding.soundOptionRecyclerView.apply {
            //alpha = if (isSoundEnabled) 1f else 0.5f
            isEnabled = isSoundEnabled
            isClickable = isSoundEnabled
            isFocusable = isSoundEnabled
            isFocusableInTouchMode = isSoundEnabled
        }
    }

    // ---------------------- Theme Updates ----------------------

    private fun observeThemeChanges() {
        AppPreferences.ThemeManager.registerListener { colorHexValue ->
            safeExecute { binding ->
                colorHex = colorHexValue
                val colorInt = colorHexValue.toColorInt()
                binding.enableNotification.thumbTintList = ColorStateList.valueOf(colorInt)
                binding.enableNotificationSound.thumbTintList = ColorStateList.valueOf(colorInt)
                binding.imgNotificationSound.setColorFilter(colorInt)
                binding.imgEnableNotification.setColorFilter(colorInt)
            }
        }
    }

    private fun enableNotificationRipple() {
        binding.llCompatNotification.isPressed = true
        binding.llCompatNotification.postDelayed({
            binding.llCompatNotification.isPressed = false
        }, 200)
    }

    private fun enableNotificationSoundRipple() {
        binding.llNotificationSound.isPressed = true
        binding.llNotificationSound.postDelayed({
            binding.llNotificationSound.isPressed = false
        }, 200)
    }
}
