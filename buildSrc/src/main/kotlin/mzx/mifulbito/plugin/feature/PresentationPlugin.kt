package mzx.mifulbito.plugin.feature

import org.gradle.api.Plugin
import org.gradle.api.Project


class PresentationPlugin : Plugin<Project> {

    private val androidTestImplementation: List<String> = listOf(
        "extJunit",
        "expressoCore",
        "composeUiTestJunit4",
        "mockkAndroid"
    )
    private val testImplementation: List<String> = listOf(
        "junit",
        "mockk",
        "coroutinesTest"
    )
    private val debugImplementations: List<String> =
        listOf("composeUiTooling", "composeUiTestManifest")
    private val implementationIds: List<String> = listOf(
        "coreKtx", "composeUi", "composeUiPreview", "composeMaterial3",
        "lifecycleKtx", "activityCompose", "hilt", "arrowKt"
    )
    private val pluginsIds: List<String> = listOf(
        "com.android.library",
        "org.jetbrains.kotlin.android",
        "kotlin-kapt",
        "mzx.mifulbito.plugin.feature.spek2Android"
    )

    override fun apply(project: Project) {
        project.pluginConfig(pluginsIds)
        project.androidLibConfig()
        project.dependenciesConfig(
            implementations = implementationIds,
            debugImplementations = debugImplementations,
            testImplementation = testImplementation,
            androidTestImplementation = androidTestImplementation
        )
    }
}
