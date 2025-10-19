package com.example.thuchanh_2

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun OnboardingScreen(navController: NavController) {
    val pages = listOf(
        OnboardingPage(
            title = "Easy Time Management",
            description = "Manage your daily tasks effectively and stay organized.",
            image = R.drawable.onboard2
        ),
        OnboardingPage(
            title = "Increase Work Effectiveness",
            description = "Track your progress and boost your productivity.",
            image = R.drawable.onboard3
        ),
        OnboardingPage(
            title = "Reminder Notification",
            description = "Never miss important tasks with smart reminders.",
            image = R.drawable.onboard3
        )
    )

    var currentPage by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFF4FB)) // nền trắng xanh nhạt
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Crossfade(targetState = currentPage) { page ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = pages[page].image),
                        contentDescription = null,
                        modifier = Modifier
                            .height(230.dp)
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(20.dp)
                        ) {
                            Text(
                                text = pages[page].title,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF308CFF)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = pages[page].description,
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                pages.forEachIndexed { index, _ ->
                    Box(
                        modifier = Modifier
                            .size(if (index == currentPage) 12.dp else 8.dp)
                            .clip(CircleShape)
                            .background(if (index == currentPage) Color(0xFF308CFF) else Color.Gray)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (currentPage > 0) {
                    IconButton(onClick = { currentPage-- }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF308CFF)
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.width(48.dp))
                }

                Button(
                    onClick = {
                        if (currentPage < pages.size - 1) {
                            currentPage++
                        } else {
                            navController.navigate("home")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF308CFF)),
                    modifier = Modifier.width(150.dp).height(50.dp)
                ) {
                    Text(
                        text = if (currentPage < pages.size - 1) "Next" else "Get Started",
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}
