package mzx.mifulbito.plugin.feature

import org.gradle.api.Plugin
import org.gradle.api.Project

class DomainPlugin : Plugin<Project> {
    private val testImplementation: List<String> = listOf(
        "junit", "mockk", "coroutinesTest"
    )
    private val implementationIds: List<String> = listOf("kotlin", "arrowKt", "javaxInject")
    private val pluginsIds: List<String> =
        listOf("java", "kotlin", "mzx.mifulbito.plugin.feature.spek2")

    override fun apply(project: Project) {
        project.pluginConfig(pluginsIds)
        project.dependenciesConfig(
            implementations = implementationIds,
            testImplementation = testImplementation,
        )
    }
}