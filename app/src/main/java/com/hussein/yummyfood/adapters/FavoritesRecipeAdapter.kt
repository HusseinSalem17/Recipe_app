package com.hussein.yummyfood.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.hussein.domain.entity.DetailsRecipeResponse
import com.hussein.domain.entity.ExtendedIngredientX
import com.hussein.domain.entity.Recipe
import com.hussein.yummyfood.R
import com.hussein.yummyfood.listeners.RecipeClickListener
import com.squareup.picasso.Picasso

class FavoritesRecipeAdapter(
    private val listener: RecipeClickListener,
    private val recipeList: List<DetailsRecipeResponse?>,
) :
    RecyclerView.Adapter<FavoritesRecipeAdapter.FavoritesRecipeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesRecipeViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_type_recipe, parent, false)
        return FavoritesRecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FavoritesRecipeViewHolder, position: Int) {
        val currentItem = recipeList[position]
        holder.textViewRecipeName.text = currentItem?.title
        holder.textViewTime.text = "${currentItem?.readyInMinutes} Minutes"
        holder.textViewCal.text = "${currentItem?.healthScore} Calories"
        holder.textViewServings.text = "${currentItem?.servings} People"
        Picasso.get().load(currentItem?.image).into(holder.imageViewFood)

        holder.cardListContainer.setOnClickListener {
            currentItem?.id?.let { it1 -> listener.onClickListener(it1) }
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    class FavoritesRecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardListContainer: CardView = itemView.findViewById(R.id.random_list_container)
        val textViewRecipeName: TextView = itemView.findViewById(R.id.textView_recipe_name)
        val textViewTime: TextView = itemView.findViewById(R.id.time_recipe)
        val textViewCal: TextView = itemView.findViewById(R.id.cal_recipe)
        val textViewServings: TextView = itemView.findViewById(R.id.serving_recipe)
        val imageViewFood: ImageView = itemView.findViewById(R.id.imageView_food)
    }
}