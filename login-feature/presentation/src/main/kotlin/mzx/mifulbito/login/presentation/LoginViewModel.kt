package mzx.mifulbito.login.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf(LoginViewState.Init)
    val state: State<LoginViewState> = _state

    data class LoginViewState(val isLoading: Boolean) {
        fun toLoading(): LoginViewState = copy(isLoading = true)

        companion object {
            val Init = LoginViewState(isLoading = false)
        }
    }

    sealed interface LoginViewEvent {
        data class SignIn(val user: String, val password: String) : LoginViewEvent
    }

    fun onEvent(event: LoginViewEvent) {
        _state.value = state.value.toLoading()
    }
}