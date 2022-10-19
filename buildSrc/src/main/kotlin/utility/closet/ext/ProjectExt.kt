package utility.closet.ext

import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import utility.closet.Versions
import utility.closet.plugin.PluginConfig

fun Project.dependenciesConfig(
    pluginConfig: PluginConfig
) {
    dependencies {
        pluginConfig.implementationIds mapToProjectLibs project addImplementations this
        pluginConfig.debugImplementations mapToProjectLibs project addDebugImplementations this
        pluginConfig.testImplementation mapToProjectLibs project addTestImplementation this
        pluginConfig.androidTestImplementation mapToProjectLibs project addAndroidTestImplementation this
    }
}

fun Project.androidAppConfig() {
    extensions.getByType(BaseAppModuleExtension::class.java).apply {
        compileSdk = Versions.Android.compileSdk
        defaultConfig {
//            applicationId = "mzx.mifulbito.login.demo"
            minSdk = Versions.Android.minSdk
            targetSdk = Versions.Android.targetSdk
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
            kotlinCompilerExtensionVersion = Versions.Android.kotlinCompilerExtensionVersion
        }
        packagingOptions {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }
}

fun Project.androidLibConfig() {
    project.extensions.getByType(LibraryExtension::class.java).let {
        it.buildFeatures.compose = true
        it.compileSdk = Versions.Android.compileSdk
        it.defaultConfig {
            this.minSdk = Versions.Android.minSdk
            vectorDrawables.useSupportLibrary = true
        }
        it.composeOptions {
            kotlinCompilerExtensionVersion = Versions.Android.kotlinCompilerExtensionVersion
        }
    }
}

fun Project.pluginConfig(pluginIds: List<String>) {
    plugins.applyIds(pluginIds = pluginIds)
}

fun Project.defineCompileOptions() {
    project.tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}