package com.example.mathnote.database

import android.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mathnote.recycler.NoteAdapter

@Entity(tableName = "notes")
data class Note(
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "color") var color: Int = Color.parseColor(NoteAdapter.COLOR_GRAY)
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}