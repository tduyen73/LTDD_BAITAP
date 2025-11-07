package com.example.bt_tuan6.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bt_tuan6.data.model.Task
import com.example.bt_tuan6.data.network.RetrofitInstance
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController) {
    val scope = rememberCoroutineScope()
    var tasks by remember { mutableStateOf<List<Task>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        scope.launch {
            try {
                val res = RetrofitInstance.api.getTasks()
                if (res.isSuccessful) {
                    tasks = res.body() ?: emptyList()
                }
            } catch (_: Exception) {}
            isLoading = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFF7F9FC), Color(0xFFE9EEF5))
                )
            )
            .padding(16.dp)
    ) {
        when {
            isLoading -> {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }

            tasks.isEmpty() -> {
                // ✅ Gọi đúng phiên bản có NavController
                EmptyView(navController)
            }

            else -> {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Header
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "SmartTasks",
                                color = Color(0xFF1E88E5),
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "A simple and efficient to-do app",
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        }
                        Box(
                            modifier = Modifier
                                .size(26.dp)
                                .background(Color(0xFFFFB300), RoundedCornerShape(8.dp))
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    // Danh sách task
                    tasks.forEachIndexed { index, task ->
                        TaskCard(
                            task = task,
                            index = index,
                            onClick = { navController.navigate("detail/${task.id}") }
                        )
                        Spacer(Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}
