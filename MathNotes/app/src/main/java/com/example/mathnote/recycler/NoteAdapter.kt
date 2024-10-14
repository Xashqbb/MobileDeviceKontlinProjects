package com.example.mathnote.recycler

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.mathnote.R
import com.example.mathnote.database.Note
import com.example.mathnote.databinding.NoteItemBinding

class NoteAdapter(
    private val clickListener: RecyclerClickListener
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var currentColorId: Int = 0
    private lateinit var binding: NoteItemBinding
    private var list = mutableListOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    fun setItems(newList: List<Note>) {
        list.apply {
            clear()
            addAll(newList)
            notifyDataSetChanged()
        }
    }

    inner class NoteViewHolder(
        private val binding: NoteItemBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Note) = with(binding) {
            tvNoteTitle.text = model.title
            tvDescription.text = model.description

            if (list[adapterPosition].color != 0) {
                root.setCardBackgroundColor(list[adapterPosition].color)
            }

            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    clickListener.onClicked(list[adapterPosition])
                }
            }

            ivEdit.setOnClickListener {
                PopupMenu(context, ivEdit).apply {
                    inflate(R.menu.popup_menu)

                    setOnMenuItemClickListener {
                        when (it.itemId) {
                            R.id.iDelete -> clickListener.onDeleteClicked(list[adapterPosition])

                            R.id.iRed -> {
                                currentColorId = Color.parseColor(COLOR_RED)
                                clickListener.updateColor(list[adapterPosition].id, currentColorId)
                            }

                            R.id.iYellow -> {
                                currentColorId = Color.parseColor(COLOR_YELLOW)
                                clickListener.updateColor(list[adapterPosition].id, currentColorId)
                            }
                        }
                        true
                    }
                    show()
                }
            }
        }
    }

    companion object {
        const val COLOR_RED = "#E65757"
        const val COLOR_YELLOW = "#DAE45B"
        const val COLOR_GRAY = "#AFC5DC"
    }
}
