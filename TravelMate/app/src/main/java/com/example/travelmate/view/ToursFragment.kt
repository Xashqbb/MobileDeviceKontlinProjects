package com.example.travelmate

import TourAdapter
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travelmate.TourViewModel

class ToursFragment : Fragment(R.layout.fragment_tours) {

    private lateinit var tourViewModel: TourViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var sortByCategoryButton: Button
    private lateinit var sortByTagsButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the ViewModel before using it
        tourViewModel = ViewModelProvider(this).get(TourViewModel::class.java)

        // Add test tours after ViewModel initialization
        tourViewModel.addTestTours()

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val tourAdapter = TourAdapter()
        recyclerView.adapter = tourAdapter

        // Observe tours LiveData and update RecyclerView
        tourViewModel.tours.observe(viewLifecycleOwner, Observer { tours ->
            tourAdapter.submitList(tours)
        })

        // Load initial tours
        tourViewModel.loadTours()

        // Sorting buttons
        sortByCategoryButton = view.findViewById(R.id.sortByCategoryButton)
        sortByCategoryButton.setOnClickListener {
            tourViewModel.loadToursSortedByCategory()
        }

        sortByTagsButton = view.findViewById<Button>(R.id.sortByTagsButton)
        sortByTagsButton.setOnClickListener {
            tourViewModel.loadToursSortedByTags()
        }
    }
}
