package com.hussein.yummyfood.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hussein.yummyfood.R
import com.hussein.yummyfood.activities.ListItemsActivity
import com.hussein.yummyfood.adapters.TypesRecipeAdapter
import com.hussein.yummyfood.listeners.CardClickListener
import com.hussein.yummyfood.model.RecipesTypes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        lateinit var typeRecyclerView: RecyclerView
        val v = inflater.inflate(R.layout.fragment_recipes, container, false)
        var imageId: Array<Int> = arrayOf(
            R.drawable.meal_course,
            R.drawable.side_dish,
            R.drawable.dessert,
            R.drawable.appetizer,
            R.drawable.salad,
            R.drawable.bread,
            R.drawable.breakfast,
            R.drawable.soup,
            R.drawable.beverage,
            R.drawable.sauce,
            R.drawable.marinade,
            R.drawable.finger_food,
            R.drawable.snack,
            R.drawable.drink,
        )
        val heading: Array<String> = arrayOf(
            "main course",
            "side dish",
            "dessert",
            "appetizer",
            "salad",
            "bread",
            "breakfast",
            "soup",
            "beverage",
            "sauce",
            "marinade",
            "fingerfood",
            "snack",
            "drink",
        )
        typeRecyclerView = v.findViewById(R.id.recycler_meal_type)
        typeRecyclerView.layoutManager = LinearLayoutManager(this@RecipesFragment.context)
        typeRecyclerView.setHasFixedSize(true)

        val typeArrayList: ArrayList<RecipesTypes> = arrayListOf<RecipesTypes>()
        for (i in imageId.indices) {
            val type = RecipesTypes(imageId[i], heading[i])
            typeArrayList.add(type)
        }
        val listener = object : CardClickListener {
            override fun onClickListener(tag: String) {
                val intent = Intent(this@RecipesFragment.context, ListItemsActivity::class.java)
                intent.putExtra("tag", tag)
                startActivity(intent)
            }

        }
        typeRecyclerView.adapter = TypesRecipeAdapter(listener, typeArrayList)

        return v
    }


}