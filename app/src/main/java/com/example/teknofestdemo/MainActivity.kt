package com.example.teknofestdemo // Kendi paket adın olduğundan emin ol!

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Connection logs menüsünün açık/kapalı durumunu tutuyoruz
    private var isLogsExpanded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSignIn = findViewById<Button>(R.id.btnSignIn)
        val editPhoneNumber = findViewById<EditText>(R.id.editPhoneNumber)

        val errorSection = findViewById<LinearLayout>(R.id.errorSection)
        val btnToggleLogs = findViewById<LinearLayout>(R.id.btnToggleLogs)
        val textLogDetails = findViewById<TextView>(R.id.textLogDetails)
        val iconExpand = findViewById<TextView>(R.id.iconExpand)

        // Connection Logs Genişletme/Daraltma İşlemi
        btnToggleLogs.setOnClickListener {
            isLogsExpanded = !isLogsExpanded
            if (isLogsExpanded) {
                textLogDetails.visibility = View.VISIBLE
                iconExpand.text = "▲"
            } else {
                textLogDetails.visibility = View.GONE
                iconExpand.text = "▼"
            }
        }

        btnSignIn.setOnClickListener {
            val msisdn = editPhoneNumber.text.toString().trim()

            // Her tıklamada önce hata mesajını gizleyelim
            errorSection.visibility = View.GONE

            // Butonu yükleniyor durumuna al
            btnSignIn.isEnabled = false
            btnSignIn.text = "Verifying over network..."
            btnSignIn.setBackgroundColor(Color.parseColor("#FEE75C")) // Sarı
            btnSignIn.setTextColor(Color.BLACK)

            // API'ye gidip gelme süresi simülasyonu (1.5 saniye)
            btnSignIn.postDelayed({

                // DOKÜMANDAKİ ŞART: Yalnızca doğrulanmış numara (test numarası) true döner
                if (msisdn == "+905390000020") {

                    Toast.makeText(this, "devicePhoneNumberVerified: true", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)

                    // DOKÜMANDAKİ ŞART: Çapraz geçişle (crossfade) ana uygulamaya açılır
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()

                } else {
                    // DOKÜMANDAKİ HATA ŞARTI: "Sign-in failed" ve "Connection logs" görünür
                    btnSignIn.isEnabled = true
                    btnSignIn.text = "Sign in"
                    btnSignIn.setBackgroundColor(Color.parseColor("#5865F2")) // Eski rengine döner
                    btnSignIn.setTextColor(Color.WHITE)

                    // Hata kartını ekrana çıkar
                    errorSection.visibility = View.VISIBLE

                    // Her hatada logları kapalı konuma sıfırla ki kullanıcı kendi açsın
                    isLogsExpanded = false
                    textLogDetails.visibility = View.GONE
                    iconExpand.text = "▼"
                }

            }, 1500)
        }
    }
}