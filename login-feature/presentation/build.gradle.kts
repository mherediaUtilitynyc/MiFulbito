plugins {
    id("mzx.mifulbito.plugin.feature.presentation")
}
android {
    namespace = "mzx.mifulbito.login.presentation"
}
dependencies {
    implementation(project(":login-feature:domain"))
}
