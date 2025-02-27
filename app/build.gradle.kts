plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.khan.samacharapp"
    compileSdkVersion(rootProject.extra["compileSdkVersion"] as Int)
    buildToolsVersion ("34.0.0")

    defaultConfig {
        applicationId = "com.khan.samacharapp"
        minSdk = 33
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Splash Api
    implementation (libs.androidx.core.splashscreen)

    //Compose Navigation
    implementation (libs.androidx.navigation.compose)

    //Dagger Hilt
    implementation (libs.hilt.android)
    kapt (libs.hilt.compiler)
    implementation (libs.androidx.hilt.navigation.compose)


    //Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)

    //Coil
    implementation(libs.coil.compose)

    //Datastore
    implementation( libs.androidx.datastore.preferences)

    //Compose Foundation
    implementation( libs.androidx.foundation)

    //Accompanist
    implementation (libs.accompanist.systemuicontroller)

    //Paging 3
    implementation (libs.androidx.paging.runtime.ktx)
    implementation (libs.androidx.paging.compose)

    //Room
    implementation (libs.androidx.room.runtime)
    //noinspection KaptUsageInsteadOfKsp
    kapt(libs.androidx.room.compiler)
    implementation (libs.androidx.room.ktx)
}