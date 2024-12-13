package com.example.travelmate.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travelmate.R
import com.example.travelmate.TourAdapter
import com.example.travelmate.model.AppDatabase
import com.example.travelmate.model.Tour
import com.example.travelmate.model.Wishlist
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileFragment : Fragment(), TourAdapter.OnTourClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TourAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        recyclerView = view.findViewById(R.id.wishlistRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = TourAdapter(this)
        recyclerView.adapter = adapter

        loadWishlist()

        return view
    }

    private fun loadWishlist() {
        val database = AppDatabase.getDatabase(requireContext())
        val userId = getCurrentUserId()

        // Observe LiveData directly on the main thread
        database.wishlistDao().getWishlistForUser(userId).observe(viewLifecycleOwner) { wishlist ->
            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    // Fetch corresponding tours
                    val tours = wishlist.mapNotNull { wishlistItem ->
                        database.tourDao().getTourById(wishlistItem.tourId)
                    }

                    // Update UI in the main thread
                    withContext(Dispatchers.Main) {
                        adapter.submitList(tours)
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "Error loading wishlist: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun getCurrentUserId(): String {
        val sharedPref = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userId = sharedPref.getString("current_user_id", null)

        if (userId.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "User not logged in. Please log in.", Toast.LENGTH_LONG).show()
            requireActivity().finish() // Close the activity or navigate to login
        }

        return userId ?: ""
    }

    override fun onTourClick(tour: Tour) {
        Toast.makeText(requireContext(), "Clicked on ${tour.title}", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()

        // Check Firebase authentication state
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            Toast.makeText(requireContext(), "Please log in to access your profile", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.loginFragment)
        }
    }
}
