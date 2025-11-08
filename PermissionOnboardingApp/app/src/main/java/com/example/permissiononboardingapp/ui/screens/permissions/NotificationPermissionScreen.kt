package com.example.permissiononboardingapp.ui.screens.permissions

import androidx.compose.runtime.Composable
import com.example.permissiononboardingapp.R

@Composable
fun NotificationPermissionScreen(onTurnOn: () -> Unit, onSkip: () -> Unit) {
    PermissionScreen(
        title = "Notification",
        description = "Please enable notifications to receive updates and reminders",
        iconRes = R.drawable.notification,
        primaryText = "Turn on",
        onPrimaryClick = onTurnOn,
        onSkipClick = onSkip
    )
}
