package com.example.smart_tasks.ui.theme.screens

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.smart_tasks.R
import com.example.smart_tasks.ui.theme.BlueUTH

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerifyCodeScreen(navController: NavController, email: String) {
    var code by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color.White, // ✅ nền trắng
        topBar = {
            CenterAlignedTopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black // ✅ icon đen
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
            Spacer(Modifier.height(20.dp))

            Image(
                painter = painterResource(id = R.drawable.uth_logo),
                contentDescription = null,
                modifier = Modifier.height(80.dp)
            )
            Spacer(Modifier.height(8.dp))
            Text("SmartTasks", color = BlueUTH, style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(20.dp))
            Text("Verify Code", style = MaterialTheme.typography.titleMedium)
            Text(
                "We just sent you a verification code to your email:",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(email, color = Color.Black, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(20.dp))

            OutlinedTextField(
                value = code,
                onValueChange = { code = it },
                label = { Text("Enter Code") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {
                    if (code.isNotEmpty()) {
                        navController.navigate("reset_password/$email")
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
