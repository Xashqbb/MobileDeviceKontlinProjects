package com.example.travelmate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.travelmate.databinding.FragmentToursBinding
import com.example.travelmate.model.Tour

class ToursFragment : Fragment() {

    private lateinit var binding: FragmentToursBinding
    private val tourViewModel: TourViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentToursBinding.inflate(inflater, container, false)

        tourViewModel.addTestTours()

        // Use binding to access profile_icon
        binding.toolbar.findViewById<ImageView>(R.id.profile_icon).setOnClickListener {
            // Navigate to profileFragment
            findNavController().navigate(R.id.profileFragment)
        }

        // Initialize adapter
        val adapter = TourAdapter(object : TourAdapter.OnTourClickListener {
            override fun onTourClick(tour: Tour) {
                val action = ToursFragmentDirections
                    .actionToursFragmentToTourDetailFragment(tour) // Navigate using NavController
                findNavController().navigate(action) // Perform navigation
            }
        })
        binding.recyclerView.adapter = adapter

        // Observe tours and update UI
        tourViewModel.tours.observe(viewLifecycleOwner) { toursList ->
            adapter.submitList(toursList)
        }

        // Handle sort buttons
        binding.sortByCategoryButton.setOnClickListener {
            val sortedTours = tourViewModel.getToursSortedByCategory()
            adapter.submitList(sortedTours)
        }

        binding.sortByTagsButton.setOnClickListener {
            val sortedTours = tourViewModel.getToursSortedByTags()
            adapter.submitList(sortedTours)
        }

        return binding.root
    }

}
