import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class DishAdapter(
    private val dishes: List<Dish>,
    private val onItemClick: (Dish) -> Unit
) : RecyclerView.Adapter<DishAdapter.DishViewHolder>() {
    init {
        Log.d("DishAdapter", "Adapter initialized with ${dishes.size} items.")
    }
    inner class DishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvDishName)
        val description: TextView = itemView.findViewById(R.id.tvDishDescription)

        fun bind(dish: Dish) {
            name.text = dish.name
            description.text = dish.description
            itemView.setOnClickListener { onItemClick(dish) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dish, parent, false)
        return DishViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        Log.d("DishAdapter", "Binding item at position $position: ${dishes[position].name}")
        holder.bind(dishes[position])
    }



    override fun getItemCount(): Int = dishes.size
}
