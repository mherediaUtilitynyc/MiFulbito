package utility.closet.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import utility.closet.ext.androidAppConfig
import utility.closet.ext.defineCompileOptions
import utility.closet.ext.dependenciesConfig
import utility.closet.ext.pluginConfig

class AppPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.pluginConfig(pluginsIds)
        project.androidAppConfig()
        project.dependenciesConfig(
            implementations = implementationIds,
            debugImplementations = debugImplementations,
            testImplementation = testImplementation,
            androidTestImplementation = androidTestImplementation
        )
        project.defineCompileOptions()
    }

    private val pluginsIds = listOf(
        "com.android.application",
        "org.jetbrains.kotlin.android",
        "kotlin-kapt",
        //                "dagger.hilt.android.plugin" TODO
    )

    private val implementationIds = listOf(
        "coreKtx",
        "composeUi",
        "composeUiPreview",
        "composeMaterial3",
        "lifecycleKtx",
        "activityCompose",
        "appCompatVersion",
        "hilt",
        "hiltNavigationCompose"
    )

    private val debugImplementations = listOf("composeUiTooling", "composeUiTestManifest")

    private val testImplementation = listOf("junit", "mockk")
    private val androidTestImplementation = listOf(
        "extJunit", "expressoCore", "composeUiTestJunit4", "mockkAndroid"
    )
}
