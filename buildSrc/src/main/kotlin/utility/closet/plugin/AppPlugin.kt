package utility.closet.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import utility.closet.ext.androidAppConfig
import utility.closet.ext.defineCompileOptions
import utility.closet.ext.dependenciesConfig
import utility.closet.ext.pluginConfig

class AppPlugin : Plugin<Project> {

    private val config = Config.APP_CONFIG

    override fun apply(project: Project) {
        project.pluginConfig(config.pluginsIds)
        project.androidAppConfig()
        project.dependenciesConfig(
            config.pluginConfig
        )
        project.defineCompileOptions()
    }
}
