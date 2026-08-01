package com.example.teknofestdemo // Kendi paket adınla değişecek

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // XML'deki elemanları koda bağlıyoruz
        val inputPhone = findViewById<EditText>(R.id.inputPhone)
        val btnSignIn = findViewById<Button>(R.id.btnSignIn)
        val errorContainer = findViewById<LinearLayout>(R.id.errorContainer)

        btnSignIn.setOnClickListener {
            val phone = inputPhone.text.toString()

            // Kılavuzdaki test numarası kontrolü
            if (phone == "+905390000020") {
                errorContainer.visibility = View.GONE
                Toast.makeText(this, "Başarılı! Ana sayfaya geçiliyor...", Toast.LENGTH_SHORT).show()

                // TODO: İleride burada HomeActivity'e geçiş kodunu yazacağız.
                startActivity(android.content.Intent(this, HomeActivity::class.java))
            } else {
                // Hata durumunda log ekranını göster
                errorContainer.visibility = View.VISIBLE
            }
        }
    }
}