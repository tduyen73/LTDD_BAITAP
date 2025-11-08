package com.example.permissiononboardingapp.ui.screens.permissions

import androidx.compose.runtime.Composable
import com.example.permissiononboardingapp.R

@Composable
fun LocationPermissionScreen(onAllow: () -> Unit, onSkip: () -> Unit) {
    PermissionScreen(
        title = "Location",
        description = "Allow maps to access your location while you use the app?",
        iconRes = R.drawable.location,
        primaryText = "Allow",
        onPrimaryClick = onAllow,
        onSkipClick = onSkip
    )
}
