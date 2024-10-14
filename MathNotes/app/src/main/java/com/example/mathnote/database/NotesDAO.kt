package com.example.mathnote.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mathnote.database.Note

@Dao
interface NotesDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("UPDATE notes SET color = :color WHERE id = :noteId")
    suspend fun update(noteId: Int, color: Int)

    @Query("SELECT * FROM notes ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Note>>

    @Update
    suspend fun updateNote(note: Note)
}