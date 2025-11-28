plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
    id("org.jetbrains.kotlin.kapt")
    id("kotlin-parcelize")
    alias(libs.plugins.hilt)
}

android {
    namespace = "uz.ibrohim.nonkaboob"
    compileSdk = 36

    defaultConfig {
        applicationId = "uz.ibrohim.nonkaboob"
        minSdk = 23
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
    implementation(libs.firebase.firestore)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Nav Fragment
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.navigation)
    implementation(libs.androidx.navigationUi)

    //ViewModel
    implementation(libs.viewModel.lifecycle)
    implementation(libs.viewModel.runtime)
    implementation(libs.lifecycle.livedata)

    //LottieAnimation
    implementation(libs.splash.airbnb)

    //Glide
    annotationProcessor(libs.glide.compiler)
    implementation(libs.glide.bumptech)

    //Toast
    implementation (libs.toast.grender)

    //Lingver
    implementation(libs.lingver.language)

    //CircleImage
    implementation(libs.hdodenhof.circleimage)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation)
    kapt(libs.hilt.compiler.androidx)

    //Coroutines
//    implementation(libs.coroutines.core)
//    implementation(libs.coroutines.android)
}