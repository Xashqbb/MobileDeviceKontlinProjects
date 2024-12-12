package com.example.travelmate.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TourDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTour(tour: Tour)

    @Query("SELECT * FROM tours")
    fun getAllTours(): LiveData<List<Tour>>
}

