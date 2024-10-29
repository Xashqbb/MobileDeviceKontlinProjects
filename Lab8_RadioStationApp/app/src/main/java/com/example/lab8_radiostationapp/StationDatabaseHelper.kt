package com.example.lab8_radiostationapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class StationDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTableSQL = "CREATE TABLE stations (id INTEGER PRIMARY KEY AUTOINCREMENT, url TEXT)"
        db.execSQL(createTableSQL)

        val defaultStations = listOf(
            "http://streaming.radio.co/s3c4d6e8b3/listen",
            "https://example.com/stream"
        )

        for (url in defaultStations) {
            val values = ContentValues().apply {
                put("url", url)
            }
            db.insert("stations", null, values)
        }
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS stations")
        onCreate(db)
    }

    fun insertStation(values: ContentValues) {
        writableDatabase.insert("stations", null, values)
    }

    fun getAllStations(): List<String> {
        val stations = mutableListOf<String>()
        val cursor = readableDatabase.rawQuery("SELECT * FROM stations", null)
        if (cursor.moveToFirst()) {
            do {
                stations.add(cursor.getString(cursor.getColumnIndexOrThrow("url")))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return stations
    }

    companion object {
        private const val DATABASE_NAME = "RadioStationDB"
        private const val DATABASE_VERSION = 1
    }
}
