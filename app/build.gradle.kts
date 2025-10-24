plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.dzo.announcerclock"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.dzo.announcerclock"
        minSdk = 28
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.lifecycle.service)
    implementation(libs.firebase.ai)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    val roomVersion = "2.6.1"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")

    //Glide
    implementation("com.github.bumptech.glide:glide:5.0.5")

    //Gson
    implementation("com.google.code.gson:gson:2.11.0")

    //Coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")

    //SDP android
    implementation("com.intuit.sdp:sdp-android:1.1.1")
    implementation("com.intuit.ssp:ssp-android:1.1.1")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.11.0")

    //Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-compiler:2.51.1")

    //ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

    implementation("com.google.accompanist:accompanist-permissions:0.30.1")
    implementation("androidx.localbroadcastmanager:localbroadcastmanager:1.1.0")

    //Data Store
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    //Tap Target View
    implementation("com.getkeepsafe.taptargetview:taptargetview:1.13.3")

    //In App Update
    implementation("com.google.android.play:app-update:2.1.0")

    //emoji
    implementation("androidx.emoji2:emoji2:1.3.0")

    //Color Picker
    implementation("com.github.Dhaval2404:ColorPicker:2.3")

    //Flex Box
    implementation("com.google.android.flexbox:flexbox:3.0.0")

    implementation("com.google.android.material:material:1.12.0")
}