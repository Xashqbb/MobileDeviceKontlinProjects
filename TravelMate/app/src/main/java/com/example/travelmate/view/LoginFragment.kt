package com.example.travelmate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.travelmate.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        // Handle Login Button Click
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            loginUser(email, password)
        }

        // Handle Register Button Click
        binding.registerButton.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        return binding.root
    }

    private fun loginUser(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Save the user ID as a String
                    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
                    val sharedPref = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                    sharedPref.edit().putString("current_user_id", userId).apply()

                    // Navigate to the ToursFragment
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToToursFragment())
                } else {
                    Toast.makeText(requireContext(), "Login failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }

}


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check Firebase authentication state
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            // User is already authenticated, redirect to main content
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_login)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, NavHostFragment.create(R.navigation.nav_graph))
                .commit()
        }
    }
}




