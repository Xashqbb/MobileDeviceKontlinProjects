package com.example.travelmate

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.travelmate.model.Tour
import com.example.travelmate.viewmodel.TourRepository
import kotlinx.coroutines.launch

class TourViewModel(application: Application) : AndroidViewModel(application) {

    private val tourDao = (application as MyApplication).db.tourDao()
    private val tourRepository = TourRepository(tourDao)

    private val _tours = MutableLiveData<List<Tour>>()
    val tours: LiveData<List<Tour>> get() = _tours

    init {
        loadTours() // Initial loading of tours
    }

    // Load all tours
    private fun loadTours() {
        viewModelScope.launch {
            tourRepository.getAllTours().observeForever { tours ->
                _tours.postValue(tours)
            }
        }
    }


    // Get sorted tours by country
    fun getToursSortedByCategory(): List<Tour>? {
        return _tours.value?.sortedBy { it.country }
    }

    // Get sorted tours by tags
    fun getToursSortedByTags(): List<Tour>? {
        return _tours.value?.sortedBy { it.tags }
    }

    // Add test tours
    fun addTestTours() {
        viewModelScope.launch {
            val testTours = listOf(
                Tour(
                    0,
                    "Paris Adventure",
                    "Explore Paris DESCRIPTION",
                    "France",
                    R.drawable.paris_image,
                    "romantic, sightseeing",
                    48.8566,
                    2.3522
                ),
                Tour(
                    0,
                    "Tokyo Highlights",
                    "Discover Tokyo DESCRIPTION",
                    "Japan",
                    R.drawable.tokyo_image,
                    "city tour, culture",
                    35.6762,
                    139.6503
                ),
                Tour(
                    0,
                    "New York City",
                    "Visit NYC DESCRIPTION",
                    "USA",
                    R.drawable.nyc_image,
                    "city tour, sightseeing",
                    40.7128,
                    -74.0060
                )
            )
            testTours.forEach { tourRepository.insertTour(it) }
        }
    }
}

