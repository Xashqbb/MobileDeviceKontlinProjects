package com.example.laba_11

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.laba_11.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postId = intent.getIntExtra("POST_ID", -1)
        val postTitle = intent.getStringExtra("POST_TITLE") ?: "N/A"
        val postBody = intent.getStringExtra("POST_BODY") ?: "N/A"

        binding.textId.text = "Post ID: $postId"
        binding.textTitle.text = postTitle
        binding.textBody.text = postBody
    }
}
