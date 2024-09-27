plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.mvp_food_planner"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mvp_food_planner"
        minSdk = 26
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}



dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.legacy.support.v4)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Retrofit and Gradle
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.google.code.gson:gson:2.8.9")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.github.bumptech.glide:glide:4.11.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.11.0")
    // Room
    implementation ("androidx.room:room-runtime:2.6.1")
    annotationProcessor ("androidx.room:room-compiler:2.6.1")
    // lotti and nav
    implementation ("com.airbnb.android:lottie:6.1.0")
    implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.22")
    implementation ("com.airbnb.android:lottie:latest_version")
    implementation ("com.google.firebase:firebase-firestore:25.1.0")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.8.1")
    implementation ("androidx.navigation:navigation-ui-ktx:2.8.1")
}