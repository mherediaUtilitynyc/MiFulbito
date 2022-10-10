package mzx.mifulbito.login.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mzx.mifulbito.login.presentation.LoginStateMachine


@Composable
@Preview(heightDp = 200, widthDp = 200,showBackground = true)
fun LoginScreenPreview() {
    LoginScreen(state = LoginStateMachine.State.CheckPassword("userName")) {}
}

@Composable
fun LoginScreen(state: LoginStateMachine.State, onEvent: (LoginStateMachine.Event) -> Unit) {
    when (state) {
        LoginStateMachine.State.CheckCredential -> {

        }
        is LoginStateMachine.State.CheckPassword -> CheckPasswordScreen()
        LoginStateMachine.State.CheckingCredentials -> {}
        LoginStateMachine.State.Init -> {}
        LoginStateMachine.State.Logged -> {}
        LoginStateMachine.State.LoggedError -> {}
        LoginStateMachine.State.CheckingCredential -> {

        }
    }
}

@Composable
fun CheckPasswordScreen() {
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
        shape = RoundedCornerShape(8.dp),
        tonalElevation = 8.dp
    ) {

    }
}