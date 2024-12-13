package com.example.travelmate.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishlist")
data class Wishlist(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val userId: Int,
    val tourId: Int
)
