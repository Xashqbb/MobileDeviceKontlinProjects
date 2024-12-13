package com.example.travelmate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travelmate.databinding.ItemTourBinding
import com.example.travelmate.model.Tour
import com.example.travelmate.view.TourDiffCallback

class TourAdapter(
    private val onTourClickListener: OnTourClickListener
) : ListAdapter<Tour, TourAdapter.TourViewHolder>(TourDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourViewHolder {
        val binding = ItemTourBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TourViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TourViewHolder, position: Int) {
        val tour = getItem(position)
        holder.bind(tour)
    }

    inner class TourViewHolder(private val binding: ItemTourBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val tour = getItem(position)
                    onTourClickListener.onTourClick(tour)
                }
            }
        }

        fun bind(tour: Tour) {
            binding.tour = tour
            binding.executePendingBindings()
        }
    }

    interface OnTourClickListener {
        fun onTourClick(tour: Tour)
    }
}
