package com.example.smart_tasks.ui.theme.screens

import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smart_tasks.R
import com.example.smart_tasks.ui.theme.BlueUTH

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPasswordScreen(navController: NavController, email: String) {
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }

    Scaffold(
        // ✅ Nền trắng tinh
        containerColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black // ✅ icon quay lại màu đen
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.uth_logo),
                contentDescription = null,
                modifier = Modifier
                    .height(80.dp)
                    .padding(top = 20.dp)
            )
            Spacer(Modifier.height(8.dp))
            Text("SmartTasks", color = BlueUTH, style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(20.dp))
            Text("Create new password", style = MaterialTheme.typography.titleMedium)
            Text(
                "Your new password must be different from previously used password",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Spacer(Modifier.height(20.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(10.dp))
            OutlinedTextField(
                value = confirm,
                onValueChange = { confirm = it },
                label = { Text("Confirm Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(20.dp))
            Button(
                onClick = {
                    if (password == confirm && password.isNotEmpty()) {
                        val encodedPassword = URLEncoder.encode(password, StandardCharsets.UTF_8.toString())
                        navController.navigate("confirm/$email/$encodedPassword")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = BlueUTH)
            ) {
                Text("Next")
            }
        }
    }
}
