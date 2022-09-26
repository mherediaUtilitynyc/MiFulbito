package mzx.mifulbito.plugin.feature

import com.android.build.gradle.BaseExtension
import de.mannodermaus.gradle.plugins.junit5.AndroidJUnitPlatformExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class Spek2AndroidPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.pluginManager.apply("de.mannodermaus.android-junit5")
        project.extensions.getByType(BaseExtension::class.java).let {
            it.testOptions {
                unitTests {
                    isReturnDefaultValues = true
                    isIncludeAndroidResources = true
                }
            }
        }

        project
            .extensions
            .getByType(AndroidJUnitPlatformExtension::class.java)
            .filters {
                includeEngines("spek2")
            }

        project.dependencies {
            addTestImplementation(project.libs("kotlinReflect"))
            addTestImplementation( project.libs("spekDls"))
            add("testRuntimeOnly", project.libs("spekRunner"))
        }
    }
}