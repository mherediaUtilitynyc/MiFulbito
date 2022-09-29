package mzx.mifulbito.login.presentation

import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import mzx.mifulbito.StateMachine
import mzx.mifulbito.domain.login.RegisterLoginUseCase

@JvmSuppressWildcards
class LoginStateMachine @Inject  constructor(
     private val listener: (
        SideEffect, CoroutineScope,
            (Event) -> Unit
    ) -> Unit
) {
    var viewModelScope: CoroutineScope? = null

    private val stateMachine = StateMachine.create<State, Event, SideEffect> {
        initialState(State.Init)
        state<State.Init> {
            on<Event.OnInit> { event ->
                transitionTo(
                    State.Loading,
                    SideEffect.LoadLogin(name = event.name, password = event.password)
                )
            }
        }
        state<State.Loading> {
            on<Event.OnLoaded> {
                transitionTo(State.Loaded)
            }
        }

        onTransition {
            val validTransition = it as? StateMachine.Transition.Valid ?: return@onTransition
            validTransition.sideEffect?.let { sideEffect ->
                viewModelScope?.let { coroutineScope ->
                    listener.invoke(
                        sideEffect,
                        coroutineScope,
                        ::onEvent
                    )
                }
            }
        }
    }

    val state = stateMachine.state

    fun onEvent(event: Event) {
        stateMachine.transition(event)
    }

    sealed class State {
        object Init : State()
        object Loading : State()
        object Loaded : State()
    }

    sealed class Event {
        data class OnInit(val name: String, val password: String) : Event()
        object OnLoaded : Event()
        object OnError : Event()
    }

    sealed class SideEffect {
        data class LoadLogin(val name: String, val password: String) : SideEffect()
    }
}

class LoginEffectListener @Inject constructor(
    private val useCase: RegisterLoginUseCase,
) : @JvmSuppressWildcards
    (
    LoginStateMachine.SideEffect, CoroutineScope,
    @JvmSuppressWildcards (LoginStateMachine.Event) -> Unit
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