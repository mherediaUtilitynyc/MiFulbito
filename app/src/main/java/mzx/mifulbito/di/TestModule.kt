package mzx.mifulbito.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import kotlin.math.roundToInt

@Module
@InstallIn(SingletonComponent::class)
interface TestModule {
    @Binds
    fun bindsDemo(demo: DemoImpl): Demo
}

interface Demo {
    fun demoFun(): Int
}

class DemoImpl @Inject constructor() : Demo {
    override fun demoFun() = (Math.random() * ROUNDED_NUMBER).roundToInt()

    companion object {
        const val ROUNDED_NUMBER = 10
    }
}