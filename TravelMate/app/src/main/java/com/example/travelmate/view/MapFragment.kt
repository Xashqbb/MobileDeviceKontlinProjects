package com.example.travelmate

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.travelmate.model.Tour
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(R.layout.fragment_map), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private lateinit var tour: Tour // Модель туру, яку потрібно передати

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Отримання переданого об'єкта туру
        arguments?.let {
            tour = it.getParcelable("tour") ?: return
        }

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap

        // Додавання маркера на карті
        val tourLocation = LatLng(tour.latitude, tour.longitude)
        googleMap.addMarker(MarkerOptions().position(tourLocation).title(tour.title))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tourLocation, 10f))
    }
}
