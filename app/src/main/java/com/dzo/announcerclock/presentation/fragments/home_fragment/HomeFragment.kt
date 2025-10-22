package com.dzo.announcerclock.presentation.fragments.home_fragment

import android.app.Activity.RESULT_CANCELED
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.media.AudioManager
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.dzo.announcerclock.R
import com.dzo.announcerclock.data.local_source.AppPreferences
import com.dzo.announcerclock.databinding.FragmentHomeBinding
import com.dzo.announcerclock.domain.timer_usecase.AnnounceTimeUseCase
import com.dzo.announcerclock.presentation.fragments.home_fragment.model.TtsSettings
import com.dzo.announcerclock.presentation.fragments.home_fragment.viewmodel.TimerViewModel
import com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption
import com.dzo.announcerclock.presentation.fragments.repeat_option.viewmodel.RepeatOptionViewModel
import com.dzo.announcerclock.presentation.fragments.sound_fragment.model.SoundOption
import com.dzo.announcerclock.presentation.fragments.sound_fragment.viewmodel.SoundOptionViewModel
import com.dzo.announcerclock.presentation.fragments.tts_fragment.viewmodel.TtsViewModel
import com.dzo.announcerclock.utils.AnimationType
import com.dzo.announcerclock.utils.animateTimerText
import com.dzo.announcerclock.utils.core.BaseFragment
import com.dzo.announcerclock.utils.extension.showCustomSnackbar
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetSequence
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.InstallState
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import kotlin.math.roundToInt
import androidx.core.graphics.toColorInt
import com.dzo.announcerclock.utils.Utils.lighten

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    TextToSpeech.OnInitListener {

    @Inject
    lateinit var announceTimeUseCase: AnnounceTimeUseCase

    private val ttsViewModel: TtsViewModel by viewModels()

    private val repeatOptionViewModel: RepeatOptionViewModel by viewModels()
    private val soundOptionViewModel: SoundOptionViewModel by viewModels()
    private lateinit var audioManager: AudioManager
    private val timerViewModel: TimerViewModel by viewModels()
    private var tts: TextToSpeech? = null
    private var repeatOption: RepeatOption? = null
    private var soundOption: SoundOption? = null
    private var ttsReady = false
    private lateinit var ttsSettings: TtsSettings
    private var appUpdateManager: AppUpdateManager? = null
    private var listener: InstallStateUpdatedListener? = null
    private var activityResultLauncher: ActivityResultLauncher<*>? = null
    private var DAYS_FOR_FLEXIBLE_UPDATE: Int = 7
    private var DAYS_FOR_IMMEDIATE_UPDATE: Int = 14
    private var colorHexx = ""
    private val volumeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            // Sync slider when system volume changed (hardware buttons or other apps)
            activity?.runOnUiThread {
                syncSliderWithSystemVolume()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkForUpdate()

        lifecycleScope.launch {
            repeatOption = AppPreferences.getRepeatOption()
            soundOption = AppPreferences.getSoundOption()
            ttsSettings = AppPreferences.getTtsSettings()

            // Move all dependent UI logic INSIDE here
            setupUIAfterPrefsLoaded()

            observeTtsState()

        }

        tts = TextToSpeech(requireContext(), this)

        volumeRockerSetup()

        clickListeners()

        observeViewModel()

        requireContext().registerReceiver(
            volumeReceiver,
            IntentFilter("android.media.VOLUME_CHANGED_ACTION")
        )

        AppPreferences.ThemeManager.registerListener { colorHex ->
            safeExecute { binding ->
                colorHexx = colorHex
                applyDynamicColor()
            }
        }

       /* val listener: (String) -> Unit = { colorHex ->
            try {
                card.setCardBackgroundColor(Color.parseColor(colorHex))
            } catch (e: Exception) {
                card.setCardBackgroundColor(Color.WHITE)
            }
        }

        // register listener
        AppPreferences.ThemeManager.registerListener(listener)

        viewLifecycleOwner.lifecycle.addObserver(object: DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                AppPreferences.ThemeManager.unregisterListener()
            }
        })*/
    }

    private fun applyDynamicColor() {
        binding.header.setCardBackgroundColor(colorHexx.lighten(0.2f))
        binding.volRocker.tickActiveTintList = ColorStateList.valueOf(colorHexx.toColorInt())
        binding.customToggle.thumbTintList = ColorStateList.valueOf(colorHexx.toColorInt())
        binding.volRocker.trackActiveTintList = ColorStateList.valueOf(colorHexx.toColorInt())
        binding.volRocker.thumbTintList = ColorStateList.valueOf(colorHexx.toColorInt())
        binding.volRocker.trackInactiveTintList = ColorStateList.valueOf(colorHexx.lighten(0.5f))
        binding.circularProgress.setIndicatorColor(colorHexx.toColorInt())
        binding.circularProgress.trackColor = colorHexx.lighten(0.5f)
        binding.img.setColorFilter(colorHexx.toColorInt())
        binding.img1.setColorFilter(colorHexx.toColorInt())
        binding.img3.setColorFilter(colorHexx.toColorInt())
        binding.img4.setColorFilter(colorHexx.toColorInt())
    }

    private fun checkForUpdate() {
        appUpdateManager = AppUpdateManagerFactory.create(requireContext())

        val appUpdateInfoTask: Task<AppUpdateInfo> = appUpdateManager!!.appUpdateInfo

        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                // Request the update.
                startInAppUpdate(appUpdateInfo, AppUpdateType.FLEXIBLE)
            }
        }

        activityResultLauncher = registerForActivityResult<IntentSenderRequest, ActivityResult>(
            ActivityResultContracts.StartIntentSenderForResult()
        ) { result: ActivityResult ->
            // handle callback
            if (result.resultCode == RESULT_CANCELED) {
                //Toast.makeText(this, "Update canceled.", Toast.LENGTH_SHORT).show();
                Log.d("log", "Update canceled by user")
            } else if (result.resultCode != AppCompatActivity.RESULT_OK) {
                checkForUpdate()
            }
        }

        listener = InstallStateUpdatedListener { state: InstallState ->
            if (state.installStatus() == InstallStatus.DOWNLOADED) {
                // After the update is downloaded, show a notification
                // and request user confirmation to restart the app.
                popupSnackBarForCompleteUpdate()
            }
        }

        appUpdateManager!!.registerListener(listener!!)
    }

    private fun startInAppUpdate(appUpdateInfo: AppUpdateInfo, updateType: Int) {
        appUpdateManager!!.startUpdateFlowForResult(
            appUpdateInfo,
            activityResultLauncher as ActivityResultLauncher<IntentSenderRequest>,
            AppUpdateOptions.newBuilder(updateType).build()
        )
    }

    private fun popupSnackBarForCompleteUpdate() {
        /* val snackbar = Snackbar.make(requireView().findViewById(R.id.scrollView), "An update has just been downloaded.", Snackbar.LENGTH_INDEFINITE)
         snackbar.setAction(
             "RESTART"
         ) { view: View? -> appUpdateManager!!.completeUpdate() }
         snackbar.setActionTextColor(
             resources.getColor(R.color.white)
         )
         snackbar.show()*/

        requireActivity().showCustomSnackbar(
            "An update has just been downloaded.",
            actionText = "RESTART",
            iconRes = R.drawable.app_update,
            colorString = colorHexx
        ) {
            appUpdateManager!!.completeUpdate()
        }
    }

    private fun volumeRockerSetup() {
        audioManager = requireContext().getSystemService(Context.AUDIO_SERVICE) as AudioManager

        val maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        val curVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        val curPercent = ((curVol.toFloat() / maxVol.toFloat()) * 100f).roundToInt()
        val snapped = snapToStep(curPercent, 10)

        // Ensure slider step config matches and set initial snapped value
        binding.volRocker.valueFrom = 0f
        binding.volRocker.valueTo = 100f
        binding.volRocker.stepSize = 10f
        binding.volRocker.value = snapped.toFloat()
        binding.selectedVolume.text = "Volume: $snapped%"

        // When user moves slider
        binding.volRocker.addOnChangeListener { _, value, fromUser ->
            if (fromUser) {
                val percent = snapToStep(value.toInt(), 10)
                // Update UI text
                binding.selectedVolume.text = "Volume: $percent%"

                // Convert percent -> device volume
                val newVol = percentToDeviceVolume(percent, maxVol)
                audioManager.setStreamVolume(
                    AudioManager.STREAM_MUSIC,
                    newVol,
                    AudioManager.FLAG_SHOW_UI
                )
                // Because we set volume, hardware broadcast may also fire updating UI; that's ok
            }
        }
    }

    private fun clickListeners() {
        binding.setRepeatTime.setOnClickListener {
            findNavController().navigate(R.id.repeatOptionFragment)
        }

        binding.setSound.setOnClickListener {
            findNavController().navigate(R.id.notificationSoundFragment)
        }

        binding.setTtsSettings.setOnClickListener {
            findNavController().navigate(R.id.textToSpeechFragment)
        }

        binding.speakCurrentTime.setOnClickListener {
            lifecycleScope.launch {
                //speakTestMessage()
            }
        }
    }

    private fun setupUIAfterPrefsLoaded() {
        // Restore toggle
        lifecycleScope.launch {
            binding.customToggle.isChecked = AppPreferences.getToggleState()
        }

        if (binding.customToggle.isChecked) {
            if (repeatOption!!.id == 8) {
                lifecycleScope.launch {
                    timerViewModel.startCustomTimer(
                        AppPreferences.getRepeatOption()?.custom!!.first,
                        AppPreferences.getRepeatOption()?.custom!!.second
                    )
                }
            } else {
                lifecycleScope.launch {
                    timerViewModel.startTimer(
                        AppPreferences.getRepeatOption()?.valueInMinute ?: 1L
                    )
                }

            }
        }


        binding.customToggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (repeatOption?.id == 8) {
                    lifecycleScope.launch {
                        timerViewModel.startCustomTimer(
                            AppPreferences.getRepeatOption()?.custom!!.first,
                            AppPreferences.getRepeatOption()?.custom!!.second
                        )
                    }
                } else {
                    lifecycleScope.launch {
                        timerViewModel.startTimer(
                            AppPreferences.getRepeatOption()?.valueInMinute ?: 1L
                        )
                    }
                }
                AppPreferences.saveToggleState(true)
            } else {
                lifecycleScope.launch {
                    AppPreferences.saveToggleState(false)
                    timerViewModel.stopTimer()
                }
            }
        }

    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.more, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.more -> {
                showBottomSheet(requireContext())
                true
            }

            else -> false
        }
    }

    private fun showIntroGuide() {
        TapTargetSequence(requireActivity())
            .targets(
                TapTarget.forView(
                    binding.customToggle,
                    "Start",
                    "Enable this toggle for start timer."
                )
                    .outerCircleColor(R.color.light_blue)
                    .targetCircleColor(R.color.white)
                    .titleTextColor(R.color.black)
                    .descriptionTextColor(R.color.black)
                    .tintTarget(true)
                    .cancelable(false)
                    .transparentTarget(true),

                TapTarget.forView(binding.setRepeatTime, "Repeat Time", "Set repeat time for timer")
                    .targetCircleColor(R.color.white)
                    .outerCircleColor(R.color.light_blue)
                    .titleTextColor(R.color.black)
                    .descriptionTextColor(R.color.black)
                    .tintTarget(true)
                    .cancelable(false)
                    .transparentTarget(true),

                TapTarget.forView(
                    binding.setSound,
                    "Notification Sound",
                    "Set your custom notification sound"
                )
                    .targetCircleColor(R.color.white)
                    .outerCircleColor(R.color.light_blue)
                    .titleTextColor(R.color.black)
                    .descriptionTextColor(R.color.black)
                    .tintTarget(true)
                    .cancelable(false)
                    .transparentTarget(true),

                TapTarget.forView(
                    binding.setTtsSettings,
                    "Text To Speech",
                    "Set Text to speech like language and voice etc"
                )
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

    /* private suspend fun speakTestMessage() {
         if (!ttsReady) {
             repeat(5) {
                 delay(500)
                 if (ttsReady) return@repeat
             }
         }

         val settings = AppPreferences.getTtsSettings()
         tts?.setPitch(settings.pitch)
         tts?.setSpeechRate(settings.rate)

         val localeParts = settings.language.split("_")
         val locale =
             if (localeParts.size == 2) Locale(localeParts[0], localeParts[1]) else Locale.getDefault()
         tts?.language = locale

         val availableVoices = tts?.voices?.filter { it.locale == locale } ?: emptyList()
         val selectedVoice = availableVoices.firstOrNull { it.name.contains(settings.genderVoice, true) }
             ?: availableVoices.firstOrNull()
         selectedVoice?.let { tts?.voice = it }

         val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())

         withContext(Dispatchers.Main) {
             tts?.speak(
                 formatter.format(Date()),
                 TextToSpeech.QUEUE_FLUSH,
                 null,
                 null
             )
         }
     }
 */

    private suspend fun speakTestMessage() {
        if (!ttsReady) {
            repeat(5) {
                delay(500)
                if (ttsReady) return@repeat
            }
        }

        val settings = AppPreferences.getTtsSettings()
        tts?.setPitch(settings.pitch)
        tts?.setSpeechRate(settings.rate)

        val localeParts = settings.language.split("_")
        val locale = if (localeParts.size == 2) {
            Locale(localeParts[0], localeParts[1])
        } else {
            // 👇 Default to English if not set properly
            Locale("en", "US")
        }

        tts?.language = locale

        val availableVoices = tts?.voices?.filter { it.locale == locale } ?: emptyList()
        val selectedVoice =
            availableVoices.firstOrNull { it.name.contains(settings.genderVoice, true) }
                ?: availableVoices.firstOrNull()
        selectedVoice?.let { tts?.voice = it }

        val formatter = SimpleDateFormat("hh:mm a", Locale("en", "US"))

        withContext(Dispatchers.Main) {
            tts?.speak(
                formatter.format(Date()),
                TextToSpeech.QUEUE_FLUSH,
                null,
                null
            )
        }
    }

    private fun observeTtsState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                ttsViewModel.state.collect { uiState ->
                    if (uiState.ttsReady && uiState.languages.isNotEmpty()) {

                        val currentLang = uiState.settings
                        if (currentLang.language.isEmpty()) {
                            val defaultLocale = Locale("en", "US")
                            val langNames = uiState.languages.map {
                                it.displayLanguage + if (it.country.isNotEmpty()) " (${it.displayCountry})" else ""
                            }

                            ttsViewModel.selectLanguage((langNames.firstOrNull() as Locale))

                        }

                        if (uiState.voices.isNotEmpty() && uiState.settings.genderVoice.isBlank()) {
                            val firstVoice = uiState.voices.first()
                            ttsViewModel.selectVoice(firstVoice)
                        }

                        println("✅ TTS initialized successfully with language=${uiState.settings.language}")
                    }
                }
            }
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    timerViewModel.progress.collect {
                        binding.circularProgress.progress = it
                    }
                }
                launch {
                    timerViewModel.timeText.collect {
                        if (AppPreferences.getToggleState()) {
                            animateTimerText(
                                binding.timerText,
                                requireContext(),
                                it,
                                colorInt = colorHexx.toColorInt(),
                                AnimationType.SMOOTH
                            )

                        } else {
                            binding.timerText.text = "OFF"
                        }
                    }
                }
                launch {
                    timerViewModel.announcement.collect { msg ->
                        //msg?.let { tts.speak(it, TextToSpeech.QUEUE_FLUSH, null, null) }
                    }
                }
            }
        }
    }

    fun showBottomSheet(context: Context) {
        val dialog = BottomSheetDialog(context, R.style.CustomBottomSheetDialogTheme)
        val view = LayoutInflater.from(context).inflate(R.layout.more_layout, null)
        dialog.setContentView(view)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)

        val params = (view.parent as View).layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(32, 16, 32, 16) // left, top, right, bottom in pixels
        (view.parent as View).layoutParams = params

        val ourApp = dialog.findViewById<LinearLayoutCompat>(R.id.ourApps)
        val appTheme = dialog.findViewById<LinearLayoutCompat>(R.id.appTheme)
        val rateApp = dialog.findViewById<LinearLayoutCompat>(R.id.rateApp)
        val shareApp = dialog.findViewById<LinearLayoutCompat>(R.id.shareApp)
        val txtVersionName = dialog.findViewById<AppCompatTextView>(R.id.txtVersionName)
        val ourAppImg = dialog.findViewById<AppCompatImageView>(R.id.ourAppImg)
        val themeImg = dialog.findViewById<AppCompatImageView>(R.id.themeImg)
        val rateAppImg = dialog.findViewById<AppCompatImageView>(R.id.rateAppImg)
        val shareImg = dialog.findViewById<AppCompatImageView>(R.id.shareImg)
        val versionImg = dialog.findViewById<AppCompatImageView>(R.id.versionImg)
        val more = dialog.findViewById<AppCompatTextView>(R.id.more)

        ourAppImg!!.setColorFilter(colorHexx.toColorInt())
        themeImg!!.setColorFilter(colorHexx.toColorInt())
        rateAppImg!!.setColorFilter(colorHexx.toColorInt())
        shareImg!!.setColorFilter(colorHexx.toColorInt())
        versionImg!!.setColorFilter(colorHexx.toColorInt())
        more!!.setTextColor(colorHexx.toColorInt())
        txtVersionName!!.setTextColor(colorHexx.toColorInt())

        ourApp?.setOnClickListener {
            findNavController().navigate(R.id.ourAppFragment)
            dialog.dismiss()
        }

        rateApp?.setOnClickListener {
            //openPlayStore(requireContext(),"")
            this.toast(requireContext(), "Share App")

        }
        shareApp?.setOnClickListener {
            this.toast(requireContext(), "Share App")
        }

        appTheme?.setOnClickListener {
            findNavController().navigate(R.id.appThemeFragment)
            dialog.dismiss()
        }

        val packageInfo =
            requireContext().packageManager.getPackageInfo(requireContext().packageName, 0)
        val versionName = packageInfo.versionName
        val versionCode = packageInfo.longVersionCode

        txtVersionName!!.text = "App Version: $versionName ($versionCode)"

        dialog.show()
    }

    fun updateAnimatedTimerText(it: String) {
        binding.timerText.animate()
            .scaleX(0.8f)
            .scaleY(0.8f)
            .alpha(0f)
            .setDuration(120)
            .withEndAction {
                // Update styled text after animation fades out
                binding.timerText.text = SpannableStringBuilder().apply {
                    val firstPart = "reset in\n"
                    append(firstPart)
                    setSpan(
                        ForegroundColorSpan(
                            ContextCompat.getColor(requireContext(), R.color.blue)
                        ),
                        0,
                        firstPart.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    setSpan(
                        AbsoluteSizeSpan(15, true),
                        0,
                        firstPart.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    append(it)
                }

                // Animate back in
                binding.timerText.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .alpha(1f)
                    .setDuration(180)
                    .setInterpolator(android.view.animation.OvershootInterpolator())
                    .start()
            }
            .start()
    }

    override fun onStart() {
        super.onStart()
        /*requireActivity().showCustomSnackbar(
            "An update has just been downloaded.",
            actionText = "RESTART",
            iconRes = R.drawable.app_update,
            colorString = colorHexx.toString()
        ) {
            appUpdateManager!!.completeUpdate()
        }*/
        val darkMode = AppPreferences.isDarkThemeEnabled()
        //setUiThemeMode()
        setThemeMode(darkMode == true)

        if (repeatOption != null && soundOption != null) {
            binding.selectedRepeatTime.text = repeatOption!!.title
            binding.selectedSound.text = soundOption!!.title
        } else {
            // default setup...
            val defaultRepeatOption = repeatOptionViewModel.repeatOptions.value.firstOrNull()
            defaultRepeatOption?.let {
                binding.selectedRepeatTime.text = it.title
                AppPreferences.saveRepeatOption(it.copy(isSelected = true))

            }

            // Set default SoundOption
            val defaultSoundOption = soundOptionViewModel.soundOptions.value.firstOrNull()
            defaultSoundOption?.let {
                binding.selectedSound.text = it.title
                AppPreferences.saveSoundOption(it.copy(isSelected = true))

            }
        }

        if (!AppPreferences.isFirstLaunch()!!) {
            showIntroGuide()
        }
    }

    private fun setUiThemeMode() {
        val savedPref = AppPreferences.isDarkThemeEnabled()
        val isSystemDark =
            (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

        if (savedPref != null) {
            AppCompatDelegate.setDefaultNightMode(
                if (savedPref) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
        } else {
            AppCompatDelegate.setDefaultNightMode(
                if (isSystemDark) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
        }
    }
    private fun setThemeMode(isDark: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (isDark) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }
    override fun onResume() {
        super.onResume()
        appUpdateManager!!
            .appUpdateInfo
            .addOnSuccessListener { appUpdateInfo: AppUpdateInfo ->
                if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                    popupSnackBarForCompleteUpdate()
                }
            }


    }

    private fun updateSlider() {
        val current = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        binding.volRocker.value = current.toFloat()
        binding.selectedVolume.text = "$current%"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        try {
            requireContext().unregisterReceiver(volumeReceiver)
        } catch (e: IllegalArgumentException) {
            // ignore if already unregistered
        }
    }

    // Helper: convert percent (0..100) to device volume (0..maxVol)
    private fun percentToDeviceVolume(percent: Int, maxVol: Int): Int {
        val vol = (percent / 100f * maxVol.toFloat()).roundToInt()
        return vol.coerceIn(0, maxVol)
    }

    // Helper: snap to nearest step (e.g., 10)
    private fun snapToStep(value: Int, step: Int): Int {
        val div = (value.toDouble() / step).roundToInt()
        return (div * step).coerceIn(0, 100)
    }

    // When external change happens, update slider/text to match system volume
    private fun syncSliderWithSystemVolume() {
        val maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        val curVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        val curPercent = ((curVol.toFloat() / maxVol.toFloat()) * 100f).roundToInt()
        val snapped = snapToStep(curPercent, 10)

        // Set slider safely (this triggers listener with fromUser=false)
        binding.volRocker.value = snapped.toFloat()
        binding.selectedVolume.text = "Volume: $snapped%"

        if (snapped.equals(100)) requireActivity().showCustomSnackbar(
            "You have reached max volume",
            R.drawable.sound,
            colorString = colorHexx.toString()
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        timerViewModel.stopTimer()
        println("🔥 TimerService destroyed")
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.language = Locale.getDefault()
            ttsReady = true
        }
    }
}