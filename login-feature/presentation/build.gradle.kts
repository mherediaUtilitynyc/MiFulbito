plugins {
    id("utility.closet.plugin.presentation")
}
android {
    namespace = "mzx.mifulbito.login.presentation"
}
dependencies {
    implementation(project(":login-feature:domain"))
}
