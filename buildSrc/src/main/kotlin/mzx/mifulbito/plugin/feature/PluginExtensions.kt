package mzx.mifulbito.plugin.feature

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.PluginContainer
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies


fun Project.dependenciesConfig(
    implementations: List<String>,
    debugImplementations: List<String>,
    testImplementation: List<String>,
    androidTestImplementation: List<String>
) {
    dependencies {
        implementations mapToProjectLibs project addImplementations this
        debugImplementations mapToProjectLibs project addDebugImplementations this
        testImplementation mapToProjectLibs project addTestImplementation this
        androidTestImplementation mapToProjectLibs project addAndroidTestImplementation this
    }
}

fun Project.androidAppConfig() {
    extensions.getByType(BaseAppModuleExtension::class.java).apply {
        compileSdk = 33
        defaultConfig {
            applicationId = "mzx.mifulbito.login.demo"
            minSdk = 21
            targetSdk = 33
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
}

fun Project.pluginConfig(pluginIds: List<String>) {
    plugins.applyIds(pluginIds = pluginIds)
}

private infix fun List<String>.addAndroidTestImplementation(dependencyHandlerScope: DependencyHandlerScope) {
    forEach { dependencyHandlerScope.addAndroidTestImplementation(it) }
}

private infix fun List<String>.addTestImplementation(dependencyHandlerScope: DependencyHandlerScope) {
    forEach { dependencyHandlerScope.addTestImplementation(it) }
}

private infix fun List<String>.addDebugImplementations(dependencyHandlerScope: DependencyHandlerScope) {
    forEach { dependencyHandlerScope.addDebugImplementation(it) }
}

private infix fun List<String>.addImplementations(dependencyHandlerScope: DependencyHandlerScope) {
    forEach { dependencyHandlerScope.addImplementation(it) }
}

private infix fun List<String>.mapToProjectLibs(project: Project) =
    map { project.libs(it) }


private fun PluginContainer.applyIds(pluginIds: List<String>) {
    pluginIds.forEach { apply(it) }
}

fun DependencyHandlerScope.addTestImplementation(libs: String) {
    add("testImplementation", libs)
}

fun DependencyHandlerScope.addAndroidTestImplementation(libs: String) {
    add("androidTestImplementation", libs)
}

fun DependencyHandlerScope.addDebugImplementation(libs: String) {
    add("debugImplementation", libs)
}

fun DependencyHandlerScope.addImplementation(libs: String) {
    add("implementation", libs)
}

fun Project.libs(lib: String): String {
    val versionCatalog =
        project.extensions.getByType(VersionCatalogsExtension::class.java).named("libs")
    val dep = versionCatalog.findDependency(lib).get().get()
    return "${dep.module.group}:${dep.module.name}:${dep.versionConstraint.displayName}"
}
