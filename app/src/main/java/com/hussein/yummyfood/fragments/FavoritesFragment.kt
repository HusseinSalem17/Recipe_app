package com.hussein.yummyfood.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.hussein.yummyfood.R
import com.hussein.yummyfood.activities.DetailsRecipeActivity
import com.hussein.yummyfood.adapters.FavoritesRecipeAdapter
import com.hussein.yummyfood.adapters.ListRecipeTypeAdapter
import com.hussein.yummyfood.listeners.RecipeClickListener
import com.hussein.yummyfood.viewModel.RecipesViewModel
import kotlinx.coroutines.launch


class FavoritesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_favorites, container, false)

        val rv: RecyclerView = v.findViewById(R.id.recycler_fav)
        val viewModel = ViewModelProvider(requireActivity())[RecipesViewModel::class.java]

        dataLocal(rv, viewModel)



        return v
    }

    private fun dataLocal(rv: RecyclerView, viewModel: RecipesViewModel) {
        lifecycleScope.launch {
            viewModel.likedRecipesList.observe(viewLifecycleOwner) {
                val adapter = FavoritesRecipeAdapter(object : RecipeClickListener {
                    override fun onClickListener(id: Int) {
                        val intent =
                            Intent(this@FavoritesFragment.context,
                                DetailsRecipeActivity::class.java)
                        intent.putExtra("id", id)
                        startActivity(intent)
                    }
                }, it)
                rv.setHasFixedSize(true)
                rv.adapter = adapter
            }
        }

    }


}