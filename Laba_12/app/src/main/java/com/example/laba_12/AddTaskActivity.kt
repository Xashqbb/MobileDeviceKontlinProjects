package com.example.laba_12

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.laba_12.databinding.ActivityAddTaskBinding

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addTaskButton.setOnClickListener {
            val taskTitle = binding.taskTitleEditText.text.toString()
            val taskDescription = binding.taskDescriptionEditText.text.toString()
            val task = Task(taskTitle, taskDescription)

            val resultIntent = intent
            resultIntent.putExtra("newTask", task)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
