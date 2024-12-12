package com.example.travelmate.view

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.travelmate.model.AppDatabase

class MyApplication : Application() {
    lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .build()
        Log.d("Database", "Database initialized: ${db.isOpen}")
    }
}
