package mzx.mifulbito.login.presentation

import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import mzx.mifulbito.domain.login.RegisterLoginUseCase
import mzx.mifulbito.domain.login.RegisterLoginUseCase.RegisterLoginError.LoginError
import mzx.mifulbito.domain.login.UseCase

class LoginEffectListener @Inject constructor(
    @JvmSuppressWildcards private val useCase: UseCase<Unit,
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
                useCase.action(Unit).fold(errorMapper(onEvent), successMapper(onEvent))
            }
            is LoginStateMachine.SideEffect.CheckingCredentials -> TODO()
        }
    }
}

fun errorMapper(onEvent: (LoginStateMachine.Event) -> Unit): (RegisterLoginUseCase.RegisterLoginError) -> Unit =
    { error ->
        onEvent(
            when (error) {
                RegisterLoginUseCase.RegisterLoginError.CommonError -> LoginStateMachine.Event.OnError
                is LoginError -> LoginStateMachine.Event.OnLoginError(error.userName)
            }
        )
    }

fun successMapper(onEvent: (LoginStateMachine.Event) -> Unit): (RegisterLoginUseCase.RegisterLoginResult) -> Unit =
    { result ->
        onEvent(
            when (result) {
                RegisterLoginUseCase.RegisterLoginResult.LoginSuccess -> LoginStateMachine.Event.OnLoginSuccess
                RegisterLoginUseCase.RegisterLoginResult.NoCredentials
                -> LoginStateMachine.Event.OnNotCredentials
                is RegisterLoginUseCase.RegisterLoginResult.PasswordExpired
                -> LoginStateMachine.Event.OnPasswordExpired(
                    result.userName
                )
            }
        )
    }