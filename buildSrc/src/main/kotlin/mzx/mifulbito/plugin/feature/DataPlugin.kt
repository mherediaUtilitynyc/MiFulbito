package mzx.mifulbito.plugin.feature

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class DataPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.pluginManager.apply("java")
        project.pluginManager.apply("kotlin")

        configureDependencies(project)
    }

    private fun configureDependencies(project: Project) {
        project.dependencies {
            add("implementation", project.libs("kotlin"))
        }
    }
}