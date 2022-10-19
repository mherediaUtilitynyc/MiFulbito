plugins {
    id("utility.closet.plugin.app")
    id("dagger.hilt.android.plugin")
}

android {

    defaultConfig {
        applicationId = "mzx.mifulbito"
    }
    namespace = "mzx.mifulbito"
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
dependencies {
    kapt(libs.hiltKapt)
}