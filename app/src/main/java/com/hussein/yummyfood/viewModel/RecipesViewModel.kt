package com.hussein.yummyfood.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.hussein.domain.entity.*
import com.hussein.domain.usecase.GetLikedRecipes
import com.hussein.domain.usecase.GetRecipes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

//get (getRecipesUseCase) from di(UseCaseModule)
@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipes,
    private val getLikedRecipes: GetLikedRecipes,
) :
    ViewModel() {
    private val _randomRecipes: MutableStateFlow<RandomRecipesResponse?> = MutableStateFlow(null)
    private val _detailsRecipe: MutableStateFlow<DetailsRecipeResponse?> = MutableStateFlow(null)
    private val _instructionsRecipe: MutableStateFlow<AnalyzedInstructionsResponse?> =
        MutableStateFlow(null)
    private val _searchRecipes: MutableStateFlow<SearchRecipesResponse?> = MutableStateFlow(null)
    val randomRecipes: StateFlow<RandomRecipesResponse?> = _randomRecipes
    val detailsRecipe: StateFlow<DetailsRecipeResponse?> = _detailsRecipe
    val instructionsRecipe: StateFlow<AnalyzedInstructionsResponse?> = _instructionsRecipe
    val searchRecipes: StateFlow<SearchRecipesResponse?> = _searchRecipes

    //to take the return from RandomRecipes
    fun getRandomRecipes(apiKey: String, number: String, tag: String) {
        viewModelScope.launch {
            try {
                _randomRecipes.value =
                    getRecipesUseCase.getRandomRecipes(apiKey, number, tag)
            } catch (e: Exception) {
                Log.e("RecipesViewModel", e.message.toString())
            }
        }
    }

    //to take the return from DetailsRecipe
    fun getDetailsRecipe(id: Int, apiKey: String) {
        viewModelScope.launch {
            try {
                _detailsRecipe.value = getRecipesUseCase.getDetailsRecipe(id, apiKey)
            } catch (e: Exception) {
                Log.e("RecipesViewModel", e.message.toString())
            }
        }
    }

    //to take the return from StepX
    fun getInstructionRecipe(id: Int, apiKey: String) {
        viewModelScope.launch {
            try {
                _instructionsRecipe.value = getRecipesUseCase.getInstructionsRecipe(id, apiKey)
            } catch (e: Exception) {
                Log.e("RecipesViewModel", e.message.toString())
            }
        }
    }

    //to get search result
    fun getSearchRecipes(query: String, apiKey: String) {
        viewModelScope.launch {
            try {
                _searchRecipes.value = getRecipesUseCase.getSearchRecipes(query, apiKey)
            } catch (e: Exception) {
                Log.e("RecipesViewModel", e.message.toString())
            }
        }
    }

    fun insertRecipe(recipe: Recipe?) {
        viewModelScope.launch {
            getRecipesUseCase.insertRecipe(recipe)
        }
    }

    fun insertList(recipes: List<Recipe?>) {
        viewModelScope.launch {
            getRecipesUseCase.insertList(recipes)
        }
    }

    fun getAllRecipes() = getRecipesUseCase.getAllRecipes()

    val likedRecipesList: LiveData<List<DetailsRecipeResponse?>> =
        getLikedRecipes.getAllLikedRecipes().asLiveData(viewModelScope.coroutineContext)

    fun insertLikedRecipe(recipe: DetailsRecipeResponse?) {
        viewModelScope.launch {
            getLikedRecipes.insertLikedRecipe(recipe)
        }
    }

    fun insertLikedRecipeList(recipe: List<DetailsRecipeResponse?>) {
        viewModelScope.launch {
            getLikedRecipes.insertLikedList(recipe)
        }
    }


    fun exists(id: Int): Boolean {
        return getLikedRecipes.exists(id)
    }


    fun deleteLikedRecipe(recipe: DetailsRecipeResponse?) {
        viewModelScope.launch {
            getLikedRecipes.deleteLikedRecipe(recipe)
        }
    }

    fun deleteAllRecipes() {
        viewModelScope.launch {
            getLikedRecipes.deleteAllRecipes()
        }
    }


}
