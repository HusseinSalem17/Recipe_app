package com.hussein.data.repo

import com.hussein.data.remote.ApiService
import com.hussein.domain.dao.RecipeDao
import com.hussein.domain.entity.*
import com.hussein.domain.repo.RecipesRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipesRepoImpl @Inject constructor(
    private val apiService: ApiService,
    private val recipeDao: RecipeDao,
) :
    RecipesRepo {
    override suspend fun getRandomRecipesRemote(
        apiKey: String,
        number: String,
        tags: String,
    ): RandomRecipesResponse {
        return apiService.getRandomRecipes(apiKey, number, tags)
    }

    override suspend fun getDetailsRecipeRemote(id: Int, apiKey: String): DetailsRecipeResponse {
        return apiService.getDetailsRecipe(id, apiKey)
    }

    override suspend fun getInstructionRecipeRemote(
        id: Int,
        apiKey: String,
    ): AnalyzedInstructionsResponse {
        return apiService.getInstructionsRecipe(id, apiKey)
    }

    override suspend fun getSearchRecipes(query: String, apiKey: String): SearchRecipesResponse {
        return apiService.getSearchRecipes(query, apiKey)
    }

    override fun getAllRecipes(): List<Recipe?> {
        return recipeDao.getAllRecipes()
    }

    override suspend fun insertRecipe(recipe: Recipe?) {
        recipeDao.insertRecipe(recipe)
    }

    override suspend fun deleteRecipe(recipe: Recipe?) {
        recipeDao.deleteRecipe(recipe)
    }

    override suspend fun deleteAllRecipes() {
        recipeDao.deleteAllRecipes()
    }

    override suspend fun insertList(recipes: List<Recipe?>) {
        recipeDao.insertList(recipes)
    }


}