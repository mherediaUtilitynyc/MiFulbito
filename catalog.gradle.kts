dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {

            version("coreKtx", "1.9.0")
            version("compose", "1.3.0-alpha01")
            version("hilt", "2.41")
            version("mockk", "1.12.8")
            version("junit", "4.13.2")
            version("extJunit", "1.1.3")
            version("expressoCore", "3.4.0")
            version("composeMaterial3", "1.0.0-beta02")
            version("lifecycleKtx", "2.5.1")
            version("activityCompose", "1.5.1")
            version("spek", "2.0.19")
            version("kotlinReflect", "1.7.10")
            version("kotlinVersion", "1.6.10")
            version("arrowVersion", "1.1.2")
            version("javaxInject", "1")
            version("appCompatVersion", "1.5.1")
            version("coroutinesTest", "1.6.4")
            version("hiltNavigationCompose","1.0.0")


            alias("kotlin").to("org.jetbrains.kotlin", "kotlin-stdlib").versionRef("kotlinVersion")
            alias("coreKtx").to("androidx.core", "core-ktx").versionRef("coreKtx")

            alias("hilt").to("com.google.dagger", "hilt-android").versionRef("hilt")
            alias("hiltKapt").to("com.google.dagger", "hilt-compiler").versionRef("hilt")

            alias("composeUi").to("androidx.compose.ui", "ui").versionRef("compose")
            alias("composeUiPreview").to("androidx.compose.ui", "ui-tooling-preview")
                .versionRef("compose")

            alias("composeUiTooling").to("androidx.compose.ui", "ui-tooling").versionRef("compose")
            alias("composeUiTestManifest").to("androidx.compose.ui", "ui-test-manifest")
                .versionRef("compose")

            alias("composeUiTestJunit4").to("androidx.compose.ui", "ui-test-junit4")
                .versionRef("compose")
            alias("mockk").to("io.mockk", "mockk").versionRef("mockk")
            alias("mockkAndroid").to("io.mockk", "mockk-android").versionRef("mockk")
            alias("junit").to("junit", "junit").versionRef("junit")

            alias("extJunit").to("androidx.test.ext", "junit").versionRef("extJunit")
            alias("expressoCore").to("androidx.test.espresso", "espresso-core")
                .versionRef("expressoCore")
            alias("composeMaterial3").to("androidx.compose.material3", "material3")
                .versionRef("composeMaterial3")
            alias("lifecycleKtx").to("androidx.lifecycle", "lifecycle-runtime-ktx")
                .versionRef("lifecycleKtx")
            alias("activityCompose").to("androidx.activity", "activity-compose")
                .versionRef("activityCompose")

            alias("kotlinReflect").to("org.jetbrains.kotlin", "kotlin-reflect")
                .versionRef("kotlinVersion")
            alias("spekDls").to("org.spekframework.spek2", "spek-dsl-jvm").versionRef("spek")
            alias("spekRunner").to("org.spekframework.spek2", "spek-runner-junit5")
                .versionRef("spek")
            alias("arrowKt").to("io.arrow-kt", "arrow-core").versionRef("arrowVersion")
            alias("javaxInject").to("javax.inject", "javax.inject").versionRef("javaxInject")
            alias("appCompatVersion").to("androidx.appcompat", "appcompat")
                .versionRef("appCompatVersion")
            alias("coroutinesTest").to("org.jetbrains.kotlinx", "kotlinx-coroutines-test")
                .versionRef("coroutinesTest")

            alias("hiltNavigationCompose").to("androidx.hilt", "hilt-navigation-compose")
                .versionRef("hiltNavigationCompose")
        }
    }
}