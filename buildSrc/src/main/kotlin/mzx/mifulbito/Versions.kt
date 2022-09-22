package mzx.mifulbito


object Versions {

    object Android {
        const val compileSdk = 33
        const val minSdk = 21
    }

    object MiFulbito {
        val versionName = System.getenv("VERSION_NAME") ?: "1.0.0"
        val versionCode = System.getenv("VERSION_CODE")?.toInt() ?: 1
    }

}
