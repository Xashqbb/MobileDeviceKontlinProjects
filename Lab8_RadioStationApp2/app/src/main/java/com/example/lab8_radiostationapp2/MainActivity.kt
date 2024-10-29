package com.example.lab8_radiostationapp

import android.database.Cursor
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab8_radiostationapp2.RadioStationProvider
import com.example.lab8_radiostationapp2.StationAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadStations()
    }

    private fun loadStations() {
        val cursor: Cursor? = contentResolver.query(RadioStationProvider.CONTENT_URI, null, null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            val stations = mutableListOf<String>()
            do {
                val urlIndex = cursor.getColumnIndexOrThrow("url")
                stations.add(cursor.getString(urlIndex))
            } while (cursor.moveToNext())
            adapter = StationAdapter(stations)
            recyclerView.adapter = adapter
        } else {
            Toast.makeText(this, "No stations found", Toast.LENGTH_SHORT).show()
        }
        cursor?.close()
    }
}
