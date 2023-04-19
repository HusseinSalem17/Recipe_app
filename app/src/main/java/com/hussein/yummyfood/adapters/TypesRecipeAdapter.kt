package com.hussein.yummyfood.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.hussein.yummyfood.R
import com.hussein.yummyfood.listeners.CardClickListener
import com.hussein.yummyfood.model.RecipesTypes

class TypesRecipeAdapter(
    private val listener: CardClickListener,
    private val mealTypesList: ArrayList<RecipesTypes>,
) :
    RecyclerView.Adapter<TypesRecipeAdapter.TypeRecipeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeRecipeViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_meal_type, parent, false)
        return TypeRecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TypeRecipeViewHolder, position: Int) {
        val currentItem = mealTypesList[position]
        holder.imageRecipeType.setBackgroundResource(currentItem.imageType)
        holder.titleRecipeType.text = currentItem.title

        holder.cardType.setOnClickListener {
            listener.onClickListener(currentItem.title)
        }
    }

    override fun getItemCount(): Int {
        return mealTypesList.size
    }


    class TypeRecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardType: CardView = itemView.findViewById(R.id.card_meal_type)
        val imageRecipeType: ImageView = itemView.findViewById(R.id.imageView_meal_type)
        val titleRecipeType: TextView = itemView.findViewById(R.id.textView_meal_type)
    }
}