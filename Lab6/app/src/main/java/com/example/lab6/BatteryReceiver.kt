package com.example.lab6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.widget.Toast
import kotlin.math.roundToInt

class BatteryReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        val batteryPct = (level / scale.toFloat() * 100).roundToInt()

        Toast.makeText(context, "Battery level changed: $batteryPct%", Toast.LENGTH_SHORT).show()
    }
}