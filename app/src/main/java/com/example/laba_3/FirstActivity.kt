package com.example.laba_3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.laba_3.databinding.FirstLayoutBinding


class FirstActivity : AppCompatActivity() {

    private lateinit var binding: FirstLayoutBinding
    companion object {
        const val REQUEST_CODE = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FirstLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            val data = "Hello SecondActivity"
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("extra_data", data)
            Log.d("FirstActivity", "Sending data: $data")
            startActivityForResult(intent, REQUEST_CODE)
        }

        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show()
        }
        return true
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val returnData = data?.getStringExtra("data_return")
            Log.d("FirstActivity", "Received return data: $returnData") // Логування повернених даних
        }
    }
}

