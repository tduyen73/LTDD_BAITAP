package com.example.btvn_tuan4_02.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.btvn_tuan4_02.SharedViewModel
import com.example.btvn_tuan4_02.navigation.AppRoutes
import com.example.btvn_tuan4_02.ui.theme.GradientBackground
import com.example.btvn_tuan4_02.ui.theme.PrimaryButton
import com.example.btvn_tuan4_02.ui.theme.TextTitleColor

@Composable
fun ResetPasswordScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GradientBackground),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White.copy(alpha = 0.95f))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Create new password", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = TextTitleColor)
            Text(
                "Your new password must be different from previously used password",
                fontSize = 13.sp,
                color = Color.Gray
            )
            Spacer(Modifier.height(20.dp))

            OutlinedTextField(
                value = sharedViewModel.newPassword.value,
                onValueChange = { sharedViewModel.newPassword.value = it },
                label = { Text("Password") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = TextTitleColor) },
                shape = RoundedCornerShape(16.dp),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = sharedViewModel.newPassword.value,
                onValueChange = { sharedViewModel.newPassword.value = it },
                label = { Text("Confirm Password") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = TextTitleColor) },
                shape = RoundedCornerShape(16.dp),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = { navController.navigate(AppRoutes.Confirm) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(12.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues()
            ) {
                Box(
                    modifier = Modifier
                        .background(PrimaryButton)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Next", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }
    }
}
