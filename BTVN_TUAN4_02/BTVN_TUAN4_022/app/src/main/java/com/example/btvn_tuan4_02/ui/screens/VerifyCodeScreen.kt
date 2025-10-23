package com.example.btvn_tuan4_02.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.btvn_tuan4_02.SharedViewModel
import com.example.btvn_tuan4_02.navigation.AppRoutes
import com.example.btvn_tuan4_02.ui.theme.GradientBackground
import com.example.btvn_tuan4_02.ui.theme.PrimaryButton
import com.example.btvn_tuan4_02.ui.theme.PrimaryColor
import com.example.btvn_tuan4_02.ui.theme.TextTitleColor
import androidx.compose.foundation.text.KeyboardOptions

@Composable
fun VerifyCodeScreen(navController: NavController, sharedViewModel: SharedViewModel) {

    val codeLength = 6
    var codeValues by remember { mutableStateOf(List(codeLength) { "" }) }
    val focusRequesters = List(codeLength) { FocusRequester() }
    var focusedIndex by remember { mutableStateOf(-1) }

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
                "Verify Code",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = TextTitleColor
            )
            Text(
                "Enter the code we just sent you on your registered Email",
                color = Color.Gray,
                fontSize = 14.sp
            )

            Spacer(Modifier.height(24.dp))

            // 🔹 Nhập mã OTP
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (i in 0 until codeLength) {
                    val borderColor: Color =
                        if (focusedIndex == i) PrimaryColor else Color.LightGray

                    Box(
                        modifier = Modifier
                            .width(45.dp)
                            .height(55.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                width = 2.dp,
                                color = borderColor,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        BasicTextField(
                            value = codeValues[i],
                            onValueChange = { value ->
                                if (value.length <= 1) {
                                    val newValues = codeValues.toMutableList()
                                    newValues[i] = value
                                    codeValues = newValues

                                    // 👉 Tự động nhảy ô tiếp theo
                                    if (value.isNotEmpty() && i < codeLength - 1) {
                                        focusRequesters[i + 1].requestFocus()
                                    }
                                    // 👉 Tự động lùi khi Backspace
                                    if (value.isEmpty() && i > 0) {
                                        focusRequesters[i - 1].requestFocus()
                                    }
                                }
                            },
                            singleLine = true,
                            textStyle = TextStyle(
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryColor,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            ),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .fillMaxSize()
                                .focusRequester(focusRequesters[i])
                                .onFocusChanged { focusState ->
                                    focusedIndex = if (focusState.isFocused) i else focusedIndex
                                },
                            decorationBox = { innerTextField ->
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    innerTextField()
                                }
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            // 🔹 Nút Next
            Button(
                onClick = {
                    sharedViewModel.verificationCode.value = codeValues.joinToString("")
                    navController.navigate(AppRoutes.ResetPassword)
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
                    Text(
                        "Next",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}
