package com.example.travelmate.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tours")
data class Tour(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String,
    val country: String,
    val imageResource: Int,
    val tags: String,
    val latitude: Double,
    val longitude: Double
)
