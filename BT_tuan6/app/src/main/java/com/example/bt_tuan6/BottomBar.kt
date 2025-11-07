package com.example.bt_tuan6.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BottomBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { navController.navigate("home") }) {
            Icon(Icons.Filled.Home, contentDescription = "Home", tint = Color(0xFF3F51B5))
        }

        IconButton(onClick = { /* Lịch */ }) {
            Icon(Icons.Filled.CalendarToday, contentDescription = "Calendar", tint = Color.Gray)
        }

        FloatingActionButton(
            onClick = { navController.navigate("empty") },
            containerColor = Color(0xFF2196F3)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add", tint = Color.White)
        }

        IconButton(onClick = { /* Cài đặt */ }) {
            Icon(Icons.Filled.Settings, contentDescription = "Settings", tint = Color.Gray)
        }
    }
}
