package mzx.mifulbito.login.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mzx.mifulbito.login.presentation.LoginStateMachine


@Composable
@Preview(heightDp = 800, widthDp = 480, showBackground = true)
fun LoginScreenPreview() {

    LoginScreen(state = LoginStateMachine.State.CheckPassword("userName")) {}
}

@Composable
fun LoginScreen(state: LoginStateMachine.State, onEvent: (LoginStateMachine.Event) -> Unit) {
    when (state) {
        LoginStateMachine.State.CheckCredential -> {
            CheckPasswordScreen()

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckPasswordScreen() {
    Surface(
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primaryContainer),
        shape = RoundedCornerShape(8.dp),
        tonalElevation = 8.dp
    ) {
        var user by rememberSaveable {
            mutableStateOf("")
        }

        var passowrd by rememberSaveable {
            mutableStateOf("")
        }
        Box() {
            Column(
//            modifier = Modifier.align(alignment = Alignment.Horizontal())
            ) {

                TextField(
                    value = user,
                    onValueChange = {
                        user = it
                    },
                    label = { Text("User") }
                )

                TextField(
                    value = passowrd,
                    onValueChange = {
                        passowrd = it
                    },
                    label = { Text("Password") }
                )

            }
        }
    }
}