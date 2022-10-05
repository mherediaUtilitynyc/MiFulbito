package mzx.mifulbito.plugin.feature

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class DomainPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.pluginManager.apply("java")
        project.pluginManager.apply("kotlin")
        project.pluginManager.apply("mzx.mifulbito.plugin.feature.spek2")

        configureDependencies(project)
    }

    private fun configureDependencies(project: Project) {
        project.dependencies {
            addImplementation( project.libs("kotlin"))
            addImplementation( project.libs("arrowKt"))
            addImplementation( project.libs("javaxInject"))

            arrayOf(
                "junit",
                "mockk",
                "coroutinesTest"
            ).forEach { addTestImplementation(project.libs(it)) }
        }
    }
}