package com.example.teknofestdemo

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.MediaController
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private var isQodEnabled = false
    private var isDarkMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnLogout = findViewById<Button>(R.id.btnLogout)
        val btnEnableQod = findViewById<Button>(R.id.btnEnableQod)
        val textQodStatus = findViewById<TextView>(R.id.textQodStatus)
        val btnToggleTheme = findViewById<Button>(R.id.btnToggleTheme)
        val mainScrollView = findViewById<ScrollView>(R.id.mainScrollView)

        val videoView = findViewById<VideoView>(R.id.videoView)
        val textVideoQuality = findViewById<TextView>(R.id.textVideoQuality)
        val btnStartVideo = findViewById<Button>(R.id.btnStartVideo)
        val btnUploadVideo = findViewById<Button>(R.id.btnUploadVideo)

        // Çıkış Butonu
        btnLogout.setOnClickListener {
            finish()
        }

        // Açık / Koyu Tema Mantığı (Arkaplan + Kartlar + YAZILAR)
        btnToggleTheme.setOnClickListener {
            isDarkMode = !isDarkMode

            val cardVerified = findViewById<androidx.cardview.widget.CardView>(R.id.cardVerified)
            val cardQod = findViewById<androidx.cardview.widget.CardView>(R.id.cardQod)
            val cardVideo = findViewById<androidx.cardview.widget.CardView>(R.id.cardVideo)
            val topBarCard = findViewById<androidx.cardview.widget.CardView>(R.id.topBarCard)

            val textOpenGw = findViewById<TextView>(R.id.textOpenGw)
            val textQodTitle = findViewById<TextView>(R.id.textQodTitle)
            val textVideoTitle = findViewById<TextView>(R.id.textVideoTitle)

            if (isDarkMode) {
                // KOYU TEMA
                mainScrollView.setBackgroundColor(Color.parseColor("#1E1F22"))
                val darkCard = Color.parseColor("#2B2D31")
                topBarCard.setCardBackgroundColor(darkCard)
                cardVerified.setCardBackgroundColor(darkCard)
                cardQod.setCardBackgroundColor(darkCard)
                cardVideo.setCardBackgroundColor(darkCard)

                // Yazılar Beyaz
                textOpenGw.setTextColor(Color.WHITE)
                textQodTitle.setTextColor(Color.WHITE)
                textVideoTitle.setTextColor(Color.WHITE)

                btnToggleTheme.text = "☀️ Açık"
            } else {
                // AÇIK TEMA
                mainScrollView.setBackgroundColor(Color.parseColor("#F0F2F5"))
                val lightCard = Color.WHITE
                topBarCard.setCardBackgroundColor(lightCard)
                cardVerified.setCardBackgroundColor(lightCard)
                cardQod.setCardBackgroundColor(lightCard)
                cardVideo.setCardBackgroundColor(lightCard)

                // Yazılar Siyah
                textOpenGw.setTextColor(Color.BLACK)
                textQodTitle.setTextColor(Color.BLACK)
                textVideoTitle.setTextColor(Color.BLACK)

                btnToggleTheme.text = "🌙 Koyu"
            }
        }

        // QoD Mantığı
        btnEnableQod.setOnClickListener {
            isQodEnabled = !isQodEnabled
            if (isQodEnabled) {
                textQodStatus.text = "Status: REQUESTED | Süre: 60s"
                btnEnableQod.text = "Quality-on-Demand'i Kapat"
                btnEnableQod.setBackgroundColor(Color.parseColor("#ED4245"))
                textVideoQuality.text = "Kalite: 1080p"
                textVideoQuality.setBackgroundColor(Color.parseColor("#23A55A"))
            } else {
                textQodStatus.text = "Status: KAPALI | Süre: 0s"
                btnEnableQod.text = "Quality-on-Demand'i Aç"
                btnEnableQod.setBackgroundColor(Color.parseColor("#5865F2"))
                textVideoQuality.text = "Kalite: 240p"
                textVideoQuality.setBackgroundColor(Color.parseColor("#ED4245"))
            }
        }

        // VİDEO OYNATMA (Kararlı Sürüm + Media Controller)
        btnStartVideo.setOnClickListener {
            try {
                val videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"
                val uri = Uri.parse(videoUrl)
                videoView.setVideoURI(uri)

                val mediaController = MediaController(this)
                mediaController.setAnchorView(videoView)
                videoView.setMediaController(mediaController)

                videoView.setOnPreparedListener { mediaPlayer ->
                    mediaPlayer.isLooping = true
                    videoView.start()
                    Toast.makeText(this, "Video akışı başlatıldı!", Toast.LENGTH_SHORT).show()
                }

                btnUploadVideo.visibility = View.VISIBLE
            } catch (e: Exception) {
                Toast.makeText(this, "Oynatma hatası", Toast.LENGTH_SHORT).show()
            }
        }

        // Result Ekranına Geçiş ve Temayı Taşıma
        btnUploadVideo.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("isDarkMode", isDarkMode)
            startActivity(intent)
        }
    }
}