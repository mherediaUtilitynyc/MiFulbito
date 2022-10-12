plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(33)

    defaultConfig {
        applicationId = "mzx.mifulbito.login.demo"
        minSdkVersion(21)
        targetSdkVersion(33)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-beta01"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
dependencies {

    implementation(project(mapOf("path" to ":login-feature:presentation")))
    implementation(project(mapOf("path" to ":login-feature:domain")))
    implementation(project(mapOf("path" to ":login-feature:data")))

    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation(libs.coreKtx)
    implementation(libs.composeUi)
    implementation(libs.composeUiPreview)

    implementation(libs.composeMaterial3)
    implementation(libs.lifecycleKtx)
    implementation(libs.activityCompose)
    implementation(libs.appCompatVersion)

    implementation(libs.hilt)
    kapt(libs.hiltKapt)

    debugImplementation(libs.composeUiTooling)
    debugImplementation(libs.composeUiTestManifest)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)

    androidTestImplementation(libs.extJunit)
    androidTestImplementation(libs.expressoCore)
    androidTestImplementation(libs.composeUiTestJunit4)
    androidTestImplementation(libs.mockkAndroid)
}