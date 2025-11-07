package com.example.bt_tuan6.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bt_tuan6.data.model.Task
import com.example.bt_tuan6.data.network.RetrofitInstance
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(taskId: Int, navController: NavController) {
    val scope = rememberCoroutineScope()
    var task by remember { mutableStateOf<Task?>(null) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        scope.launch {
            try {
                val res = RetrofitInstance.api.getTaskDetail(taskId)
                if (res.isSuccessful) task = res.body()
            } catch (e: Exception) {
                Log.e("DETAIL", "Error: ${e.message}")
            }
            loading = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F9FC))
            .padding(16.dp)
    ) {
        if (loading) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        } else {
            task?.let { t ->
                Column(Modifier.fillMaxSize()) {

                    // 🔹 Thanh trên (Back + Delete)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier
                                .size(40.dp)
                                .background(Color(0xFF21A2FF), shape = RoundedCornerShape(100))
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }

                        Text("Detail", fontWeight = FontWeight.Bold, fontSize = 20.sp)

                        IconButton(
                            onClick = {
                                scope.launch {
                                    try {
                                        RetrofitInstance.api.deleteTask(taskId)
                                    } catch (_: Exception) {}
                                    navController.popBackStack()
                                }
                            },
                            modifier = Modifier
                                .size(40.dp)
                                .background(Color(0xFFFF9800), shape = RoundedCornerShape(100))
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Delete",
                                tint = Color.White
                            )
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    Text(
                        text = t.title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(t.description, color = Color.Gray, fontSize = 13.sp)

                    Spacer(Modifier.height(16.dp))

                    // 🔹 3 Tag
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        DetailTag("Category", "Work")
                        DetailTag("Status", t.status)
                        DetailTag("Priority", "High")
                    }

                    Spacer(Modifier.height(20.dp))
                    Text("Subtasks", fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(8.dp))

                    RepeatSubtask("This task is related to Fitness. It needs to be completed")
                    RepeatSubtask("This task is related to Fitness. It needs to be completed")
                    RepeatSubtask("This task is related to Fitness. It needs to be completed")

                    Spacer(Modifier.height(20.dp))
                    Text("Attachments", fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFE0E0E0), RoundedCornerShape(10.dp))
                            .padding(12.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("📎", fontSize = 16.sp)
                            Spacer(Modifier.width(8.dp))
                            Text("document_1_0.pdf", fontSize = 13.sp)
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Button(
                        onClick = {
                            scope.launch {
                                try {
                                    RetrofitInstance.api.deleteTask(taskId)
                                } catch (_: Exception) {}
                                navController.popBackStack()
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Delete Task")
                    }
                }
            } ?: Text("Không tìm thấy task", Modifier.align(Alignment.Center))
        }
    }
}

@Composable
private fun RepeatSubtask(text: String) {
    var checked by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF1F1F1), RoundedCornerShape(10.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it },
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFF1E88E5),
                uncheckedColor = Color.Black
            )
        )
        Spacer(Modifier.width(8.dp))
        Text(text, fontSize = 13.sp)
    }
}

@Composable
private fun DetailTag(title: String, value: String) {
    Column(
        modifier = Modifier
            .background(Color(0xFFF9C9CC), shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(title, fontSize = 11.sp)
        Text(value, fontWeight = FontWeight.SemiBold)
    }
}
