package com.example.laba_12

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.laba_12.databinding.ActivityTaskDetailBinding
import com.google.android.material.snackbar.Snackbar

class TaskDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val task = intent.getParcelableExtra<Task>("task")
        task?.let {
            binding.taskTitleDetail.text = it.title
            binding.taskDescriptionDetail.text = it.description
        }

        binding.deleteTaskButton.setOnClickListener {
            task?.let {
                val resultIntent = Intent().apply {
                    putExtra("deletedTask", it)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}
