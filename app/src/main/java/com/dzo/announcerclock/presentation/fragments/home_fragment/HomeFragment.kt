package com.dzo.announcerclock.presentation.fragments.home_fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.app.Activity.RESULT_CANCELED
import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.RECEIVER_NOT_EXPORTED
import android.content.Intent
import android.content.IntentFilter
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.NumberPicker
import android.widget.ScrollView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.registerReceiver
import androidx.core.graphics.toColorInt
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dzo.announcerclock.R
import com.dzo.announcerclock.data.local_source.AppPreferences
import com.dzo.announcerclock.databinding.FragmentHomeBinding
import com.dzo.announcerclock.databinding.MoreLayoutBinding
import com.dzo.announcerclock.domain.timer_usecase.AnnounceTimeUseCase
import com.dzo.announcerclock.presentation.fragments.home_fragment.model.ScheduleTimerModel
import com.dzo.announcerclock.presentation.fragments.home_fragment.model.TtsSettings
import com.dzo.announcerclock.presentation.fragments.home_fragment.viewmodel.ScheduleTimerViewModel
import com.dzo.announcerclock.presentation.fragments.home_fragment.viewmodel.TimerViewModel
import com.dzo.announcerclock.presentation.fragments.repeat_option.model.RepeatOption
import com.dzo.announcerclock.presentation.fragments.repeat_option.viewmodel.RepeatOptionViewModel
import com.dzo.announcerclock.presentation.fragments.sound_fragment.model.SoundOption
import com.dzo.announcerclock.presentation.fragments.sound_fragment.viewmodel.SoundOptionViewModel
import com.dzo.announcerclock.presentation.fragments.tts_fragment.viewmodel.TtsViewModel
import com.dzo.announcerclock.utils.Constants
import com.dzo.announcerclock.utils.Utils
import com.dzo.announcerclock.utils.helper.AnimationType
import com.dzo.announcerclock.utils.Utils.lighten
import com.dzo.announcerclock.utils.helper.animateTimerText
import com.dzo.announcerclock.utils.core.BaseFragment
import com.dzo.announcerclock.utils.extension.getRippleResource
import com.dzo.announcerclock.utils.extension.showColoredToast
import com.dzo.announcerclock.utils.extension.showCustomSnackBar
import com.dzo.announcerclock.utils.helper.InAppReviewUtil
import com.dzo.announcerclock.utils.helper.ScheduleTimerBottomSheet
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetView
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
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
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import kotlin.math.roundToInt
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.model.KeyPath
import com.airbnb.lottie.value.LottieValueCallback
import com.dzo.announcerclock.utils.Utils.darken

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate){

    @Inject
    lateinit var announceTimeUseCase: AnnounceTimeUseCase

    private val ttsViewModel: TtsViewModel by viewModels()
    private val repeatOptionViewModel: RepeatOptionViewModel by viewModels()
    private val soundOptionViewModel: SoundOptionViewModel by viewModels()
    private lateinit var audioManager: AudioManager
    private val timerViewModel: TimerViewModel by viewModels()
    private val scheduleTimerModel: ScheduleTimerViewModel by viewModels()
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
    private var startCal: Calendar? = null
    private var endCal: Calendar? = null
    private var schTime: ScheduleTimerModel? = null

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

        startCal = Calendar.getInstance()
        endCal = Calendar.getInstance()

        binding.setRepeatTime.setBackgroundResource(requireContext().getRippleResource(false))
        binding.setSound.setBackgroundResource(requireContext().getRippleResource(false))
        binding.setTtsSettings.setBackgroundResource(requireContext().getRippleResource(false))
        binding.newScheduling.setBackgroundResource(requireContext().getRippleResource(true))
        //binding.setVolume.setBackgroundResource(requireContext().getRippleResource(false))

        checkForUpdate()

        lifecycleScope.launch {
            repeatOption = AppPreferences.getRepeatOption()
            soundOption = AppPreferences.getSoundOption()
            ttsSettings = AppPreferences.getTtsSettings()

            // Move all dependent UI logic INSIDE here
            setupUIAfterPrefsLoaded()

            observeTtsState()

        }

        //tts = TextToSpeech(requireContext(), this)

        volumeRockerSetup()

        clickListeners()

        observeViewModel()

        requireContext().registerReceiver(
            volumeReceiver, IntentFilter("android.media.VOLUME_CHANGED_ACTION")
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
        val background = binding.outlineHeader.background as GradientDrawable
        background.setStroke(3, ColorStateList.valueOf(colorHexx.lighten(0.6f)), 10f, 10f)
        binding.header.setCardBackgroundColor(colorHexx.lighten(0.2f))
        //binding.outlineHeader.backgroundTintList = ColorStateList.valueOf(colorHexx.lighten(0.5f))
        binding.customToggle.thumbTintList = ColorStateList.valueOf(colorHexx.toColorInt())
        binding.enableScheduling.thumbTintList = ColorStateList.valueOf(colorHexx.toColorInt())

        binding.mediaVolRocker.tickActiveTintList = ColorStateList.valueOf(colorHexx.toColorInt())
        binding.mediaVolRocker.trackActiveTintList = ColorStateList.valueOf(colorHexx.toColorInt())
        binding.mediaVolRocker.thumbTintList = ColorStateList.valueOf(colorHexx.toColorInt())
        binding.mediaVolRocker.trackInactiveTintList = ColorStateList.valueOf(colorHexx.lighten(0.5f))

        binding.notificationVolRocker.tickActiveTintList = ColorStateList.valueOf(colorHexx.toColorInt())
        binding.notificationVolRocker.trackActiveTintList = ColorStateList.valueOf(colorHexx.toColorInt())
        binding.notificationVolRocker.thumbTintList = ColorStateList.valueOf(colorHexx.toColorInt())
        binding.notificationVolRocker.trackInactiveTintList = ColorStateList.valueOf(colorHexx.lighten(0.5f))
        binding.circularProgress.setIndicatorColor(colorHexx.toColorInt())
        binding.circularProgress.trackColor = colorHexx.lighten(0.5f)
        binding.img.setColorFilter(colorHexx.toColorInt())
        binding.img1.setColorFilter(colorHexx.toColorInt())
        binding.img3.setColorFilter(colorHexx.toColorInt())
        binding.img4.setColorFilter(colorHexx.toColorInt())
        binding.img7.setColorFilter(colorHexx.toColorInt())

        binding.placeHolder.addValueCallback(
            KeyPath("**"), // ** = all layers; or target specific ones by name
            LottieProperty.COLOR_FILTER,
            LottieValueCallback(
                PorterDuffColorFilter(
                    colorHexx.toColorInt(),
                    PorterDuff.Mode.SRC_ATOP
                )
            )
        )
    }

    private fun checkForUpdate() {
        appUpdateManager = AppUpdateManagerFactory.create(requireContext())

        val appUpdateInfoTask: Task<AppUpdateInfo> = appUpdateManager!!.appUpdateInfo

        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && appUpdateInfo.isUpdateTypeAllowed(
                    AppUpdateType.IMMEDIATE
                )
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

    private fun popupSnackBarForCompleteUpdate() {/* val snackbar = Snackbar.make(requireView().findViewById(R.id.scrollView), "An update has just been downloaded.", Snackbar.LENGTH_INDEFINITE)
         snackbar.setAction(
             "RESTART"
         ) { view: View? -> appUpdateManager!!.completeUpdate() }
         snackbar.setActionTextColor(
             resources.getColor(R.color.white)
         )
         snackbar.show()*/

        requireActivity().showCustomSnackBar(
            "An update has just been downloaded.",
            actionText = "RESTART",
            iconRes = R.drawable.app_update,
            colorString = colorHexx,
        ) {
            appUpdateManager!!.completeUpdate()
        }
    }

    private fun volumeRockerSetup() {
        audioManager = requireContext().getSystemService(Context.AUDIO_SERVICE) as AudioManager

        // Define audio stream types
        val musicStream = AudioManager.STREAM_MUSIC
        val notificationStream = AudioManager.STREAM_NOTIFICATION

        // Get max and current volumes
        val maxMusicVol = audioManager.getStreamMaxVolume(musicStream)
        val curMusicVol = audioManager.getStreamVolume(musicStream)
        val maxNotificationVol = audioManager.getStreamMaxVolume(notificationStream)
        val curNotificationVol = audioManager.getStreamVolume(notificationStream)

        // Convert to %
        val curMusicPercent = ((curMusicVol.toFloat() / maxMusicVol.toFloat()) * 100f).roundToInt()
        val curNotificationPercent = ((curNotificationVol.toFloat() / maxNotificationVol.toFloat()) * 100f).roundToInt()

        // Snap to steps of 10
        val snappedMusic = snapToStep(curMusicPercent, 10)
        val snappedNotification = snapToStep(curNotificationPercent, 10)

        // --- MUSIC SLIDER ---
        binding.mediaVolRocker.apply {
            valueFrom = 0f
            valueTo = 100f
            stepSize = 10f
            value = snappedMusic.toFloat()
        }
        "vol: $snappedMusic%".also { binding.selectedMediaVolume.text = it }
        "vol: $snappedMusic%".also { binding.txtCurrentVol.text = it }

        // --- NOTIFICATION SLIDER ---
        binding.notificationVolRocker.apply {
            valueFrom = 0f
            valueTo = 100f
            stepSize = 10f
            value = snappedNotification.toFloat()
        }
        "vol: $snappedNotification%".also { binding.selectedNotificationVolume.text = it }

        // Handle user interaction
        binding.mediaVolRocker.addOnChangeListener { _, value, fromUser ->
            if (fromUser) {
                val percent = snapToStep(value.toInt(), 10)
                "vol: $percent%".also { binding.selectedMediaVolume.text = it }
                "vol: $percent%".also { binding.txtCurrentVol.text = it }

                val newVol = percentToDeviceVolume(percent, maxMusicVol)
                audioManager.setStreamVolume(musicStream, newVol, AudioManager.FLAG_SHOW_UI)
            }
        }

        binding.notificationVolRocker.addOnChangeListener { _, value, fromUser ->
            if (fromUser) {
                val percent = snapToStep(value.toInt(), 10)
                "vol: $percent%".also { binding.selectedNotificationVolume.text = it }

                val newVol = percentToDeviceVolume(percent, maxNotificationVol)
                audioManager.setStreamVolume(notificationStream, newVol, AudioManager.FLAG_SHOW_UI)
            }
        }
    }

    private fun clickListeners() {
        binding.setRepeatTime.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_repeatOptionFragment)
        }

        binding.setSound.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_notificationSoundFragment)
        }

        binding.setTtsSettings.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_textToSpeechFragment)
        }

        binding.speakCurrentTime.setOnClickListener {
            lifecycleScope.launch {
                //speakTestMessage()
            }
        }

        binding.setScheduling.setOnClickListener {
            //showScheduleTimerBottomSheet(this)
            val bottomSheet = ScheduleTimerBottomSheet(colorHexx) {
                schTime = AppPreferences.getScheduleTime()
            }
            bottomSheet.show(parentFragmentManager, "ScheduleTimerBottomSheet")

        }

        binding.header.setOnClickListener { }

        binding.customScheduling.setOnClickListener { }

        binding.setVolume.setOnClickListener {
            val viewB = binding.mediaLayout
            if (viewB.visibility == View.VISIBLE) {
                collapse(viewB)
                binding.txtCurrentVol.visibility = View.VISIBLE
            } else {
                expand(viewB)
                binding.txtCurrentVol.visibility = View.INVISIBLE
            }
        }
    }
    private fun expand(view: View) {
        view.measure(
            View.MeasureSpec.makeMeasureSpec((view.parent as View).width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )
        val targetHeight = view.measuredHeight

        view.layoutParams.height = 0
        view.alpha = 0f
        view.visibility = View.VISIBLE

        val animator = ValueAnimator.ofInt(0, targetHeight)
        animator.addUpdateListener { valueAnimator ->
            val newHeight = valueAnimator.animatedValue as Int
            view.layoutParams.height = newHeight
            view.requestLayout() // ← ensures smooth layout shift
            view.alpha = newHeight / targetHeight.toFloat()
        }
        animator.duration = 300
        animator.interpolator = DecelerateInterpolator()
        animator.start()
    }

    private fun collapse(view: View) {
        val initialHeight = view.measuredHeight
        val animator = ValueAnimator.ofInt(initialHeight, 0)
        animator.addUpdateListener { valueAnimator ->
            val newHeight = valueAnimator.animatedValue as Int
            view.layoutParams.height = newHeight
            view.requestLayout() // ← smooth layout updates
            view.alpha = newHeight / initialHeight.toFloat()
        }
        animator.duration = 300
        animator.interpolator = DecelerateInterpolator()
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                view.visibility = View.GONE
            }
        })
        animator.start()
    }


    private fun setupUIAfterPrefsLoaded() {
        // Restore toggle
        binding.customToggle.isChecked = AppPreferences.getToggleState()
        binding.enableScheduling.isChecked = AppPreferences.getCustomToggleState()

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
                headerCardRipple()
                //In app review
                /*AppPreferences.incrementToggleCount()
                val count = AppPreferences.getToggleCount()
                if (count == 30) {
                    InAppReviewUtil.launchInAppReview(requireActivity())
                    AppPreferences.resetToggleCount()
                }*/
            } else {
                lifecycleScope.launch {
                    AppPreferences.saveToggleState(false)
                    timerViewModel.stopTimer()
                }
                headerCardRipple()

            }
        }

        binding.enableScheduling.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                if (schTime != null) {
                    val startTime = schTime?.startTimeMillis
                    val endTime = schTime?.endTimeMillis
                    val interval = schTime?.intervalMillis

                    if (startTime != null && endTime != null && interval != null) {
                        // ✅ FIX: startTime should be in the future, not the past
                        if (startTime > System.currentTimeMillis()) {
                            scheduleTimerModel.startScheduleTimer(startTime, endTime, interval)
                            AppPreferences.saveCustomToggleState(true)
                            println("HomeFragment.setupUIAfterPrefsLoaded::${schTime.toString()}")

                        } else {
                            // Time already passed — show warning and uncheck
                            requireActivity().showCustomSnackBar(
                                message = "Your schedule time has passed away, please set again",
                                iconRes = R.drawable.ic_logo,
                                colorString = colorHexx
                            )
                            buttonView.isChecked = false
                            AppPreferences.saveCustomToggleState(false)
                        }
                    } else {
                        // Missing time values
                        requireActivity().showCustomSnackBar(
                            message = "Invalid schedule time",
                            iconRes = R.drawable.ic_logo,
                            colorString = colorHexx
                        )
                        buttonView.isChecked = false
                        AppPreferences.saveCustomToggleState(false)
                    }
                } else {
                    // Schedule time not set
                    requireActivity().showCustomSnackBar(
                        message = "Please set schedule time first",
                        iconRes = R.drawable.ic_logo,
                        colorString = colorHexx
                    )
                    buttonView.isChecked = false
                    AppPreferences.saveCustomToggleState(false)
                }
                customSchedulingRipple()
            } else {
                // ✅ When toggle turned OFF
                scheduleTimerModel.stopTimer()
                customSchedulingRipple()
                AppPreferences.saveCustomToggleState(false)
            }
        }
    }

    private fun headerCardRipple() {
        binding.header.isPressed = true
        binding.header.postDelayed({
            binding.header.isPressed = false
        }, 200)
    }

    private fun customSchedulingRipple() {
        binding.customScheduling.isPressed = true
        binding.customScheduling.postDelayed({
            binding.customScheduling.isPressed = false
        }, 200)
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

    /*private fun showIntroGuide() {
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
    }*/

    private fun showIntroGuide() {
        val scrollView = binding.scrollView
        val targets = listOf(
            binding.customToggle, binding.setRepeatTime, binding.setSound, binding.setTtsSettings
        )

        showNextTarget(scrollView, targets, 0)
    }

    private fun showNextTarget(scrollView: ScrollView, targets: List<View>, index: Int) {
        if (index >= targets.size) {
            AppPreferences.saveFirstLaunch(true)
            return
        }

        val view = targets[index]

        // Pehle scrollView ko target tak scroll karo
        scrollView.post {
            scrollView.smoothScrollTo(0, view.bottom)
            view.postDelayed({
                val (title, desc) = when (index) {
                    0 -> "Start" to "Enable this toggle for start timer."
                    1 -> "Repeat Time" to "Set repeat time for timer"
                    2 -> "Notification Sound" to "Set your custom notification sound"
                    else -> "Text To Speech" to "Set Text to speech like language and voice etc"
                }

                val target =
                    TapTarget.forView(view, title, desc).outerCircleColor(R.color.light_blue)
                        .targetCircleColor(R.color.white).titleTextColor(R.color.black)
                        .descriptionTextColor(R.color.black).tintTarget(true).cancelable(false)
                        .transparentTarget(true)

                TapTargetView.showFor(requireActivity(), target, object : TapTargetView.Listener() {
                    override fun onTargetClick(viewTapTarget: TapTargetView?) {
                        super.onTargetClick(viewTapTarget)
                        showNextTarget(scrollView, targets, index + 1)
                    }
                })
            }, 500)
        }
    }

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
                formatter.format(Date()), TextToSpeech.QUEUE_FLUSH, null, null
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
                    scheduleTimerModel.isScheduleFinished.collect {isFinish->
                        if (isFinish){
                            binding.enableScheduling.isChecked = false
                            schTime = null
                        }
                    }
                }
