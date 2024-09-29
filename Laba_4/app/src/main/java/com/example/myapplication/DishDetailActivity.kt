package com.example.myapplication
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import Dish

class DishDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish_detail)

        val dish = intent.getParcelableExtra<Dish>("dish")

        if (dish != null) {
            findViewById<TextView>(R.id.tvDishNameDetail).text = dish.name
            findViewById<TextView>(R.id.tvDishDescriptionDetail).text = dish.description
            findViewById<TextView>(R.id.tvDishPrice).text = "Price: $${dish.price}"
            findViewById<TextView>(R.id.tvDishIngredients).text = "Ingredients: ${dish.ingredients.joinToString()}"
        }
    }
}
