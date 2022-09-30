package mzx.mifulbito.login.presentation

import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import mzx.mifulbito.domain.login.RegisterLoginUseCase
import mzx.mifulbito.domain.login.UseCase

class LoginEffectListener @Inject constructor(
    @JvmSuppressWildcards private val useCase: UseCase<RegisterLoginUseCase.RegisterLoginParam,
            RegisterLoginUseCase.RegisterLoginResult,
            RegisterLoginUseCase.RegisterLoginError>,
) : (
    LoginStateMachine.SideEffect, CoroutineScope,
    @JvmSuppressWildcards
        (LoginStateMachine.Event) -> Unit
) -> Unit {

    override fun invoke(
        sideEffect: LoginStateMachine.SideEffect,
        viewModelScope: CoroutineScope,
        onEvent: (LoginStateMachine.Event) -> Unit
    ) {
        when (sideEffect) {
            is LoginStateMachine.SideEffect.LoadLogin -> viewModelScope.launch {
                useCase.action(
                    RegisterLoginUseCase.RegisterLoginParam(
                        sideEffect.name,
                        sideEffect.password
                    )
                ).fold({
                    onEvent(LoginStateMachine.Event.OnLoaded)
                }, {
                    onEvent(LoginStateMachine.Event.OnError)
                })
            }
        }
    }
}