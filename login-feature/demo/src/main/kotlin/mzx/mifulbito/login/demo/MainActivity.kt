package mzx.mifulbito.login.demo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import mzx.mifulbito.login.demo.theme.MiFulbitoTheme
import mzx.mifulbito.login.ui.LoginScreen


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "example") {
                composable("example") { backStackEntry ->
                    // Creates a ViewModel from the current BackStackEntry
                    // Available in the androidx.hilt:hilt-navigation-compose artifact
                    MiFulbitoTheme {

                        MyScreen()
                    }
                }
                /* ... */
            }

        }
    }
}

@Composable
fun MyScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    LoginScreen(state = viewModel.state.value, onEvent = viewModel::onEvent)
}