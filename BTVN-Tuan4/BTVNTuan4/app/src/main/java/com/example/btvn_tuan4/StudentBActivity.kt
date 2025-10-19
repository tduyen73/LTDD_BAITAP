package com.example.btvn_tuan4

import android.os.Bundle

class StudentBActivity : BaseStudentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        txtStudentName.setText("Nguyen Thi B")
    }
}
