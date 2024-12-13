package com.example.travelmate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.travelmate.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterFragment : Fragment() {
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var profileImage: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(R.layout.fragment_register, container, false)

        email = binding.findViewById(R.id.email)
        password = binding.findViewById(R.id.password)
        profileImage = binding.findViewById(R.id.profile_image)

        binding.findViewById<Button>(R.id.register_button).setOnClickListener {
            val user = User(
                email = email.text.toString(),
                password = password.text.toString(),
                profileImage = R.drawable.ic_profile
            )
            registerUser(user)
        }

        return binding
    }

    private fun registerUser(user: User) {
        if (user.email.isBlank() || user.password.isBlank()) {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(user.email).matches()) {
            Toast.makeText(requireContext(), "Please enter a valid email address", Toast.LENGTH_SHORT).show()
            return
        }

        if (user.password.length < 6) {
            Toast.makeText(requireContext(), "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show()
            return
        }

        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    saveUserDataToFirestore(user)
                } else {
                    val errorMessage = task.exception?.localizedMessage ?: "Unknown error occurred"
                    Toast.makeText(requireContext(), "Registration failed: $errorMessage", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun saveUserDataToFirestore(user: User) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val userRef = FirebaseFirestore.getInstance().collection("users").document(currentUser.uid)
            val userData = mapOf(
                "email" to user.email,
                "profileImage" to user.profileImage.toString()
            )
            userRef.set(userData)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "User registered successfully!", Toast.LENGTH_LONG).show()
                    findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(requireContext(), "Failed to save user data: ${exception.message}", Toast.LENGTH_LONG).show()
                }
        } else {
            Toast.makeText(requireContext(), "User is not authenticated", Toast.LENGTH_LONG).show()
        }
    }
}
