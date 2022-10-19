package utility.closet.plugin

data class PluginConfig(
    val implementationIds: List<String>,
    val debugImplementations: List<String>,
    val testImplementation: List<String>,
    val androidTestImplementation: List<String>
)

enum class Config(val pluginsIds: List<String>, val pluginConfig: PluginConfig) {
    APP_CONFIG(
        pluginsIds = listOf(
            "com.android.application",
            "org.jetbrains.kotlin.android",
            "kotlin-kapt",
            //                "dagger.hilt.android.plugin" TODO
        ),
        PluginConfig(
            implementationIds = listOf(
                "coreKtx",
                "composeUi",
                "composeUiPreview",
                "composeMaterial3",
                "lifecycleKtx",
                "activityCompose",
                "appCompatVersion",
                "hilt",
                "hiltNavigationCompose"
            ),
            debugImplementations = listOf("composeUiTooling", "composeUiTestManifest"),
            testImplementation = listOf("junit", "mockk"),
            androidTestImplementation = listOf(
                "extJunit", "expressoCore", "composeUiTestJunit4", "mockkAndroid"
            )
        )
    ),

    DATA_CONFIG(
        pluginsIds = listOf("java", "kotlin"),
        PluginConfig(
            implementationIds = listOf("kotlin"),
            debugImplementations = emptyList(),
            testImplementation = emptyList(),
            androidTestImplementation = emptyList()
        )
    ),

    DOMAIN_CONFIG(
        pluginsIds =
        listOf("java", "kotlin", "utility.closet.plugin.spek2"),
        PluginConfig(
            implementationIds = listOf("kotlin", "arrowKt", "javaxInject"),
            testImplementation = listOf(
                "junit", "mockk", "coroutinesTest"
            ),
            debugImplementations = emptyList(),
            androidTestImplementation = emptyList()
        )
    ),
    PRESENTATION_CONFIG(
        pluginsIds = listOf(
            "com.android.library",
            "org.jetbrains.kotlin.android",
            "kotlin-kapt",
            "utility.closet.plugin.spek2Android"
        ), PluginConfig(
            implementationIds = listOf(
                "coreKtx", "composeUi", "composeUiPreview", "composeMaterial3",
                "lifecycleKtx", "activityCompose", "hilt", "arrowKt"
            ),
            debugImplementations =
            listOf("composeUiTooling", "composeUiTestManifest"),
            testImplementation = listOf(
                "junit",
                "mockk",
                "coroutinesTest"
            ),
            androidTestImplementation = listOf(
                "extJunit",
                "expressoCore",
                "composeUiTestJunit4",
                "mockkAndroid"
            )
        )
    )
}