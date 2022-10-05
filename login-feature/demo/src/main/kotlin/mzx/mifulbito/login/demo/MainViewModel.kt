package mzx.mifulbito.login.demo

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import mzx.mifulbito.login.presentation.LoginStateMachine
import mzx.mifulbito.MVI

@HiltViewModel
class MainViewModel @Inject constructor(
    private val stateMachine: MVI<LoginStateMachine.Event, LoginStateMachine.State>,
    private val mainDispatcher : CoroutineDispatcher
) : ViewModel() {
    private val job = SupervisorJob()
    private val uiScope = CoroutineScope(mainDispatcher + job)
    init {
        stateMachine.viewModelScope = uiScope
    }

    val state: State<LoginStateMachine.State> = stateMachine.state

    fun onEvent(event: LoginStateMachine.Event) {
        stateMachine.onEvent(event)
    }

    override fun onCleared() {
        super.onCleared()
        this.job.cancel()
    }
}