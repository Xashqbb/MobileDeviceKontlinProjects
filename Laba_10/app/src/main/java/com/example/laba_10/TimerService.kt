package com.example.laba_10

import android.app.IntentService
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.widget.TextView
import androidx.core.app.NotificationCompat

class TimerService : IntentService("TimerService") {

    private var timeRemaining: Int = 0
    private var isPaused = false

    override fun onHandleIntent(intent: Intent?) {
        timeRemaining = intent?.getIntExtra("timeInSeconds", 0) ?: 0

        while (timeRemaining > 0 && !isPaused) {
            Thread.sleep(1000)
            timeRemaining--
            sendBroadcast(Intent("com.example.timerapp.UPDATE_TIME").putExtra("timeRemaining", timeRemaining))

            if (timeRemaining == 0) {
                sendNotification("Таймер завершений!")
            }
        }
    }

    fun pauseTimer() {
        isPaused = true
    }

    fun resetTimer() {
        timeRemaining = 0
        isPaused = true
    }

    private fun sendNotification(message: String) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(this, "TIMER_CHANNEL")
            .setContentTitle("Таймер")
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_timer)
            .build()
        notificationManager.notify(1, notification)
    }
}
