package utility.closet.ext

import org.gradle.kotlin.dsl.DependencyHandlerScope


fun DependencyHandlerScope.addTestImplementation(libs: String) {
    add("testImplementation", libs)
}

fun DependencyHandlerScope.addAndroidTestImplementation(libs: String) {
    add("androidTestImplementation", libs)
}

fun DependencyHandlerScope.addDebugImplementation(libs: String) {
    add("debugImplementation", libs)
}

fun DependencyHandlerScope.addImplementation(libs: String) {
    add("implementation", libs)
}