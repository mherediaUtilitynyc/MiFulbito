package mzx.mifulbito

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import mzx.mifulbito.di.Demo

@HiltAndroidApp
class MiFulbitoApp : Application() {
    @Inject
    lateinit var demo: Demo
    override fun onCreate() {
        super.onCreate()

        Log.e("Test" ,"this is ${demo.demoFun()}")
    }

}