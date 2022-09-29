package mzx.mifulbito.login.demo.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineScope
import mzx.mifulbito.login.presentation.LoginEffectListener
import mzx.mifulbito.login.presentation.LoginStateMachine

@Module
@InstallIn(ViewModelComponent::class)
interface DemoModule {
//    @Binds
//    fun useCase(useCase: RegisterLoginUseCase): @JvmSuppressWildcards UseCase<RegisterLoginUseCase.RegisterLoginParam,
//            RegisterLoginUseCase.RegisterLoginResult,
//            RegisterLoginUseCase.RegisterLoginError>

    @Binds
    @JvmSuppressWildcards
    fun effectListener(effectListener: LoginEffectListener):
                (
        LoginStateMachine.SideEffect, CoroutineScope,
        (LoginStateMachine.Event) -> Unit
    ) -> Unit

}