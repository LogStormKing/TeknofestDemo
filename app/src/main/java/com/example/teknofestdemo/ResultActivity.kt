package com.example.teknofestdemo

import android.graphics.Color
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

        // TEMA YAKALAMA VE UYGULAMA MANTIĞI
        val isDarkMode = intent.getBooleanExtra("isDarkMode", true)

        val resultScrollView = findViewById<ScrollView>(R.id.resultScrollView)
        val cardVehicle = findViewById<androidx.cardview.widget.CardView>(R.id.cardVehicle)
        val cardDetections = findViewById<androidx.cardview.widget.CardView>(R.id.cardDetections)
        val cardJson = findViewById<androidx.cardview.widget.CardView>(R.id.cardJson)

        val textAiResultTitle = findViewById<TextView>(R.id.textAiResultTitle)
        val textVehicleTitle = findViewById<TextView>(R.id.textVehicleTitle)
        val textDetectionsTitle = findViewById<TextView>(R.id.textDetectionsTitle)
        val textJsonTitle = findViewById<TextView>(R.id.textJsonTitle)
        val textVehicleInfo = findViewById<TextView>(R.id.textVehicleInfo)

        if (isDarkMode) {
            // KOYU TEMA
            resultScrollView.setBackgroundColor(Color.parseColor("#1E1F22"))
            val darkCard = Color.parseColor("#2B2D31")
            cardVehicle.setCardBackgroundColor(darkCard)
            cardDetections.setCardBackgroundColor(darkCard)
            cardJson.setCardBackgroundColor(darkCard)

            textAiResultTitle.setTextColor(Color.WHITE)
            textVehicleTitle.setTextColor(Color.WHITE)
            textDetectionsTitle.setTextColor(Color.WHITE)
            textJsonTitle.setTextColor(Color.WHITE)
        } else {
            // AÇIK TEMA
            resultScrollView.setBackgroundColor(Color.parseColor("#F0F2F5"))
            val lightCard = Color.WHITE
            cardVehicle.setCardBackgroundColor(lightCard)
            cardDetections.setCardBackgroundColor(lightCard)
            cardJson.setCardBackgroundColor(lightCard)

            textAiResultTitle.setTextColor(Color.BLACK)
            textVehicleTitle.setTextColor(Color.BLACK)
            textDetectionsTitle.setTextColor(Color.BLACK)
            textJsonTitle.setTextColor(Color.BLACK)
            textVehicleInfo.setTextColor(Color.parseColor("#333333")) // Açık modda gri okunsun diye
        }

        // SAYFA İŞLEVLERİ
        val btnRefresh = findViewById<Button>(R.id.btnRefresh)
        val btnOpenTrace = findViewById<Button>(R.id.btnOpenTrace)
        val textProcessing = findViewById<TextView>(R.id.textProcessing)
        val resultContent = findViewById<LinearLayout>(R.id.resultContent)

        btnOpenTrace.setOnClickListener {
            startActivity(android.content.Intent(this, TraceActivity::class.java))
        }

        btnRefresh.setOnClickListener {
            textProcessing.text = "⏳ AI sonuçları güncelleniyor..."
            resultContent.visibility = LinearLayout.GONE
            textProcessing.postDelayed({
                textProcessing.visibility = LinearLayout.GONE
                resultContent.visibility = LinearLayout.VISIBLE
            }, 1500)
        }

        textProcessing.postDelayed({
            textProcessing.visibility = LinearLayout.GONE
            resultContent.visibility = LinearLayout.VISIBLE
        }, 2000)
    }
}