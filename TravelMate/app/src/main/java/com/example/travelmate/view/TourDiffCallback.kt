package com.example.travelmate.view

import androidx.recyclerview.widget.DiffUtil
import com.example.travelmate.model.Tour

class TourDiffCallback : DiffUtil.ItemCallback<Tour>() {
    override fun areItemsTheSame(oldItem: Tour, newItem: Tour): Boolean {
        // Перевірка, чи це той самий об'єкт (за унікальним ідентифікатором)
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Tour, newItem: Tour): Boolean {
        // Перевірка, чи вміст об'єкта не змінився
        return oldItem == newItem
    }
}
