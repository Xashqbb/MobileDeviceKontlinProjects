package com.example.travelmate

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, @DrawableRes imageResource: Int?) {
    if (imageResource != null) {
        Glide.with(view.context)
            .load(imageResource)  // Завантажуємо зображення з ресурсів
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.error_image)
            .into(view)
    }
}


