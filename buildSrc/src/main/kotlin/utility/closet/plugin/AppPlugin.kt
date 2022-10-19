package utility.closet.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import utility.closet.androidAppConfig
import utility.closet.dependenciesConfig
import utility.closet.pluginConfig

class AppPlugin : Plugin<Project> {
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
        "hilt"
    )

    private val debugImplementations = listOf("composeUiTooling", "composeUiTestManifest")

    private val testImplementation = listOf("junit", "mockk")
    private val androidTestImplementation = listOf(
        "extJunit",
        "expressoCore",
        "composeUiTestJunit4",
        "mockkAndroid"
    )

    override fun apply(project: Project) {
        project.pluginConfig(pluginsIds)
        project.androidAppConfig()
        project.dependenciesConfig(
            implementations = implementationIds,
            debugImplementations = debugImplementations,
            testImplementation = testImplementation,
            androidTestImplementation = androidTestImplementation
        )
    }
}
