package mzx.mifulbito.login.presentation

import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import mzx.mifulbito.StateMachine

@JvmSuppressWildcards
class LoginStateMachine @Inject constructor(
    private val listener: (
        SideEffect, CoroutineScope,
        (Event) -> Unit
    ) -> Unit
) : MVI<LoginStateMachine.Event, LoginStateMachine.State> {
    override var viewModelScope: CoroutineScope? = null

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

    override val state = stateMachine.state

    override fun onEvent(event: Event) {
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