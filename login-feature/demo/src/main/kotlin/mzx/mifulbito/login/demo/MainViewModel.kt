package mzx.mifulbito.login.demo

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import mzx.mifulbito.login.presentation.LoginStateMachine
import mzx.mifulbito.MVI

@HiltViewModel
class MainViewModel @Inject constructor(
    private val stateMachine: MVI<LoginStateMachine.Event, LoginStateMachine.State>
) : ViewModel() {
    init {
        stateMachine.viewModelScope = viewModelScope
    }

    val state: State<LoginStateMachine.State> = stateMachine.state

    fun onEvent(event: LoginStateMachine.Event) {
        stateMachine.onEvent(event)
    }
}