//                launch {
//                    timerViewModel.isCustomTimerStart.collect { finished ->
//                        if (finished) {
//                            binding.customToggle.isChecked = false
//                            binding.timerText.text = "OFF"
//                        }
//                    }
//                }

//                launch {
//                    scheduleTimerModel.isScheduleStart.collect { start ->
//                        if (start){
//                            binding.customToggle.isChecked = false
//                            timerViewModel.stopTimer()
//                        }
//                    }
//                }
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
            }
        }
    }

    private fun showAnnouncerDialog() {
        val dialog = Dialog(requireContext()).apply {
            setContentView(R.layout.announcer_dialog)
            setCancelable(true)
            setCanceledOnTouchOutside(true)
        }

        val gif = dialog.findViewById<AppCompatImageView>(R.id.announceGif)
        //Glide.with(this).load(R.raw.megaphone).into(gif)
        dialog.show()
        Handler().postDelayed({
            dialog.dismiss()
        }, 2500)
    }

    fun showBottomSheet(context: Context) {
        // Inflate view using ViewBinding
        val binding = MoreLayoutBinding.inflate(LayoutInflater.from(context))
        val dialog = BottomSheetDialog(context, R.style.CustomBottomSheetDialogTheme)
        dialog.setContentView(binding.root)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)

        binding.ourApps.setBackgroundResource(requireContext().getRippleResource(true))
        binding.appTheme.setBackgroundResource(context.getRippleResource(true))
        binding.rateApp.setBackgroundResource(context.getRippleResource(true))
        binding.shareApp.setBackgroundResource(context.getRippleResource(false))
        binding.appVersion.setBackgroundResource(context.getRippleResource(false))

        // Apply color filters
        binding.ourAppImg.setColorFilter(colorHexx.toColorInt())
        binding.themeImg.setColorFilter(colorHexx.toColorInt())
        binding.rateAppImg.setColorFilter(colorHexx.toColorInt())
        binding.shareImg.setColorFilter(colorHexx.toColorInt())
        binding.versionImg.setColorFilter(colorHexx.toColorInt())
        //binding.upArrow.setColorFilter(colorHexx.toColorInt())
        binding.more.setTextColor(colorHexx.toColorInt())
        binding.txtVersionName.setTextColor(colorHexx.toColorInt())

        binding.upArrow.addValueCallback(
            KeyPath("**"), // ** = all layers; or target specific ones by name
            LottieProperty.COLOR_FILTER,
            LottieValueCallback(
                PorterDuffColorFilter(
                    colorHexx.toColorInt(),
                    PorterDuff.Mode.SRC_ATOP
                )
            )
        )

        // Click Listeners
        binding.ourApps.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_ourAppFragment)
            dialog.dismiss()
        }

        binding.rateApp.setOnClickListener {
            Utils.openPlayStore(requireContext(), requireContext().packageName)
        }

        binding.shareApp.setOnClickListener {
            Utils.shareApp(requireContext(), requireContext().packageName)
        }

        binding.appTheme.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_appThemeFragment)
            dialog.dismiss()
        }

        // App version info
        val packageInfo =
            requireContext().packageManager.getPackageInfo(requireContext().packageName, 0)
        val versionName = packageInfo.versionName
        val versionCode = packageInfo.longVersionCode
        //binding.txtVersionName.text = "App Version: $versionName ($versionCode)"
        "App Version: $versionName".also {
            binding.txtVersionName.text = it
        }

        dialog.show()
    }


    override fun onStart() {
        super.onStart()

        val darkMode = AppPreferences.isDarkThemeEnabled()
        //setUiThemeMode()
        setThemeMode(darkMode == true)

        schTime = AppPreferences.getScheduleTime()

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

        appUpdateManager!!.appUpdateInfo.addOnSuccessListener { appUpdateInfo: AppUpdateInfo ->
            if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                popupSnackBarForCompleteUpdate()
            }
        }


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
    // --- Helper Functions ---
    private fun percentToDeviceVolume(percent: Int, maxVol: Int): Int {
        val vol = (percent / 100f * maxVol.toFloat()).roundToInt()
        return vol.coerceIn(0, maxVol)
    }

    private fun snapToStep(value: Int, step: Int): Int {
        val div = (value.toDouble() / step).roundToInt()
        return (div * step).coerceIn(0, 100)
    }

    // --- Keep in sync with external volume changes ---
    private fun syncSliderWithSystemVolume() {
        val musicStream = AudioManager.STREAM_MUSIC
        val notiStream = AudioManager.STREAM_NOTIFICATION

        val maxMusicVol = audioManager.getStreamMaxVolume(musicStream)
        val curMusicVol = audioManager.getStreamVolume(musicStream)
        val musicPercent = ((curMusicVol.toFloat() / maxMusicVol.toFloat()) * 100f).roundToInt()
        val snappedMusic = snapToStep(musicPercent, 10)

        val maxNotiVol = audioManager.getStreamMaxVolume(notiStream)
        val curNotiVol = audioManager.getStreamVolume(notiStream)
        val notiPercent = ((curNotiVol.toFloat() / maxNotiVol.toFloat()) * 100f).roundToInt()
        val snappedNoti = snapToStep(notiPercent, 10)

        binding.mediaVolRocker.value = snappedMusic.toFloat()
        "vol: $snappedMusic%".also { binding.selectedMediaVolume.text = it }
        "vol: $snappedMusic%".also { binding.txtCurrentVol.text = it }
        binding.notificationVolRocker.value = snappedNoti.toFloat()
        "vol: $snappedNoti%".also { binding.selectedNotificationVolume.text = it }

        if (snappedMusic == 100 && snappedNoti == 100) {
            requireActivity().showCustomSnackBar(
                "You have reached max volume",
                R.drawable.sound,
                colorString = colorHexx
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        timerViewModel.stopTimer()
        println("🔥 TimerService destroyed")
    }

}