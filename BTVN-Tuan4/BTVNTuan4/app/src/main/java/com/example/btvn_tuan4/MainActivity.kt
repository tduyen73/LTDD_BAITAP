package com.example.btvn_tuan4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnA = findViewById<Button>(R.id.btnA)
        val btnB = findViewById<Button>(R.id.btnB)
        val btnC = findViewById<Button>(R.id.btnC)

        btnA.setOnClickListener {
            startActivity(Intent(this, StudentAActivity::class.java))
        }

        btnB.setOnClickListener {
            startActivity(Intent(this, StudentBActivity::class.java))
        }

        btnC.setOnClickListener {
            startActivity(Intent(this, StudentCActivity::class.java))
        }
    }
}
