package com.example.mathnote.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mathnote.database.Note
import com.example.mathnote.databinding.FragmentAddNoteBinding
import com.example.mathnote.viewModel.NoteViewModel

class CreateNoteFragment : Fragment() {

    private val viewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[NoteViewModel::class.java]
    }
    private lateinit var binding: FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentAddNoteBinding.inflate(layoutInflater)

        binding.btAdd.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()
            val updateNote = Note(title, description)

            viewModel.addNote(updateNote)
           requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.btCancel.setOnClickListener{
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        return binding.root
    }

}