package mzx.mifulbito.login.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import mzx.mifulbito.login.presentation.LoginStateMachine


@Composable
@Preview(heightDp = 200, widthDp = 200, showBackground = true)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckPasswordScreen() {
//    Surface(
//        color = Color.White,
////        MaterialTheme.colorScheme.primary,
//        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
//        shape = RoundedCornerShape(8.dp),
//        tonalElevation = 8.dp
//    ) {
//        rememberSaveable { mutableStateOf("Text") }
        var text by rememberSaveable {
            mutableStateOf("Text")
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {

            TextField(
                value = text,
                onValueChange = {
                    text = it
                },
                label = { Text("User") }
            )

            TextField(
                value = text,
                onValueChange = {
                    text = it
                },
                label = { Text("Password") }
            )

//        }
    }
}