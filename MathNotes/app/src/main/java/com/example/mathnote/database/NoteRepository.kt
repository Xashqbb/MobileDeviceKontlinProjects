package com.example.mathnote.database

import androidx.lifecycle.LiveData

class NoteRepository(private val notesDao: NotesDAO) {
    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }

    suspend fun update(noteId: Int, color: Int) {
        notesDao.update(noteId, color)
    }

    suspend fun delete(note: Note) {
        notesDao.delete(note)
    }

    suspend fun update(note: Note) {
        notesDao.updateNote(note)
    }
}
