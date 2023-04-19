package com.hussein.domain.repo

import com.hussein.domain.entity.Recipe
import com.hussein.domain.entity.*

interface RecipesRepo {

    //For Remote
    suspend fun getRandomRecipesRemote(
        apiKey: String,
        number: String,
        tags: String,
    ): RandomRecipesResponse

    suspend fun getDetailsRecipeRemote(
        id: Int,
        apiKey: String,
    ): DetailsRecipeResponse

    suspend fun getInstructionRecipeRemote(
        id: Int,
        apiKey: String,
    ): AnalyzedInstructionsResponse

    suspend fun getSearchRecipes(
        query: String,
        apiKey: String,
    ): SearchRecipesResponse


    //for Local

    fun getAllRecipes(): List<Recipe?>

    suspend fun insertRecipe(recipe: Recipe?)

    suspend fun deleteRecipe(recipe: Recipe?)

    suspend fun deleteAllRecipes()

    suspend fun insertList(recipes: List<Recipe?>)
}