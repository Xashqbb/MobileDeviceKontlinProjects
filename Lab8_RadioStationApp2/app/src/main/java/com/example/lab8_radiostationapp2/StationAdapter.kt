package com.example.lab8_radiostationapp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab8_radiostationapp.R

class StationAdapter(private val stations: List<String>) : RecyclerView.Adapter<StationAdapter.StationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_station, parent, false)
        return StationViewHolder(view)
    }

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        holder.bind(stations[position])
    }

    override fun getItemCount(): Int = stations.size

    class StationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val stationUrlTextView: TextView = itemView.findViewById(R.id.stationUrlTextView)

        fun bind(stationUrl: String) {
            stationUrlTextView.text = stationUrl
        }
    }
}
