package utility.closet.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import utility.closet.dependenciesConfig
import utility.closet.pluginConfig

class DataPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.pluginConfig(pluginsIds)
        project.dependenciesConfig(implementations = implementations)
    }

    private val pluginsIds: List<String> = listOf("java", "kotlin")
    private val implementations = listOf("kotlin")
}