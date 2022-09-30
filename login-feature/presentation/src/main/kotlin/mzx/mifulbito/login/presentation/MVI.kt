package mzx.mifulbito.login.presentation

import androidx.compose.runtime.State
import kotlinx.coroutines.CoroutineScope

interface MVI<E, S> {
    var viewModelScope: CoroutineScope?
    val state: State<S>
    fun onEvent(event: E)
}