package utility.closet.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import utility.closet.ext.androidLibConfig
import utility.closet.ext.dependenciesConfig
import utility.closet.ext.pluginConfig


class PresentationPlugin : Plugin<Project> {

    private val config = Config.PRESENTATION_CONFIG

    override fun apply(project: Project) {
        project.pluginConfig(config.pluginsIds)
        project.androidLibConfig()
        project.dependenciesConfig(config.pluginConfig)
    }
}
