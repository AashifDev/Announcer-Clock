package com.dzo.announcerclock.presentation.fragments.tts_fragment

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.dzo.announcerclock.databinding.FragmentTextToSpeechBinding
import com.dzo.announcerclock.presentation.fragments.tts_fragment.viewmodel.TtsViewModel
import com.dzo.announcerclock.utils.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class TextToSpeechFragment :
    BaseFragment<FragmentTextToSpeechBinding>(FragmentTextToSpeechBinding::inflate) {

    private val viewModel: TtsViewModel by viewModels()

    private lateinit var langAdapter: ArrayAdapter<String>
    private lateinit var voiceAdapter: ArrayAdapter<String>

    private var suppressLangSelection = false
    private var suppressVoiceSelection = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupListeners()
    }

   /* private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                // UI toggle
                binding.enableTimeSpeaking.isChecked = state.timeSpeakingEnabled
                binding.play.isEnabled = state.ttsReady
                binding.disableWhilePhoneCalls.isChecked = state.disableDuringPhoneCalls
                binding.disableDuringMusic.isChecked = state.disableWhilePlayingMusic
                binding.ttsLayout.isEnabled = state.timeSpeakingEnabled
                binding.selectedLangSpinner.isEnabled = state.timeSpeakingEnabled
                binding.selectedVoiceSpinner.isEnabled = state.timeSpeakingEnabled
                val alpha = if (state.timeSpeakingEnabled) 1f else 0.4f
                binding.ttsLayout.alpha = alpha
                binding.selectedLangSpinner.alpha = alpha
                binding.selectedVoiceSpinner.alpha = alpha

                // --- Setup Language Spinner ---
                if (state.languages.isNotEmpty() && !::langAdapter.isInitialized) {
                    val langNames = state.languages.map {
                        it.displayLanguage + if (it.country.isNotEmpty()) " (${it.displayCountry})" else ""
                    }
                    langAdapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        langNames
                    ).apply {
                        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    }

                    binding.selectedLangSpinner.adapter = langAdapter

                    val savedLang = state.settings.language
                    val savedLangIndex = state.languages.indexOfFirst {
                        "${it.language}_${it.country}" == savedLang
                    }
                    if (savedLangIndex >= 0) {
                        suppressLangSelection = true
                        binding.selectedLangSpinner.setSelection(savedLangIndex, false)
                        suppressLangSelection = false
                    }

                    binding.selectedLangSpinner.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                parent: AdapterView<*>?, view: View?, position: Int, id: Long
                            ) {
                                if (!suppressLangSelection && position in state.languages.indices) {
                                    viewModel.selectLanguage(state.languages[position])
                                }
                            }

                            override fun onNothingSelected(parent: AdapterView<*>?) {}
                        }
                }

                // --- Setup Voice Spinner ---
                val voiceNames = state.voices.map { it.name }

                if (!::voiceAdapter.isInitialized) {
                    voiceAdapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        voiceNames
                    ).apply {
                        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    }
                    binding.selectedVoiceSpinner.adapter = voiceAdapter
                } else {
                    // Update voices dynamically
                    suppressVoiceSelection = true
                    voiceAdapter.clear()
                    voiceAdapter.addAll(voiceNames)
                    voiceAdapter.notifyDataSetChanged()
                    suppressVoiceSelection = false
                }

                // Restore saved voice selection if exists
                val savedVoiceIndex = state.voices.indexOfFirst { it.name == state.settings.genderVoice }
                if (savedVoiceIndex >= 0 && binding.selectedVoiceSpinner.selectedItemPosition != savedVoiceIndex) {
                    suppressVoiceSelection = true
                    binding.selectedVoiceSpinner.setSelection(savedVoiceIndex, false)
                    suppressVoiceSelection = false
                }

                binding.selectedVoiceSpinner.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?, view: View?, position: Int, id: Long
                        ) {
                            if (!suppressVoiceSelection && position in state.voices.indices) {
                                viewModel.selectVoice(state.voices[position])
                            }
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {}
                    }
            }
        }
    }
*/
   private fun setupObservers() {
       lifecycleScope.launch {
           viewModel.state.collectLatest { state ->
               // UI toggle
               binding.enableTimeSpeaking.isChecked = state.timeSpeakingEnabled
               binding.play.isEnabled = state.ttsReady
               binding.disableWhilePhoneCalls.isChecked = state.disableDuringPhoneCalls
               binding.disableDuringMusic.isChecked = state.disableWhilePlayingMusic
               binding.ttsLayout.isEnabled = state.timeSpeakingEnabled
               binding.selectedLangSpinner.isEnabled = state.timeSpeakingEnabled
               binding.selectedVoiceSpinner.isEnabled = state.timeSpeakingEnabled
               val alpha = if (state.timeSpeakingEnabled) 1f else 0.4f
               binding.ttsLayout.alpha = alpha
               binding.selectedLangSpinner.alpha = alpha
               binding.selectedVoiceSpinner.alpha = alpha

               // --- Setup Language Spinner ---
               if (state.languages.isNotEmpty() && !::langAdapter.isInitialized) {
                   val langNames = state.languages.map {
                       it.displayLanguage + if (it.country.isNotEmpty()) " (${it.displayCountry})" else ""
                   }

                   langAdapter = ArrayAdapter(
                       requireContext(),
                       android.R.layout.simple_spinner_item,
                       langNames
                   ).apply {
                       setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                   }

                   binding.selectedLangSpinner.adapter = langAdapter

                   // ✅ Set default language: English (US)
                   val defaultLocale = Locale("en", "US")
                   val defaultLangIndex = state.languages.indexOfFirst {
                       it.language == defaultLocale.language && it.country == defaultLocale.country
                   }

                   val savedLang = state.settings.language
                   val selectedIndex = when {
                       savedLang.isNotBlank() -> state.languages.indexOfFirst { "${it.language}_${it.country}" == savedLang }
                       defaultLangIndex >= 0 -> defaultLangIndex
                       else -> 0
                   }

                   suppressLangSelection = true
                   binding.selectedLangSpinner.setSelection(selectedIndex.coerceAtLeast(0), false)
                   suppressLangSelection = false

                   binding.selectedLangSpinner.onItemSelectedListener =
                       object : AdapterView.OnItemSelectedListener {
                           override fun onItemSelected(
                               parent: AdapterView<*>?, view: View?, position: Int, id: Long
                           ) {
                               if (!suppressLangSelection && position in state.languages.indices) {
                                   viewModel.selectLanguage(state.languages[position])
                               }
                           }

                           override fun onNothingSelected(parent: AdapterView<*>?) {}
                       }
               }

               // --- Setup Voice Spinner ---
               val voiceNames = state.voices.map { it.name }

               if (!::voiceAdapter.isInitialized) {
                   voiceAdapter = ArrayAdapter(
                       requireContext(),
                       android.R.layout.simple_spinner_item,
                       voiceNames
                   ).apply {
                       setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                   }
                   binding.selectedVoiceSpinner.adapter = voiceAdapter
               } else {
                   // Update voices dynamically
                   suppressVoiceSelection = true
                   voiceAdapter.clear()
                   voiceAdapter.addAll(voiceNames)
                   voiceAdapter.notifyDataSetChanged()
                   suppressVoiceSelection = false
               }

               // Restore saved voice selection if exists
               val savedVoiceIndex = state.voices.indexOfFirst { it.name == state.settings.genderVoice }
               if (savedVoiceIndex >= 0 && binding.selectedVoiceSpinner.selectedItemPosition != savedVoiceIndex) {
                   suppressVoiceSelection = true
                   binding.selectedVoiceSpinner.setSelection(savedVoiceIndex, false)
                   suppressVoiceSelection = false
               }

               binding.selectedVoiceSpinner.onItemSelectedListener =
                   object : AdapterView.OnItemSelectedListener {
                       override fun onItemSelected(
                           parent: AdapterView<*>?, view: View?, position: Int, id: Long
                       ) {
                           if (!suppressVoiceSelection && position in state.voices.indices) {
                               viewModel.selectVoice(state.voices[position])
                           }
                       }

                       override fun onNothingSelected(parent: AdapterView<*>?) {}
                   }
           }
       }
   }

    private fun setupListeners() {
        binding.enableTimeSpeaking.setOnCheckedChangeListener { _, isChecked ->
            viewModel.toggleTimeSpeaking(isChecked)
        }

        binding.play.setOnClickListener {
            viewModel.speakCurrentTime()
        }

        binding.disableWhilePhoneCalls.setOnCheckedChangeListener { _, isChecked ->
            viewModel.toggleDisableDuringPhoneCalls(isChecked)
        }

        /*binding.disableDuringMusic.setOnCheckedChangeListener { _, isChecked ->
            viewModel.toggleDisableWhilePlayingMusic(isChecked)
        }*/
    }
}

