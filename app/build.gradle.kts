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

dependencies {
    kapt(libs.hiltKapt)
}