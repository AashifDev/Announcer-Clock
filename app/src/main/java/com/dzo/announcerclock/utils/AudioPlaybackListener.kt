package com.dzo.announcerclock.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager

class AudioPlaybackListener(
    private val context: Context,
    private val onAudioStateChanged: (isPlaying: Boolean) -> Unit
) {
    private var audioManager: AudioManager =
        context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    // Detect headphones unplugged
    private val noisyReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == AudioManager.ACTION_AUDIO_BECOMING_NOISY) {
                // Treat as media started (pause your TTS)
                onAudioStateChanged(true)
            }
        }
    }

    // Detect audio focus changes
    private val audioFocusChangeListener =
        AudioManager.OnAudioFocusChangeListener { focusChange ->
            when (focusChange) {
                AudioManager.AUDIOFOCUS_LOSS,
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT,
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> {
                    // Other app started media → pause TTS
                    onAudioStateChanged(true)
                }
                AudioManager.AUDIOFOCUS_GAIN -> {
                    // Media stopped → resume TTS
                    onAudioStateChanged(false)
                }
            }
        }

    fun register() {
        // Register noisy broadcast
        context.registerReceiver(
            noisyReceiver,
            IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY)
        )

        // Request audio focus for detecting other media
        audioManager.requestAudioFocus(
            audioFocusChangeListener,
            AudioManager.STREAM_MUSIC,
            AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK
        )
    }

    fun unregister() {
        // Unregister receiver and abandon focus
        context.unregisterReceiver(noisyReceiver)
        audioManager.abandonAudioFocus(audioFocusChangeListener)
    }
}
