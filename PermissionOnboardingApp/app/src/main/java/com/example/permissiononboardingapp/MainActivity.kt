package com.example.permissiononboardingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.permissiononboardingapp.ui.screens.permissions.LoginBackground
import com.example.permissiononboardingapp.ui.theme.PermissionOnboardingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PermissionOnboardingAppTheme {
                LoginBackground()
            }
        }
    }
}
