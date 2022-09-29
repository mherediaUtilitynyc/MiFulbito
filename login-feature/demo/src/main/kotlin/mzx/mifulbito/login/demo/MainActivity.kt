package mzx.mifulbito.login.demo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import mzx.mifulbito.login.demo.theme.MiFulbitoTheme


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
                        // A surface container using the 'background' color from the theme
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            MyScreen()
                        }
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
    viewModel.state
}