import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travelmate.databinding.ItemTourBinding
import com.example.travelmate.model.Tour
import com.example.travelmate.view.TourDiffCallback

class TourAdapter : ListAdapter<Tour, TourAdapter.TourViewHolder>(TourDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourViewHolder {
        val binding = ItemTourBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TourViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TourViewHolder, position: Int) {
        val tour = getItem(position)
        Log.d("Adapter", "Binding tour: ${getItem(position)}")
        holder.bind(tour)
    }

    class TourViewHolder(private val binding: ItemTourBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tour: Tour) {
            binding.tour = tour
            binding.executePendingBindings()
        }
    }
}
