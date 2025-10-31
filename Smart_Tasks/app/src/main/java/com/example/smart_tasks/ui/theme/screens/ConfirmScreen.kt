package com.example.smart_tasks.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.smart_tasks.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmScreen(
    navController: NavController,
    email: String,
    password: String
) {
    var inputPassword by remember { mutableStateOf(password) }
    var passwordVisible by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black // 🔹 Icon rõ
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(id = R.drawable.uth_logo),
                contentDescription = null,
                modifier = Modifier
                    .height(80.dp)
                    .padding(bottom = 8.dp)
            )

            // 🔹 Logo + Tiêu đề
            Text(
                text = "SmartTasks",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF007AFF)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 🔹 Các dòng chữ đều dùng màu đậm rõ
            Text(
                text = "Confirm",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = "We are here to help you!",
                color = Color(0xFF444444), // đậm hơn gray
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {},
                label = { Text("Email", color = Color(0xFF333333)) },
                textStyle = LocalTextStyle.current.copy(color = Color.Black),
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            )

            OutlinedTextField(
                value = "123456",
                onValueChange = {},
                label = { Text("Code", color = Color(0xFF333333)) },
                textStyle = LocalTextStyle.current.copy(color = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                enabled = false
            )

            OutlinedTextField(
                value = inputPassword,
                onValueChange = { inputPassword = it },
                label = { Text("Password", color = Color(0xFF333333)) },
                textStyle = LocalTextStyle.current.copy(color = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val icon = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "Toggle password visibility",
                            tint = Color.Black
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { /* TODO: Handle submit */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007AFF))
            ) {
                Text("Submit", color = Color.White, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
