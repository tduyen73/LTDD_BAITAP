package com.example.btvn_tuan4_02

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    var email = mutableStateOf("")
    var verificationCode = mutableStateOf("")
    var newPassword = mutableStateOf("")
}
