package mzx.mifulbito.plugin.feature

import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalogsExtension

fun Project.libs(lib: String): String {
    val versionCatalog = project.extensions.getByType(VersionCatalogsExtension::class.java).named("libs")
    val dep = versionCatalog.findDependency(lib).get().get()
    return "${dep.module.group}:${dep.module.name}:${dep.versionConstraint.displayName}"
}

fun Project.bundles(bundle: String): List<String> {
    val versionCatalog = project.extensions.getByType(VersionCatalogsExtension::class.java).named("libs")
    val bundle = versionCatalog.findBundle(bundle).get().get() as MutableList<MinimalExternalModuleDependency>
    val deps = mutableListOf<String>()
    bundle.forEach {
        deps.add("${it.module.group}:${it.module.name}:${it.versionConstraint.displayName}")
    }
    return deps
}