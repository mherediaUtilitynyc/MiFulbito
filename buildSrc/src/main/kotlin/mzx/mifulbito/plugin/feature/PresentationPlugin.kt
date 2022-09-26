package mzx.mifulbito.plugin.feature

import com.android.build.gradle.BaseExtension
import mzx.mifulbito.Versions
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies


class PresentationPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        configureAndroid(project)
        configureDependencies(project)
    }

    private fun configureAndroid(project: Project) {
        project.pluginManager.apply("com.android.library")
        project.pluginManager.apply("org.jetbrains.kotlin.android")
        project.pluginManager.apply("kotlin-kapt")
//        project.pluginManager.apply("dagger.hilt.android.plugin")
        project.extensions.getByType(BaseExtension::class.java).let {
            it.setCompileSdkVersion(Versions.Android.compileSdk)
            it.defaultConfig {
                this.minSdk = Versions.Android.minSdk
                vectorDrawables.useSupportLibrary = true
            }
        }
    }

    private fun configureDependencies(project: Project) {


        project.dependencies {
            arrayOf(
                "coreKtx", "composeUi", "composeUiPreview", "composeMaterial3",
                "lifecycleKtx", "activityCompose", "hilt","arrowKt"
            ).forEach { addImplementation(project.libs(it)) }
            //add("kapt", project.libs("hiltKapt"))
            arrayOf("composeUiTooling", "composeUiTestManifest").forEach {
                addDebugImplementation(
                    project.libs(it)
                )
            }

            arrayOf("junit", "mockk").forEach { addTestImplementation(project.libs(it)) }
            arrayOf(
                "extJunit",
                "expressoCore",
                "composeUiTestJunit4",
                "mockkAndroid"
            ).forEach { addAndroidTestImplementation(project.libs(it)) }

//            add("implementation", project.libs("jsr305"))
        }
    }
}

fun DependencyHandlerScope.addTestImplementation(libs: String) {
    add("testImplementation", libs)
}

private fun DependencyHandlerScope.addAndroidTestImplementation(libs: String) {
    add("androidTestImplementation", libs)
}

private fun DependencyHandlerScope.addDebugImplementation(libs: String) {
    add("debugImplementation", libs)
}

fun DependencyHandlerScope.addImplementation(libs: String) {
    add("implementation", libs)
}
