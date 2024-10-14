package com.example.mathnote.recycler

import com.example.mathnote.database.Note

interface RecyclerClickListener {

    fun onClicked(note: Note)

    fun onDeleteClicked(note: Note)

    fun updateColor(noteId: Int, color: Int)

    fun updateNote(note: Note)
}