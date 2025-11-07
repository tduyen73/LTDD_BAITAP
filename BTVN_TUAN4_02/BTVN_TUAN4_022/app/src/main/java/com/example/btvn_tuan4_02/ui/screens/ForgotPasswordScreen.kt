package com.example.btvn_tuan4_02.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.btvn_tuan4_02.SharedViewModel
import com.example.btvn_tuan4_02.navigation.AppRoutes
import com.example.btvn_tuan4_02.ui.theme.GradientBackground
import com.example.btvn_tuan4_02.ui.theme.PrimaryButton
import com.example.btvn_tuan4_02.ui.theme.TextTitleColor

@Composable
fun ForgotPasswordScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    var emailText by remember { mutableStateOf(TextFieldValue(sharedViewModel.email.value)) }
    var emailError by remember { mutableStateOf("") }

    fun isValidGmail(email: String): Boolean {
        return email.endsWith("@gmail.com") && email.length > 10
    }

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
            Text(
                text = "SmartTasks",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = TextTitleColor
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = "Forget Password?",
                fontSize = 20.sp,
                color = Color.Gray
            )
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = emailText,
                onValueChange = {
                    emailText = it
                    emailError = ""
                },
                label = { Text("Your Email") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email icon",
                        tint = TextTitleColor
                    )
                },
                isError = emailError.isNotEmpty(),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                singleLine = true,
                placeholder = { Text("example@gmail.com") }
            )

            if (emailError.isNotEmpty()) {
                Text(
                    text = emailError,
                    color = Color.Red,
                    fontSize = 13.sp,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 4.dp, start = 8.dp)
                )
            }

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    if (isValidGmail(emailText.text)) {
                        sharedViewModel.email.value = emailText.text
                        navController.navigate(AppRoutes.VerifyCode)
                    } else {
                        emailError = "❌ Vui lòng nhập đúng địa chỉ Gmail (đuôi @gmail.com)"
                    }
                },
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

            if (sharedViewModel.email.value.isNotEmpty() ||
                sharedViewModel.verificationCode.value.isNotEmpty() ||
                sharedViewModel.newPassword.value.isNotEmpty()
            ) {
                Spacer(Modifier.height(28.dp))
                Text("Dữ liệu bạn đã nhập:", fontWeight = FontWeight.Bold, color = TextTitleColor)
                Text("📧 Email: ${sharedViewModel.email.value}")
                Text("🔐 Code: ${sharedViewModel.verificationCode.value}")
                Text("🔑 Password: ${sharedViewModel.newPassword.value}")
            }
        }
    }
}
