package com.example.mathnote.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mathnote.database.Note
import com.example.mathnote.databinding.FragmentNoteBinding
import com.example.mathnote.recycler.NoteAdapter
import com.example.mathnote.recycler.RecyclerClickListener
import com.example.mathnote.viewModel.NoteViewModel

class NoteFragment : Fragment(), RecyclerClickListener {

    private lateinit var binding: FragmentNoteBinding
    private lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)

        binding = FragmentNoteBinding.inflate(layoutInflater)

        binding.rvList.layoutManager = LinearLayoutManager(requireContext())
        val noteAdapter = NoteAdapter(this)
        binding.rvList.adapter = noteAdapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[NoteViewModel::class.java]

        viewModel.allNotes.observe(requireActivity()) { list ->
            list?.let {
                noteAdapter.setItems(it)
            }
        }

        binding.ivAdd.setOnClickListener {
            findNavController().navigate(NoteFragmentDirections.actionNoteFragmentToCreateNoteFragment())
        }

        return binding.root
    }

    override fun onClicked(note: Note) {
        findNavController().navigate(
            NoteFragmentDirections.actionNoteFragmentToInfoNoteFragment(
                title = note.title,
                description = note.description
            )
        )
    }

    override fun onDeleteClicked(note: Note) {
        viewModel.deleteNote(note)
    }

    override fun updateColor(noteId: Int, color: Int) {
        viewModel.updateColor(noteId, color)
    }

    override fun updateNote(note: Note) {
        // Якщо метод не потрібен, його можна залишити порожнім
    }
}
