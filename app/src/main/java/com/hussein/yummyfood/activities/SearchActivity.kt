package com.hussein.yummyfood.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.hussein.domain.entity.Result
import com.hussein.yummyfood.R
import com.hussein.yummyfood.adapters.SearchRecipeAdapter
import com.hussein.yummyfood.databinding.ActivitySearchBinding
import com.hussein.yummyfood.listeners.RecipeClickListener
import com.hussein.yummyfood.viewModel.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class SearchActivity : AppCompatActivity(), RecipeClickListener {

    private lateinit var activitySearchBinding: ActivitySearchBinding
    private lateinit var searchAdapter: SearchRecipeAdapter
    private var resultsList: ArrayList<Result> = ArrayList()
    private var timer: Timer? = null
    private var currentPage = 1
    private var totalAvailablePages = 1

    private val viewModel: RecipesViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySearchBinding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        customView()
        doInitialization()
    }

    private fun customView() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    private fun doInitialization() {
        activitySearchBinding.imageBack.setOnClickListener { onBackPressed() }
        activitySearchBinding.RecipesRecyclerView.setHasFixedSize(true)
        searchAdapter = SearchRecipeAdapter(resultsList, this)
        activitySearchBinding.RecipesRecyclerView.adapter = searchAdapter
        activitySearchBinding.inputSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (timer != null) {
                    timer?.cancel()
                }
            }
            @SuppressLint("NotifyDataSetChanged")
            override fun afterTextChanged(editable: Editable) {
                if (editable.toString().trim { it <= ' ' }.isNotEmpty()) {
                    timer = Timer()
                    timer?.schedule(object : TimerTask() {
                        override fun run() {
                            Handler(Looper.getMainLooper()).post {
                                currentPage = 1
                                totalAvailablePages = 1
                                searchRecipe(editable.toString())
                            }
                        }
                    }, 800)
                } else {
                    resultsList.clear()
                    searchAdapter.notifyDataSetChanged()
                }
            }
        })
    }

    fun searchRecipe(query: String) {
        toggleLoading()
        viewModel.getSearchRecipes(query, getString(R.string.apiKey))
        lifecycleScope.launch {
            viewModel.searchRecipes.collect {
                toggleLoading()
                if (it != null) {
                    totalAvailablePages = it.totalResults
                    if (it.results.isNotEmpty()) {
                        val oldCount = resultsList.size
                        resultsList.addAll(it.results)
                        searchAdapter.notifyItemRangeInserted(oldCount, it.totalResults)
                    }
                }
            }
        }

    }

    private fun toggleLoading() {
        if (currentPage == 1) {
            activitySearchBinding.isLoading =
                !(activitySearchBinding.isLoading != null && activitySearchBinding.isLoading!!)
        } else {
            activitySearchBinding.isLoadingMore =
                !(activitySearchBinding.isLoadingMore != null && activitySearchBinding.isLoadingMore!!)
        }
    }

    override fun onClickListener(id: Int) {
        val intent =
            Intent(this, DetailsRecipeActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }


}