plugins {
    id("utility.closet.plugin.app")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "mzx.mifulbito.login.demo"
}
dependencies {

    implementation(project(mapOf("path" to ":login-feature:presentation")))
    implementation(project(mapOf("path" to ":login-feature:domain")))

    implementation(project(mapOf("path" to ":login-feature:data")))
    implementation(project(mapOf("path" to ":login-feature:data:data-preferences")))
    implementation(project(mapOf("path" to ":login-feature:data:data-server-json")))

    kapt(libs.hiltKapt)

}