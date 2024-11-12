package com.example.laba_9

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class FullscreenPhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen_photo)

        val photoUri = intent.getStringExtra("photoUri")
        val imageView: ImageView = findViewById(R.id.fullscreenImageView)

        Glide.with(this).load(Uri.parse(photoUri)).into(imageView)
    }
}
