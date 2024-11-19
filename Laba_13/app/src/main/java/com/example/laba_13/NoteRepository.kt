package com.example.laba_13

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {

    fun getAllNotes(): LiveData<List<Note>> {
        return noteDao.getAllNotes()
    }

    suspend fun addNote(note: Note) {
        noteDao.insert(note)
    }

    suspend fun updateNote(note: Note) {
        noteDao.update(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.delete(note)
    }

    fun searchNotes(query: String): LiveData<List<Note>> {
        return noteDao.searchNotes(query)
    }

    fun getNoteById(noteId: Int): LiveData<Note> {
        return noteDao.getNoteById(noteId)
    }
}


