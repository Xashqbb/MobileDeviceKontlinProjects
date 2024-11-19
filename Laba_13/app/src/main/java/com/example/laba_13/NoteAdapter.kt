package com.example.laba_13

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.laba_13.databinding.ItemNoteBinding

class NoteAdapter(
    private val notes: List<Note>,
    private val deleteNote: (Note) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)
    }

    override fun getItemCount(): Int = notes.size

    inner class NoteViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.titleTextView.text = note.title
            binding.descriptionTextView.text = note.description

            // Обробка натискання кнопки видалення
            binding.deleteButton.setOnClickListener {
                deleteNote(note)
            }

            // Перехід до редагування нотатки
            binding.root.setOnClickListener {
                val context = it.context
                val intent = Intent(context, AddEditNoteActivity::class.java)
                intent.putExtra("noteId", note.id)
                context.startActivity(intent)
            }
        }
    }
}

