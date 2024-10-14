package com.example.mathnote.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.mathnote.databinding.FragmentNoteInfoBinding

class InfoNoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteInfoBinding
    private val args by navArgs<InfoNoteFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = FragmentNoteInfoBinding.inflate(layoutInflater)


        binding.tvTitle.setText(args.title)
        binding.tvDescription.setText(args.description)

        binding.btBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        return binding.root
    }

    companion object {
        const val TITLE_KEY = "TITLE_KEY"
        const val DESCRIPTION_KEY = "DESCRIPTION_KEY"
        const val COLOR_KEY = "COLOR_KEY"
    }
}