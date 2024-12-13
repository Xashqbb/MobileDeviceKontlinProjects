package com.example.travelmate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.travelmate.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userId = sharedPref.getString("current_user_id", null) // Retrieve as String

        if (userId.isNullOrEmpty()) {
            // Redirect to login activity if user is not logged in
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        // Inflate the layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the toolbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Initialize NavController
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Setup ActionBar with NavController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Handle Profile Icon click
        val profileIcon = findViewById<ImageView>(R.id.profile_icon)
        if (profileIcon == null) {
            Toast.makeText(this, "Profile icon is missing", Toast.LENGTH_SHORT).show()
        } else {
            profileIcon.setOnClickListener {
                navController.navigate(R.id.profileFragment)
            }
        }
    }
    override fun onStart() {
        super.onStart()

        FirebaseAuth.getInstance().addAuthStateListener { auth ->
            val currentUser = auth.currentUser
            if (currentUser == null) {
                // Redirect to login if user is logged out
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}