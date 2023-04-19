package com.hussein.yummyfood.activities

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.hussein.domain.entity.Recipe
import com.hussein.yummyfood.R
import com.hussein.yummyfood.adapters.ListRecipeTypeAdapter
import com.hussein.yummyfood.listeners.RecipeClickListener
import com.hussein.yummyfood.viewModel.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListItemsActivity : AppCompatActivity() {

    private val viewModel: RecipesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_items_type)

        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        val rv: RecyclerView = findViewById(R.id.recycler_random)


        val tag = intent.getStringExtra("tag").toString()
        val textView_recipe_type: TextView = findViewById(R.id.textView_recipe_type)
        val back_btn: ImageButton = findViewById(R.id.back_btn)
        textView_recipe_type.text = tag
        Log.d("Hussein", tag)
        back_btn.setOnClickListener {
            onBackPressed()
        }
        viewModel.getRandomRecipes(getString(R.string.apiKey), "5", tag)
        val job = lifecycleScope.launch {
            viewModel.randomRecipes.collect {
                val recipeAdapter = it?.let { it1 ->
                    ListRecipeTypeAdapter(object : RecipeClickListener {
                        override fun onClickListener(id: Int) {
                            val intent =
                                Intent(this@ListItemsActivity, DetailsRecipeActivity::class.java)
                            intent.putExtra("id", id)
                            startActivity(intent)
                        }
                    }, it1.recipes)
                }
                rv.adapter = recipeAdapter
            }
        }

    }
}