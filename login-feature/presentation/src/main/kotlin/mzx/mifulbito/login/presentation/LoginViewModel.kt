package mzx.mifulbito.login.presentation

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//@HiltViewModel
//class LoginViewModel @Inject constructor(
//    private val stateMachine: LoginStateMachine
//) : ViewModel() {
//
//    init {
//        stateMachine.viewModelScope = viewModelScope
//    }
//
//    val state: State<LoginStateMachine.State> = stateMachine.state
//
//
//    fun onEvent(event: LoginStateMachine.Event) {
//        stateMachine.onEvent(event)
//    }
//}