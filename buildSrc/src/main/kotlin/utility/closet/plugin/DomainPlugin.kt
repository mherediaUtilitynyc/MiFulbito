package utility.closet.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import utility.closet.ext.dependenciesConfig
import utility.closet.ext.pluginConfig

class DomainPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.pluginConfig(pluginsIds)
        project.dependenciesConfig(
            implementations = implementationIds,
            testImplementation = testImplementation,
        )
    }

    private val testImplementation: List<String> = listOf(
        "junit", "mockk", "coroutinesTest"
    )
    private val implementationIds: List<String> = listOf("kotlin", "arrowKt", "javaxInject")
    private val pluginsIds: List<String> =
        listOf("java", "kotlin", "utility.closet.plugin.spek2")
}