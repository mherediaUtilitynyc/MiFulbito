package utility.closet

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.PluginContainer
import org.gradle.kotlin.dsl.DependencyHandlerScope
import utility.closet.ext.addAndroidTestImplementation
import utility.closet.ext.addDebugImplementation
import utility.closet.ext.addImplementation
import utility.closet.ext.addTestImplementation


internal infix fun List<String>.addAndroidTestImplementation(dependencyHandlerScope: DependencyHandlerScope) {
    forEach { dependencyHandlerScope.addAndroidTestImplementation(it) }
}

internal infix fun List<String>.addTestImplementation(dependencyHandlerScope: DependencyHandlerScope) {
    forEach { dependencyHandlerScope.addTestImplementation(it) }
}

internal infix fun List<String>.addDebugImplementations(dependencyHandlerScope: DependencyHandlerScope) {
    forEach { dependencyHandlerScope.addDebugImplementation(it) }
}

internal infix fun List<String>.addImplementations(dependencyHandlerScope: DependencyHandlerScope) {
    forEach { dependencyHandlerScope.addImplementation(it) }
}

internal infix fun List<String>.mapToProjectLibs(project: Project) =
    map { project.libs(it) }


internal fun PluginContainer.applyIds(pluginIds: List<String>) {
    pluginIds.forEach { apply(it) }
}

fun Project.libs(lib: String): String {
    val versionCatalog =
        project.extensions.getByType(VersionCatalogsExtension::class.java).named("libs")
    val dep = versionCatalog.findDependency(lib).get().get()
    return "${dep.module.group}:${dep.module.name}:${dep.versionConstraint.displayName}"
}
