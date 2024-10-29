package com.example.lab8_radiostationapp2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class StationDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE stations (id INTEGER PRIMARY KEY AUTOINCREMENT, url TEXT)"
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS stations")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "RadioStationDB"
        private const val DATABASE_VERSION = 1
    }
}
