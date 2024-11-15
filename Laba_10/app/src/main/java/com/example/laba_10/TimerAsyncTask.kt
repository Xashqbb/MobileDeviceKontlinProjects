package com.example.laba_10

import android.os.AsyncTask
import android.widget.TextView
import java.util.concurrent.TimeUnit

class TimerAsyncTask(private val timerTextView: TextView) : AsyncTask<Int, Int, Void>() {

    private var timeRemaining: Int = 0
    private var isPaused: Boolean = false

    override fun doInBackground(vararg params: Int?): Void? {
        timeRemaining = params[0] ?: 0

        while (timeRemaining > 0 && !isCancelled) {
            if (isPaused) {
                Thread.sleep(100)
                continue
            }
            timeRemaining--
            publishProgress(timeRemaining)
            TimeUnit.SECONDS.sleep(1)
        }
        return null
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        timerTextView.text = formatTime(values[0] ?: 0)
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        timerTextView.text = "Час вичерпано!"
    }

    fun pauseTimer() {
        isPaused = true
    }

    fun resumeTimer() {
        isPaused = false
        // Check if task is already running before calling execute again
        if (status != AsyncTask.Status.RUNNING) {
            execute(timeRemaining)
        }
    }

    fun resetTimer() {
        cancel(true)
        timerTextView.text = "00:00"
    }

    private fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d", minutes, remainingSeconds)
    }
}
