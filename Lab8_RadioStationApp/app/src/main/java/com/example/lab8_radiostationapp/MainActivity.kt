package com.example.lab8_radiostationapp

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var editTextUrl: EditText
    private lateinit var buttonAdd: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var stationDatabaseHelper: StationDatabaseHelper
    private lateinit var adapter: StationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextUrl = findViewById(R.id.editTextUrl)
        buttonAdd = findViewById(R.id.buttonAdd)
        recyclerView = findViewById(R.id.recyclerView)

        stationDatabaseHelper = StationDatabaseHelper(this)
        adapter = StationAdapter(stationDatabaseHelper.getAllStations())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        buttonAdd.setOnClickListener {
            addStation()
        }
    }

    private fun addStation() {
        val url = editTextUrl.text.toString()
        if (url.isNotEmpty()) {
            val values = ContentValues().apply {
                put("url", url)
            }
            stationDatabaseHelper.insertStation(values)
            adapter.updateData(stationDatabaseHelper.getAllStations())
            editTextUrl.text.clear()
        } else {
            Toast.makeText(this, "Enter a URL", Toast.LENGTH_SHORT).show()
        }
    }
}
