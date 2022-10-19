enableFeaturePreview("VERSION_CATALOGS")
apply {
    from("catalog.gradle.kts")
}
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "dagger.hilt.android.plugin" -> useModule("com.google.dagger:hilt-android-gradle-plugin:2.41")
                "org.jetbrains.kotlin.android" -> useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
                "com.android.application" -> useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
                "kotlin-kapt" -> useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
            }
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }

}
rootProject.name = "MiFulbito"
include(":app")
include(
    "login-feature:presentation",
    "login-feature:domain",
    "login-feature:demo",
    "login-feature:data",
    "login-feature:data:data-server-json",
    "login-feature:data:data-preferences"

)
