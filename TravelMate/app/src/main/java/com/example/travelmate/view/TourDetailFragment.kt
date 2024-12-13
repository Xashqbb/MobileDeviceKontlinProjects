package com.example.travelmate

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.travelmate.databinding.FragmentTourDetailBinding
import com.example.travelmate.model.Tour
import com.example.travelmate.model.Wishlist
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class TourDetailFragment : Fragment() {

    private lateinit var binding: FragmentTourDetailBinding
    private lateinit var tour: Tour

    // Updated function to check if the user is authenticated
    private fun isUserAuthenticated(): Boolean {
        val sharedPref = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userId = sharedPref.getString("current_user_id", null) // Retrieve as String
        return !userId.isNullOrEmpty()
    }

    private fun navigateToLoginScreen() {
        findNavController().navigate(R.id.action_tourDetailFragment_to_loginFragment)
    }

    private fun addTourToWishlist(tour: Tour) {
        lifecycleScope.launch {
            val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return@launch
            val wishlist = Wishlist(0, userId.hashCode(), tour.id)
            (requireActivity().application as MyApplication).db.wishlistDao().insertWishlist(wishlist)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTourDetailBinding.inflate(inflater, container, false)

        // Get the arguments passed from the previous fragment
        arguments?.let {
            val args = TourDetailFragmentArgs.fromBundle(it)
            tour = args.tour
        }

        // Use binding to access profile_icon
        binding.toolbar.findViewById<ImageView>(R.id.profile_icon).setOnClickListener {
            findNavController().navigate(R.id.profileFragment)
        }

        // Bind tour data to the views
        binding.title.text = tour.title
        binding.description.text = tour.description
        binding.image.setImageResource(tour.imageResource)

        // Handle click on the "Add to wishlist" button
        binding.heartIcon.setOnClickListener {
            if (isUserAuthenticated()) {
                addTourToWishlist(tour)
                Toast.makeText(requireContext(), "Added to wishlist", Toast.LENGTH_SHORT).show()
            } else {
                navigateToLoginScreen()
            }
        }

        return binding.root
    }
}
