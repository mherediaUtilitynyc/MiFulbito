package mzx.mifulbito.login.demo

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import mzx.mifulbito.login.presentation.LoginViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel : LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.state
        Log.e("jusst ","jejeje ${viewModel.state}")
    }
}