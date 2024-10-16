package com.example.lab6

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {
    private var batteryReceiver: BatteryReceiver? = null
    private var isListening = false
    private lateinit var batteryCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        batteryCheckBox = findViewById(R.id.batteryCheckBox)
        batteryReceiver = BatteryReceiver()

        batteryCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                startListening()
            } else {
                stopListening()
            }
        }
    }

    private fun startListening() {
        isListening = true
        Toast.makeText(this, "Listening to battery changes", Toast.LENGTH_SHORT).show()
        registerReceiver(batteryReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }

    private fun stopListening() {
        isListening = false
        Toast.makeText(this, "Stopped listening to battery changes", Toast.LENGTH_SHORT).show()
        unregisterReceiver(batteryReceiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isListening) {
            stopListening()
        }
    }
}
