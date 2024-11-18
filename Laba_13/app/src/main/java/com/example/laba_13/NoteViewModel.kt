package com.example.laba_13

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    fun getAllNotes(): LiveData<List<Note>> = repository.getAllNotes()

    fun addNote(note: Note) {
        viewModelScope.launch {
            repository.addNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            repository.updateNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }

    fun searchNotes(query: String): LiveData<List<Note>> {
        return repository.searchNotes(query)
    }

    fun getNoteById(noteId: Int): LiveData<Note> {
        return repository.getNoteById(noteId)
    }
}



