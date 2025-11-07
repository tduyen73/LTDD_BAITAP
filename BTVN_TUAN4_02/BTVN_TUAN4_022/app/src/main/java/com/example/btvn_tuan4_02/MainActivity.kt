package com.example.btvn_tuan4_02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.btvn_tuan4_02.navigation.AppRoutes
import com.example.btvn_tuan4_02.ui.screens.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val sharedViewModel: SharedViewModel = viewModel()

            Surface(color = MaterialTheme.colorScheme.background) {
                NavHost(
                    navController = navController,
                    startDestination = AppRoutes.ForgotPassword
                ) {
                    composable(AppRoutes.ForgotPassword) {
                        ForgotPasswordScreen(navController, sharedViewModel)
                    }
                    composable(AppRoutes.VerifyCode) {
                        VerifyCodeScreen(navController, sharedViewModel)
                    }
                    composable(AppRoutes.ResetPassword) {
                        ResetPasswordScreen(navController, sharedViewModel)
                    }
                    composable(AppRoutes.Confirm) {
                        ConfirmScreen(navController, sharedViewModel)
                    }
                }
            }
        }
    }
}
