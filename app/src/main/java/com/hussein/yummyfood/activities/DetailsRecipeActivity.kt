package com.hussein.yummyfood.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.hussein.yummyfood.R
import com.hussein.yummyfood.adapters.DetailsRecipeAdapter
import com.hussein.yummyfood.adapters.InstructionsRecipeAdapter
import com.hussein.yummyfood.viewModel.RecipesViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_details_recipe.*
import kotlinx.coroutines.launch
import android.view.WindowManager

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.hussein.domain.entity.DetailsRecipeResponse
import com.hussein.yummyfood.databinding.ActivityDetailsRecipeBinding
import java.lang.Exception

@AndroidEntryPoint
class DetailsRecipeActivity : AppCompatActivity() {

    private val viewModel: RecipesViewModel by viewModels()
    private lateinit var binding: ActivityDetailsRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityDetailsRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.apply {
            clearFlags(WindowManager.LayoutParams.TYPE_STATUS_BAR)
            addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

        backBtn.setOnClickListener {
            onBackPressed()
        }
        val id = intent.getIntExtra("id", 0)
        Log.e("DetailsRecipeActivity", id.toString())
        onClick(viewModel.exists(id))

        viewModel.getDetailsRecipe(id, getString(R.string.apiKey))
        viewModel.getInstructionRecipe(id, getString(R.string.apiKey))

        val job = lifecycleScope.launch {
            viewModel.detailsRecipe.collect {
                recipeName.text = it?.title
                dishTypeTxt.text = it?.sourceName
                recipeTime.text = "${it?.readyInMinutes} Minutes"
                Log.d("DetailRecipeActivity", it?.title.toString())
                recipeCal.text = "${it?.healthScore} Calories"
                recipeServing.text = "${it?.servings} People"
                Picasso.get().load(it?.image).into(imageRecipe)
                val detailsAdapter =
                    it?.extendedIngredients?.let { it1 -> DetailsRecipeAdapter(it1) }
                recyclerIngredients.layoutManager = LinearLayoutManager(this@DetailsRecipeActivity)
                recyclerIngredients.setHasFixedSize(true)
                recyclerIngredients.adapter = detailsAdapter


            }
        }
        lifecycleScope.launch {
            viewModel.instructionsRecipe.collect {
                val instructionsAdapter =
                    it?.get(0)?.steps?.let { it1 -> InstructionsRecipeAdapter(it1) }
                recyclerInstructions.layoutManager =
                    LinearLayoutManager(this@DetailsRecipeActivity)
                recyclerInstructions.setHasFixedSize(true)
                recyclerInstructions.adapter = instructionsAdapter
            }
        }

    }

    private fun onClick(like: Boolean) {
        Log.d("recipe", like.toString())
        if (like)
            binding.LikeBtn.setImageResource(R.drawable.ic_liked)
        else
            binding.LikeBtn.setImageResource(R.drawable.ic_like)

        binding.LikeBtn.setOnClickListener {
            if (like) {
                viewModel.deleteLikedRecipe(viewModel.detailsRecipe.value)
                binding.LikeBtn.setImageResource(R.drawable.ic_like)
            } else {
                viewModel.insertLikedRecipe(viewModel.detailsRecipe.value)
                binding.LikeBtn.setImageResource(R.drawable.ic_liked)
            }

        }

    }


}