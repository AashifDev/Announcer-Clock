package com.dzo.announcerclock.presentation.fragments.sound_fragment

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.core.graphics.toColorInt
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dzo.announcerclock.R
import com.dzo.announcerclock.data.local_source.AppPreferences
import com.dzo.announcerclock.databinding.FragmentNotificationSoundBinding
import com.dzo.announcerclock.presentation.fragments.sound_fragment.adapter.SoundOptionAdapter
import com.dzo.announcerclock.presentation.fragments.sound_fragment.viewmodel.SoundOptionViewModel
import com.dzo.announcerclock.utils.core.BaseFragment
import com.dzo.announcerclock.utils.extension.showCustomSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NotificationSoundFragment :
    BaseFragment<FragmentNotificationSoundBinding>(FragmentNotificationSoundBinding::inflate) {

    private var colorHexx = ""
    @Inject
    lateinit var soundOptionAdapter: SoundOptionAdapter
    private val viewModel: SoundOptionViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView setup
        binding.soundOptionRecyclerView.adapter = soundOptionAdapter

        // Item click listener
        soundOptionAdapter.onItemClick = { soundOption ->
            // Only play/select if enabled
            if (binding.soundOptionRecyclerView.isEnabled) {
                viewModel.selectOption(soundOption)
            } else {
                requireActivity().showCustomSnackbar(
                    "Please enable notification sound!", R.drawable.notification,
                    colorString = colorHexx
                )
            }
        }

        // Collect sound options from ViewModel
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.soundOptions.collect { list ->
                    val updatedList = list.map {
                        it.copy(isSelected = it.soundResId == AppPreferences.getSoundOption()?.soundResId)
                    }
                    soundOptionAdapter.submitList(updatedList)
                }
            }
        }

        binding.enableNotification.setOnCheckedChangeListener { _, isChecked ->
            AppPreferences.saveNotificationEnabled(isChecked)
        }

        // 🔘 Toggle enable/disable
        binding.enableNotificationSound.setOnCheckedChangeListener { _, isChecked ->
            binding.soundOptionRecyclerView.isEnabled = isChecked
            binding.soundOptionRecyclerView.alpha = if (isChecked) 1f else 0.5f
            AppPreferences.saveNotificationSoundEnabled(isChecked)
        }

        // 🔘 Restore previous toggle state (if you store it)
        val isEnabled = AppPreferences.isNotificationEnabled() ?: true
        val isSoundEnabled = AppPreferences.isNotificationSoundEnabled() ?: true
        binding.enableNotification.isChecked = isEnabled
        binding.enableNotificationSound.isChecked = isSoundEnabled
        binding.soundOptionRecyclerView.isEnabled = isSoundEnabled
        binding.soundOptionRecyclerView.alpha = if (isSoundEnabled) 1f else 0.5f


        AppPreferences.ThemeManager.registerListener { colorHex ->
            safeExecute { binding ->
                colorHexx = colorHex
                binding.enableNotification.thumbTintList = ColorStateList.valueOf(colorHex.toColorInt())
                binding.enableNotificationSound.thumbTintList = ColorStateList.valueOf(colorHex.toColorInt())
            }

        }
    }
}
