package com.example.teknofestdemo

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Ana sayfadan gelen tema bilgisini yakalıyoruz
        val isDarkMode = intent.getBooleanExtra("isDarkMode", true)
        val resultScrollView = findViewById<ScrollView>(R.id.resultScrollView)

        if (isDarkMode) {
            resultScrollView.setBackgroundColor(android.graphics.Color.parseColor("#1E1F22"))
        } else {
            resultScrollView.setBackgroundColor(android.graphics.Color.parseColor("#F0F2F5"))
        }

        val btnRefresh = findViewById<Button>(R.id.btnRefresh)
        val btnOpenTrace = findViewById<Button>(R.id.btnOpenTrace)
        val textProcessing = findViewById<TextView>(R.id.textProcessing)
        val resultContent = findViewById<LinearLayout>(R.id.resultContent)

        // Trace butonuna basınca TraceActivity'yi açar
        btnOpenTrace.setOnClickListener {
            startActivity(android.content.Intent(this, TraceActivity::class.java))
        }

        // Yenile Butonu Mantığı
        btnRefresh.setOnClickListener {
            textProcessing.text = "⏳ AI sonuçları güncelleniyor..."
            resultContent.visibility = LinearLayout.GONE

            textProcessing.postDelayed({
                textProcessing.visibility = LinearLayout.GONE
                resultContent.visibility = LinearLayout.VISIBLE
            }, 1500)
        }

        // Sayfa ilk açıldığında sahte yükleme simülasyonu
        textProcessing.postDelayed({
            textProcessing.visibility = LinearLayout.GONE
            resultContent.visibility = LinearLayout.VISIBLE
        }, 2000)
    }
}