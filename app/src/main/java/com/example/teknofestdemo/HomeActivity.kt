package com.example.teknofestdemo // Kendi paket adınla aynı olduğundan emin ol

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private var isQodEnabled = false
    private var isDarkMode = true // Varsayılan Discord koyu modu

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

        // Açık / Koyu Tema Değiştirme Mantığı
        btnToggleTheme.setOnClickListener {
            isDarkMode = !isDarkMode
            if (isDarkMode) {
                // Koyu Tema (Discord Grisi)
                mainScrollView.setBackgroundColor(Color.parseColor("#1E1F22"))
                btnToggleTheme.text = "☀️ Açık"
                Toast.makeText(this, "Koyu tema aktif", Toast.LENGTH_SHORT).show()
            } else {
                // Açık Tema (Yumuşak Beyaz/Gri)
                mainScrollView.setBackgroundColor(Color.parseColor("#F0F2F5"))
                btnToggleTheme.text = "🌙 Koyu"
                Toast.makeText(this, "Açık tema aktif", Toast.LENGTH_SHORT).show()
            }
        }

        // QoD Aç/Kapa (Toggle) Mantığı
        btnEnableQod.setOnClickListener {
            isQodEnabled = !isQodEnabled

            if (isQodEnabled) {
                textQodStatus.text = "Status: REQUESTED | Süre: 60s"
                btnEnableQod.text = "Quality-on-Demand'i Kapat"
                btnEnableQod.setBackgroundColor(Color.parseColor("#ED4245"))

                textVideoQuality.text = "Kalite: 1080p"
                textVideoQuality.setBackgroundColor(Color.parseColor("#23A55A"))
                Toast.makeText(this, "QoD Oturumu Başlatıldı (1080p)", Toast.LENGTH_SHORT).show()
            } else {
                textQodStatus.text = "Status: KAPALI | Süre: 0s"
                btnEnableQod.text = "Quality-on-Demand'i Aç"
                btnEnableQod.setBackgroundColor(Color.parseColor("#5865F2"))

                textVideoQuality.text = "Kalite: 240p"
                textVideoQuality.setBackgroundColor(Color.parseColor("#ED4245"))
                Toast.makeText(this, "QoD Oturumu Sonlandırıldı (240p)", Toast.LENGTH_SHORT).show()
            }
        }

        // Teknofest Start - Videoyu Oynat
        btnStartVideo.setOnClickListener {
            try {
                val videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                val uri = Uri.parse(videoUrl)

                videoView.setVideoURI(uri)
                videoView.setOnPreparedListener { mediaPlayer ->
                    mediaPlayer.isLooping = true
                    videoView.start()
                }

                btnUploadVideo.visibility = View.VISIBLE
                Toast.makeText(this, "Canlı video akışı başlatıldı!", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Video oynatılamadı.", Toast.LENGTH_SHORT).show()
            }
        }

        // Upload Butonu - Sonuç Ekranına Geçiş
        btnUploadVideo.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            // isDarkMode değişkeninin durumunu (true veya false) diğer ekrana gönderiyoruz
            intent.putExtra("isDarkMode", isDarkMode)

            Toast.makeText(this, "Video yüklendi. AI Result sekmesine geçiliyor...", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
    }
}