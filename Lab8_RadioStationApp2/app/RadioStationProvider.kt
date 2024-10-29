package com.example.lab8_radiostationapp2

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class RadioStationProvider : ContentProvider() {
    companion object {
        const val AUTHORITY = "com.example.radiostationapp.provider"
        val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/stations")
    }

    private lateinit var database: SQLiteDatabase

    override fun onCreate(): Boolean {
        val dbHelper = StationDatabaseHelper(context!!)
        database = dbHelper.writableDatabase
        return true
    }

    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        return database.query("stations", projection, selection, selectionArgs, null, null, sortOrder)
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val id = database.insert("stations", null, values)
        context?.contentResolver?.notifyChange(CONTENT_URI, null)
        return Uri.parse("$CONTENT_URI/$id")
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        return database.update("stations", values, selection, selectionArgs)
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return database.delete("stations", selection, selectionArgs)
    }

    override fun getType(uri: Uri): String? {
        return null
    }
}
