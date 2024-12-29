plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("kotlin-android")
}

android {
    namespace = "com.hindi.todo_app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.hindi.todo_app"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Room components (latest version)
    val roomVersion = "2.6.1"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion") // Kotlin extensions for Room
    kapt("androidx.room:room-compiler:$roomVersion") // Kotlin annotation processor

    // Lifecycle components
    val lifecycleVersion = "2.8.7"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion") // ViewModel
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion") // LiveData
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion") // Lifecycles only (without ViewModel or LiveData)
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycleVersion") // Saved state module for ViewModel
    implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion") // Java8 support

    // Optional - Coroutines support
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.5")

    // Optional - Test helpers for LiveData
    testImplementation("androidx.arch.core:core-testing:2.2.0")
}
