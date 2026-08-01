package com.example.teknofestdemo

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TraceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trace)

        val btnClose = findViewById<Button>(R.id.btnCloseTrace)
        btnClose.setOnClickListener {
            finish()
        }
    }
}