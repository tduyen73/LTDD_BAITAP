package com.example.bt_tuan6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.bt_tuan6.ui.screens.AppNavGraph   // ✅ dòng này quan trọng
import com.example.bt_tuan6.ui.theme.BT_tuan6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BT_tuan6Theme(darkTheme = false) {
                val navController = rememberNavController()
                AppNavGraph(navController)
            }
        }
    }
}
