package com.example.laba_13

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.laba_13.databinding.ActivityAddEditNoteBinding

class AddEditNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditNoteBinding
    private val noteRepository: NoteRepository by lazy {
        NoteRepository(NoteDatabase.getDatabase(this).noteDao())
    }

    private val noteViewModel: NoteViewModel by viewModels {
        NoteViewModelFactory(noteRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val noteId = intent.getIntExtra("noteId", -1)
        if (noteId != -1) {
            // Якщо редагуємо існуючу нотатку
            noteViewModel.getNoteById(noteId).observe(this, { note ->
                binding.titleEditText.setText(note.title)
                binding.descriptionEditText.setText(note.description)
            })
        }

        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString().trim()
            val description = binding.descriptionEditText.text.toString().trim()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                val newNote = Note(
                    id = if (noteId == -1) 0 else noteId, // Для нової нотатки id буде 0
                    title = title,
                    description = description
                )
                if (noteId == -1) {
                    // Додаємо нову нотатку
                    noteViewModel.addNote(newNote)
                } else {
                    // Оновлюємо існуючу нотатку
                    noteViewModel.updateNote(newNote)
                }
                Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()
                finish() // Закриваємо Activity і повертаємось на головний екран
            } else {
                Toast.makeText(this, "Please enter both title and description", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
