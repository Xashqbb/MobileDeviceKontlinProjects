package com.example.laba_10

import android.os.Bundle
import android.os.AsyncTask
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var startButton: Button
    private lateinit var pauseButton: Button
    private lateinit var resetButton: Button
    private lateinit var timerTextView: TextView
    private lateinit var timeEditText: EditText
    private var timerAsyncTask: TimerAsyncTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.startButton)
        pauseButton = findViewById(R.id.pauseButton)
        resetButton = findViewById(R.id.resetButton)
        timerTextView = findViewById(R.id.timerTextView)
        timeEditText = findViewById(R.id.timeEditText)

        startButton.setOnClickListener {
            val timeInSeconds = timeEditText.text.toString().toIntOrNull() ?: 0
            if (timeInSeconds > 0) {
                startTimer(timeInSeconds)
                startButton.visibility = Button.GONE
                pauseButton.visibility = Button.VISIBLE
            }
        }

        pauseButton.setOnClickListener {
            if (pauseButton.text == "Зупинити") {
                timerAsyncTask?.pauseTimer()
                pauseButton.text = "Продовжити"
            } else {
                timerAsyncTask?.resumeTimer()
                pauseButton.text = "Зупинити"
            }
        }

        resetButton.setOnClickListener {
            timerAsyncTask?.resetTimer()
            pauseButton.text = "Зупинити"
            startButton.visibility = Button.VISIBLE
        }
    }

    private fun startTimer(timeInSeconds: Int) {
        // Create a new TimerAsyncTask instance if it is not running
        if (timerAsyncTask?.status != AsyncTask.Status.RUNNING) {
            timerAsyncTask = TimerAsyncTask(timerTextView)
            timerAsyncTask?.execute(timeInSeconds)
        }
    }
}
