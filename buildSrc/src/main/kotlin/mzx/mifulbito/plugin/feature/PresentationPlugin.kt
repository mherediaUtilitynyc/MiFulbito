package mzx.mifulbito.plugin.feature

import org.gradle.api.Plugin
import org.gradle.api.Project
import mzx.mifulbito.plugin.bundles
import mzx.mifulbito.plugin.libs
import com.android.build.gradle.BaseExtension
import mzx.mifulbito.Versions
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
            addImplementation( project.libs("coreKtx"))
            addImplementation( project.libs("composeUi"))
            addImplementation( project.libs("composeUiPreview"))

            addImplementation( project.libs("composeMaterial3"))
            addImplementation( project.libs("lifecycleKtx"))
            addImplementation( project.libs("activityCompose"))

            addImplementation( project.libs("hilt"))
//            add("kapt", project.libs("hiltKapt"))

            addDebugImplementation(project.libs("composeUiTooling"))
            addDebugImplementation(project.libs("composeUiTestManifest"))

            addTestImplementation(project.libs("junit"))
            addTestImplementation(project.libs("mockk"))

            addAndroidTestImplementation(project.libs("extJunit"))
            addAndroidTestImplementation(project.libs("expressoCore"))
            addAndroidTestImplementation(project.libs("composeUiTestJunit4"))
            addAndroidTestImplementation(project.libs("mockkAndroid"))

//            add("implementation", project.libs("jsr305"))
        }
    }
}

private fun DependencyHandlerScope.addTestImplementation(libs: String) {
    add("testImplementation", libs)
}

private fun DependencyHandlerScope.addAndroidTestImplementation(libs: String) {
    add("androidTestImplementation", libs)
}

private fun DependencyHandlerScope.addDebugImplementation(libs: String) {
    add("debugImplementation", libs)
}

private fun DependencyHandlerScope.addImplementation(libs: String) {
    add("implementation", libs)
}
