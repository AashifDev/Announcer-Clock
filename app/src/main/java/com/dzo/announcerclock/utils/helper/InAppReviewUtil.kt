package com.dzo.announcerclock.utils.helper

import android.app.Activity
import android.widget.Toast
import com.google.android.play.core.review.ReviewManagerFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object InAppReviewUtil {
    fun launchInAppReview(activity: Activity) {
        val manager = ReviewManagerFactory.create(activity)
        CoroutineScope(Dispatchers.Main).launch {
            val request = manager.requestReviewFlow()
            request.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val reviewInfo = task.result
                    manager.launchReviewFlow(activity, reviewInfo)
                }
            }
        }
        /*CoroutineScope(Dispatchers.Main).launch {
            val request = manager.requestReviewFlow()
            request.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // We got the ReviewInfo object
                    val reviewInfo = task.result
                    val flow = manager.launchReviewFlow(activity, reviewInfo)
                    flow.addOnCompleteListener {
                        // Flow completed (user may or may not have rated)
                        Toast.makeText(activity, "Thanks for your feedback!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Failed to get review flow
                    Toast.makeText(activity, "Unable to start review right now", Toast.LENGTH_SHORT).show()
                }
            }
        }*/
    }
}