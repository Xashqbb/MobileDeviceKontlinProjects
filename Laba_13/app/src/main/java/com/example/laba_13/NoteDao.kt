package com.example.laba_13

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM notes WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    fun searchNotes(query: String): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE id = :noteId LIMIT 1")
    fun getNoteById(noteId: Int): LiveData<Note>

    @Query("SELECT * FROM notes")
    fun getAllNotes(): LiveData<List<Note>>
}

