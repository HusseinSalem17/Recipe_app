package com.hussein.yummyfood.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hussein.domain.entity.Result
import com.hussein.yummyfood.R
import com.hussein.yummyfood.databinding.ListRecipeSearchBinding
import com.hussein.yummyfood.listeners.RecipeClickListener
import com.squareup.picasso.Picasso

class SearchRecipeAdapter(
    private var recipe: List<Result>,
    private var listener: RecipeClickListener,
) :
    RecyclerView.Adapter<SearchRecipeAdapter.SearchRecipeViewHolder>() {
    private var layoutInflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRecipeViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val tvShowBinding: ListRecipeSearchBinding = DataBindingUtil.inflate(
            layoutInflater!!, R.layout.list_recipe_search, parent, false
        )
        return SearchRecipeViewHolder(tvShowBinding)
    }

    override fun onBindViewHolder(holder: SearchRecipeViewHolder, position: Int) {
        holder.bindRecipe(recipe[position])
    }

    override fun getItemCount(): Int {
        return recipe.size
    }

    inner class SearchRecipeViewHolder(private val listRecipeSearchBinding: ListRecipeSearchBinding) :
        RecyclerView.ViewHolder(listRecipeSearchBinding.root) {

        //to set the value of the tvShow (Object) and send his values to the views
        fun bindRecipe(recipe: Result) {
            listRecipeSearchBinding.titleTextView.text = recipe.title
            Picasso.get().load(recipe.image).into(listRecipeSearchBinding.imageView)
            listRecipeSearchBinding.cardListContainer.setOnClickListener {
                listener.onClickListener(recipe.id)
            }
        }

    }

}