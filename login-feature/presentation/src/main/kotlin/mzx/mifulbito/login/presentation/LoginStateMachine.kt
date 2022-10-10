package mzx.mifulbito.login.presentation

import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import mzx.mifulbito.MVI
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
            on<Event.OnInit> {
                transitionTo(
                    State.CheckingCredentials,
                    SideEffect.LoadLogin
                )
            }
        }
        state<State.CheckingCredentials> {
            on<Event.OnPasswordExpired> { event ->
                transitionTo(State.CheckPassword(event.userName))
            }
            on<Event.OnNotCredentials> {
                transitionTo(State.CheckCredential)
            }
            on<Event.OnLoginSuccess> {
                transitionTo(State.Logged)
            }
            on<Event.OnError> {
                transitionTo(State.LoggedError)
            }
        }
        state<State.CheckPassword> { }
        state<State.CheckCredential> { }
        state<State.Logged> { }
        state<State.LoggedError> { }

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

    sealed interface State {
        object Init : State
        object CheckingCredentials : State
        data class CheckPassword(val userName: String) : State
        object CheckCredential : State
        object CheckingCredential : State
        object Logged : State
        object LoggedError : State
    }

    sealed interface Event {
        object OnInit : Event
        data class OnPasswordExpired(val userName: String) : Event
        data class OnLoginError(val userName: String) : Event
        data class OnCheckCredentials(val user: String, val password: String) :
            Event

        object OnNotCredentials : Event
        object OnLoginSuccess : Event
        object OnError : Event
    }

    sealed interface SideEffect {
        object LoadLogin : SideEffect
    }
}