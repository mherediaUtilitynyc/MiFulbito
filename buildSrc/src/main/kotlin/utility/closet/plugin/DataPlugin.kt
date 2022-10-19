package utility.closet.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import utility.closet.ext.dependenciesConfig
import utility.closet.ext.pluginConfig

class DataPlugin : Plugin<Project> {

    private val config = Config.DATA_CONFIG

    override fun apply(project: Project) {
        project.pluginConfig(config.pluginsIds)
        project.dependenciesConfig(config.pluginConfig)
    }
}