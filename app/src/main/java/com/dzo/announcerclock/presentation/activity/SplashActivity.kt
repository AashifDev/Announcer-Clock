package com.dzo.announcerclock.presentation.activity

import android.Manifest
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.airbnb.lottie.LottieAnimationView
import com.dzo.announcerclock.R
import com.dzo.announcerclock.data.local_source.AppPreferences

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    lateinit var lottieView : LottieAnimationView
    private val requiredPermissions = buildList {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            add(Manifest.permission.POST_NOTIFICATIONS)
            add(Manifest.permission.READ_MEDIA_AUDIO)
        }else{
            add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        add(Manifest.permission.READ_PHONE_STATE)
    }.toTypedArray()

    private val requestPermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val allGranted = permissions.values.all { it }

            if (allGranted) {
                goToMainScreen()
            } else {
                val permanentlyDenied = permissions.entries.any {
                    !shouldShowRequestPermissionRationale(it.key)
                            && ContextCompat.checkSelfPermission(this, it.key) != PackageManager.PERMISSION_GRANTED
                }

                if (permanentlyDenied) {
                    showPermissionDeniedDialog()
                } else {
                    // Ask again if user just denied (not permanently)
                    checkAndRequestPermissions()
                }
            }
        }

    private fun goToMainScreen() {
        if (AppPreferences.isFirstLaunch()==true) {
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        }else{
            /*lottieView.addAnimatorListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finishAffinity()
                    AppPreferences.saveFirstLaunch(true)
                }
            })*/
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finishAffinity()
            }, 1500)
            //AppPreferences.saveFirstLaunch(true)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        lottieView = findViewById<LottieAnimationView>(R.id.splashAnim)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        showAppVersion()

        checkAndRequestPermissions()
    }
    private fun showAppVersion() {
        val packageInfo = packageManager.getPackageInfo(packageName, 0)
        val versionName = packageInfo.versionName
        val versionCode = packageInfo.longVersionCode
        val textView = findViewById<AppCompatTextView>(R.id.appVersion)
        "App Version: $versionName".also {
            textView.text = it
        }
    }
    private fun checkAndRequestPermissions() {
        val missingPermissions = requiredPermissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

        if (missingPermissions.isEmpty()) {
            goToMainScreen()
        } else {
            requestPermissionsLauncher.launch(missingPermissions.toTypedArray())
        }
    }

    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(this)
            .setTitle("Permission Required")
            .setMessage(
                "This app needs phone state and notification permissions to function properly.\n\n" +
                        "Please enable them in Settings."
            )
            .setPositiveButton("Go to Settings") { _, _ ->
                val intent = Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    "package:$packageName".toUri()
                )
                intent.addCategory(Intent.CATEGORY_DEFAULT)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
            .setNegativeButton("Exit") { _, _ ->
                finish()
            }
            .setCancelable(false)
            .show()
    }

}
