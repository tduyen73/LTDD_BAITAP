package com.example.permissiononboardingapp.ui.screens.permissions

import androidx.compose.runtime.Composable
import com.example.permissiononboardingapp.R

@Composable
fun CameraPermissionScreen(onTurnOn: () -> Unit, onSkip: () -> Unit) {
    PermissionScreen(
        title = "Camera",
        description = "We need access to your camera to scan QR codes",
        iconRes = R.drawable.camera,
        primaryText = "Turn on",
        onPrimaryClick = onTurnOn,
        onSkipClick = onSkip
    )
}
