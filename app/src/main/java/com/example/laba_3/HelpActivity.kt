package com.example.laba_3

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HelpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        val helpTextView: TextView = findViewById(R.id.helpTextView)
        helpTextView.text = "This app allows you to enter your details and see them displayed in the second activity."
    }
}
