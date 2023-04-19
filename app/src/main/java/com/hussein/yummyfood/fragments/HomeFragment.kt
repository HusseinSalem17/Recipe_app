package com.hussein.yummyfood.fragments

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.hussein.yummyfood.R
import com.hussein.yummyfood.activities.DetailsRecipeActivity
import com.hussein.yummyfood.adapters.ListRecipeTypeAdapter
import com.hussein.yummyfood.listeners.RecipeClickListener
import com.hussein.yummyfood.viewModel.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        lateinit var imageSlider: ImageSlider
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        imageSlider = v.findViewById(R.id.slider)
        val slideModels: ArrayList<SlideModel> = ArrayList()
        slideModels.add(SlideModel(R.drawable.meal_course, ScaleTypes.CENTER_CROP))
        slideModels.add(SlideModel(R.drawable.drink, ScaleTypes.CENTER_CROP))
        slideModels.add(SlideModel(R.drawable.snack, ScaleTypes.CENTER_CROP))
        slideModels.add(SlideModel(R.drawable.sauce, ScaleTypes.CENTER_CROP))
        slideModels.add(SlideModel(R.drawable.salad, ScaleTypes.CENTER_CROP))

        imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP)

        val rv: RecyclerView = v.findViewById(R.id.recycler_random_recipes)
        val viewModel = ViewModelProvider(requireActivity())[RecipesViewModel::class.java]

        if (isOnline(requireActivity().applicationContext)) {
            dataOnline(rv, viewModel)
        } else {
            Toast.makeText(this@HomeFragment.context, "Its a toast!", Toast.LENGTH_SHORT).show()
            dataLocal(rv, viewModel)
        }



        return v
    }

    private fun dataOnline(rv: RecyclerView, viewModel: RecipesViewModel) {
        Log.d("checkViewModel", viewModel.hashCode().toString())
        viewModel.getRandomRecipes(getString(R.string.apiKey), "5", "")
        lifecycleScope.launch {
            viewModel.randomRecipes.collect {
                val recipeAdapter = it?.let { it1 ->
                    viewModel.insertList(it1.recipes)
                    ListRecipeTypeAdapter(object : RecipeClickListener {
                        override fun onClickListener(id: Int) {
                            val intent =
                                Intent(this@HomeFragment.context, DetailsRecipeActivity::class.java)
                            intent.putExtra("id", id)
                            startActivity(intent)
                        }
                    }, it.recipes)
                }
                rv.setHasFixedSize(true)
                rv.adapter = recipeAdapter
            }
        }
    }

    private fun dataLocal(rv: RecyclerView, viewModel: RecipesViewModel) {
        lifecycleScope.launch {
            val adapter = ListRecipeTypeAdapter(object : RecipeClickListener {
                override fun onClickListener(id: Int) {
                    val intent =
                        Intent(this@HomeFragment.context, DetailsRecipeActivity::class.java)
                    intent.putExtra("id", id)
                    startActivity(intent)
                }
            }, viewModel.getAllRecipes())
            rv.setHasFixedSize(true)
            rv.adapter = adapter
        }

    }


    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                } else {
                    TODO("VERSION.SDK_INT < M")
                }
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}

