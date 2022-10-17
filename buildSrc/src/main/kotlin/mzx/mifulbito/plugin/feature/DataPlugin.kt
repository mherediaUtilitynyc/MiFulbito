package mzx.mifulbito.plugin.feature

import org.gradle.api.Plugin
import org.gradle.api.Project

class DataPlugin : Plugin<Project> {
    private val pluginsIds: List<String> = listOf("java", "kotlin")
    private val implementations = listOf("kotlin")

    override fun apply(project: Project) {
        project.pluginConfig(pluginsIds)
        project.dependenciesConfig(implementations = implementations)
    }
}