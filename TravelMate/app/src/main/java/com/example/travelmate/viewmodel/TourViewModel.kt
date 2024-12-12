package com.example.travelmate

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.travelmate.model.Tour
import com.example.travelmate.view.MyApplication
import com.example.travelmate.viewmodel.TourRepository
import kotlinx.coroutines.launch

class TourViewModel(application: Application) : AndroidViewModel(application) {

    private val tourDao = (application as MyApplication).db.tourDao()
    private val tourRepository = TourRepository(tourDao)

    private val _tours = MutableLiveData<List<Tour>>()  // Non-nullable list
    val tours: LiveData<List<Tour>> get() = _tours

    fun loadTours() {
        viewModelScope.launch {
            val toursFromDb = tourRepository.getAllTours().value
            toursFromDb?.let {
                _tours.postValue(it)
            } ?: Log.d("ViewModel", "No tours found in database")
        }
    }


    fun loadToursSortedByCategory() {
        viewModelScope.launch {
            val sortedTours = tourRepository.getAllTours().value?.sortedBy { it.country } ?: emptyList()
            _tours.postValue(sortedTours)
        }
    }

    fun loadToursSortedByTags() {
        viewModelScope.launch {
            val sortedTours = tourRepository.getAllTours().value?.sortedBy { it.tags } ?: emptyList()
            _tours.postValue(sortedTours)
        }
    }

    // Method for adding test tours
    fun addTestTours() {
        viewModelScope.launch {
            val testTours = listOf(
                Tour(
                    id = 0,
                    title = "Paris Adventure",
                    description = "A romantic getaway exploring the sights of Paris.",
                    country = "France",
                    imageResource = R.drawable.paris_image,
                    tags = "romantic, sightseeing",
                    latitude = 48.8566,
                    longitude = 2.3522
                ),
                Tour(
                    id = 0,
                    title = "Tokyo Highlights",
                    description = "Experience the vibrant culture and tech wonders of Tokyo.",
                    country = "Japan",
                    imageResource = R.drawable.tokyo_image,
                    tags = "city tour, culture",
                    latitude = 35.6762,
                    longitude = 139.6503
                )
            )
            testTours.forEach { tourRepository.insertTour(it) }
        }
    }
}
