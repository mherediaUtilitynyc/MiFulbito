package mzx.mifulbito.login.demo

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import mzx.mifulbito.MVI
import mzx.mifulbito.login.presentation.LoginStateMachine

@HiltViewModel
class MainViewModel @Inject constructor(
    private val stateMachine: MVI<LoginStateMachine.Event, LoginStateMachine.State>,
    private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {
    init {
        stateMachine.viewModelScope = viewModelScope
        stateMachine.onEvent(LoginStateMachine.Event.OnInit)
    }

    val state: State<LoginStateMachine.State> = stateMachine.state

    fun onEvent(event: LoginStateMachine.Event) {
        stateMachine.onEvent(event)
    }

}