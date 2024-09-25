package com.example.laba_3

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val name = intent.getStringExtra("extra_name")
        val surname = intent.getStringExtra("extra_surname")
        val email = intent.getStringExtra("extra_email")

        val detailText = "Name: $name\nSurname: $surname\nEmail: $email"
        val textView: TextView = findViewById(R.id.detailTextView)
        textView.text = detailText
    }
}
