plugins {
    id("com.android.application")
    id("kotlin-parcelize")
    kotlin("android")
}

android {
    compileSdk = 34

    defaultConfig {
        applicationId = "edu.anbu.libraryofalexandria"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    namespace = "edu.anbu.libraryofalexandria"
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0-alpha13")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.room:room-runtime:2.6.0-rc01")
    annotationProcessor("androidx.room:room-compiler:2.6.0-rc01")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.6.2")
}