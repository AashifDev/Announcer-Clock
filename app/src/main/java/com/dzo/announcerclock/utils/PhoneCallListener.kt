package com.dzo.announcerclock.utils

import android.content.Context
import android.content.pm.PackageManager
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.util.Log
import androidx.core.content.ContextCompat

class PhoneCallListener(
    private val context: Context,
    private val onCallStateChanged: (state: Int) -> Unit
) : PhoneStateListener() {

    override fun onCallStateChanged(state: Int, incomingNumber: String?) {
        super.onCallStateChanged(state, incomingNumber)

        when (state) {
            TelephonyManager.CALL_STATE_RINGING -> {
                // Incoming call — pause service
                onCallStateChanged(TelephonyManager.CALL_STATE_RINGING)
            }
            TelephonyManager.CALL_STATE_OFFHOOK -> {
                // Call picked or outgoing — pause service
                onCallStateChanged(TelephonyManager.CALL_STATE_OFFHOOK)
            }
            TelephonyManager.CALL_STATE_IDLE -> {
                // No call — resume service
                onCallStateChanged(TelephonyManager.CALL_STATE_IDLE)
            }
        }
    }

    companion object {
        fun register(context: Context, onCallStateChanged: (Int) -> Unit): PhoneCallListener? {
            val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

            if (ContextCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.READ_PHONE_STATE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Log.w("PhoneCallListener", "READ_PHONE_STATE not granted, skipping registration")
                return null
            }

            val listener = PhoneCallListener(context,onCallStateChanged)
            telephonyManager.listen(listener, LISTEN_CALL_STATE)
            return listener
        }

        fun unregister(context: Context, listener: PhoneCallListener) {
            val telephonyManager =
                context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            telephonyManager.listen(listener, PhoneStateListener.LISTEN_NONE)
        }
    }
}
