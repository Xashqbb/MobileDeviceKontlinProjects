package com.example.travelmate

import android.app.Application
import androidx.room.Room
import com.example.travelmate.model.AppDatabase

class MyApplication : Application() {
    lateinit var db: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
