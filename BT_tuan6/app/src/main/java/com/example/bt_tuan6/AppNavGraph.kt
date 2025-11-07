package com.example.bt_tuan6.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            // ✅ Bottom bar chỉ hiển thị ở Home
            val currentRoute = navController.currentBackStackEntry?.destination?.route
            if (currentRoute == "home") {
                BottomBar(navController)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") { HomeScreen(navController) }
            composable("empty") { EmptyView(navController) }
            composable("detail/{taskId}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("taskId")?.toIntOrNull() ?: 0
                DetailScreen(taskId = id, navController = navController)
            }
        }
    }
}
