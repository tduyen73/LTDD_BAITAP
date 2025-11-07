package com.example.bt_tuan6.data.model

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val status: String,
    val dueDate: String? = null
)
