plugins {
    id("utility.closet.plugin.app")
    id("dagger.hilt.android.plugin")
}


tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
android {
    namespace = "mzx.mifulbito.login.demo"
}
dependencies {

    implementation(project(mapOf("path" to ":login-feature:presentation")))
    implementation(project(mapOf("path" to ":login-feature:domain")))
    implementation(project(mapOf("path" to ":login-feature:data")))

    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    kapt(libs.hiltKapt)

}