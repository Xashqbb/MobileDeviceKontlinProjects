package com.example.laba_3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.laba_3.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("extra_name")
        val surname = intent.getStringExtra("extra_surname")
        val email = intent.getStringExtra("extra_email")

        Log.d("SecondActivity", "Received data: Name=$name, Surname=$surname, Email=$email")

        // Відображення привітання
        val greetingText = "Hello, $name $surname! Your email is $email."
        val textView: TextView = findViewById(R.id.textView)
        textView.text = greetingText

        binding.button2.setOnClickListener {
            val intent = Intent()
            intent.putExtra("data_return", "Thanks for the info!")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        binding.button3.setOnClickListener {
            // Відкриття DetailActivity
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("extra_name", name)
                putExtra("extra_surname", surname)
                putExtra("extra_email", email)
            }
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "onStart called", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "onResume called", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "onPause called", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "onStop called", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "onDestroy called", Toast.LENGTH_SHORT).show()
    }
}
