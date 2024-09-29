package com.example.myapplication
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import Dish
import DishAdapter
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyMessageTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewDishes)
        emptyMessageTextView = findViewById(R.id.tvEmptyMessage)

        recyclerView.layoutManager = LinearLayoutManager(this)

        //val dishes = listOf<Dish>()
        val dishes = listOf(
            Dish("Pizza", "Delicious cheese pizza", 12.99, listOf("Cheese", "Tomato", "Dough")),
            Dish("Burger", "Juicy beef burger", 10.99, listOf("Beef", "Lettuce", "Bun")),
            Dish("Pasta", "Creamy Alfredo pasta", 11.99, listOf("Pasta", "Cream", "Parmesan")),
            Dish("Sushi", "Fresh salmon sushi", 14.99, listOf("Salmon", "Rice", "Seaweed")),
            Dish("Salad", "Healthy garden salad", 9.99, listOf("Lettuce", "Tomato", "Cucumber"))
        )

        recyclerView.adapter = DishAdapter(dishes) { dish ->
            val intent = Intent(this, DishDetailActivity::class.java)
            intent.putExtra("dish", dish)
            startActivity(intent)
        }

        if (dishes.isEmpty()) {
            recyclerView.visibility = View.GONE
            emptyMessageTextView.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            emptyMessageTextView.visibility = View.GONE
        }
    }
}
