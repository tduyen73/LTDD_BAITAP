package com.example.permissiononboardingapp.ui.screens.permissions

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.permissiononboardingapp.R

@Composable
fun LoginBackground() {
    var showPermission by remember { mutableStateOf(true) }

    // Hình nền (giao diện đăng nhập hoặc ảnh nền app)
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_clean),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Lớp phủ permission (chỉ hiện khi cần)
        if (showPermission) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f))
            ) {
                PermissionPager(onDone = { showPermission = false })
            }
        }
    }
}
