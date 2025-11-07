package com.example.btvn_tuan4

import android.os.Bundle

class StudentAActivity : BaseStudentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        txtStudentName.setText("Nguyen Van A")
    }
}
