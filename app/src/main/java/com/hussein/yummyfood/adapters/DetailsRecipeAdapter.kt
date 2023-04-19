package com.hussein.yummyfood.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hussein.domain.entity.ExtendedIngredientX
import com.hussein.yummyfood.R
import com.squareup.picasso.Picasso

class DetailsRecipeAdapter(
    private val mealDetailsList: List<ExtendedIngredientX>,
) :
    RecyclerView.Adapter<DetailsRecipeAdapter.DetailsRecipeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsRecipeViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_recipe_ingredients, parent, false)
        return DetailsRecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DetailsRecipeViewHolder, position: Int) {
        val currentItem = mealDetailsList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return mealDetailsList.size
    }


    class DetailsRecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ingredientsSteps: TextView =
            itemView.findViewById(R.id.ingredientsSteps)

        fun bind(ingredientX: ExtendedIngredientX) {
            ingredientsSteps.text = ingredientX.original
        }
    }
}