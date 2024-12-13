package com.example.travelmate.viewmodel

import androidx.lifecycle.LiveData
import com.example.travelmate.model.Tour
import com.example.travelmate.model.TourDao

class TourRepository(private val tourDao: TourDao) {

    fun getAllTours(): LiveData<List<Tour>> {
        return tourDao.getAllTours()
    }

    fun getTourByName(name: String): Tour? {
        return tourDao.getTourByName(name)
    }


    suspend fun insertTour(tour: Tour) {
        tourDao.insertTour(tour)
    }
}
