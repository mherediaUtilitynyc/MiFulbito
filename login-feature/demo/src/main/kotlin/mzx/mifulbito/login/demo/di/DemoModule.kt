package mzx.mifulbito.login.demo.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import mzx.mifulbito.MVI
import mzx.mifulbito.data.login.LoginRepository
import mzx.mifulbito.data.login.RegisteredUserRepository
import mzx.mifulbito.domain.login.RegisterLoginUseCase
import mzx.mifulbito.domain.login.UseCase
import mzx.mifulbito.login.presentation.LoginEffectListener
import mzx.mifulbito.login.presentation.LoginStateMachine
import utility.closet.data.preference.AccountSettings
import utility.closet.data.preference.MockRegisteredUserRepository
import utility.closet.data.server.json.MockLoginRepository

@Module
@InstallIn(ViewModelComponent::class)
interface DemoModule {
    @Binds
    fun useCase(useCase: RegisterLoginUseCase): UseCase<Unit,
            RegisterLoginUseCase.RegisterLoginResult,
            RegisterLoginUseCase.RegisterLoginError>

    @Binds
    @JvmSuppressWildcards
    fun effectListener(effectListener: LoginEffectListener):
                (
        LoginStateMachine.SideEffect, CoroutineScope,
        (LoginStateMachine.Event) -> Unit
    ) -> Unit

    @Binds
    @JvmSuppressWildcards
    fun viewModelIntent(machineState: LoginStateMachine): MVI<LoginStateMachine.Event, LoginStateMachine.State>

    @Binds
    fun registeredUserRepository(registeredUserRepository: MockRegisteredUserRepository): RegisteredUserRepository

    @Binds
    fun accountSettings(accountSettings: MockRegisteredUserRepository.AccountSettingsImpl): AccountSettings

    @Binds
    fun loginRepository(loginRepository: MockLoginRepository): LoginRepository

    companion object {
        @Provides
        fun  mainDispatcher(): CoroutineDispatcher = Dispatchers.Main
    }

}