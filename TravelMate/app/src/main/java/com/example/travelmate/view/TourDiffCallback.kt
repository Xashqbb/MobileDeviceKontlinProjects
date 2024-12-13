package com.example.travelmate.view

import androidx.recyclerview.widget.DiffUtil
import com.example.travelmate.model.Tour

class TourDiffCallback : DiffUtil.ItemCallback<Tour>() {
    override fun areItemsTheSame(oldItem: Tour, newItem: Tour): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Tour, newItem: Tour): Boolean {
        return oldItem == newItem
    }
}
