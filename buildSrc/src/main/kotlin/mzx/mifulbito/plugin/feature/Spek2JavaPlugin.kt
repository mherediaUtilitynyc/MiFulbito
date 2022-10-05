package mzx.mifulbito.plugin.feature

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.dependencies

class Spek2JavaPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        (project.tasks.getByName("test") as Test).let { test ->
            test.useJUnitPlatform {
                includeEngines("spek2")
            }
        }

        project.dependencies {
            add("testImplementation", project.libs("kotlinReflect"))

            add("testImplementation", project.libs("spekDls"))
            add("testRuntimeOnly", project.libs("spekRunner"))
        }
    }

}
