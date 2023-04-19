package com.hussein.domain.usecase

import com.hussein.domain.entity.Recipe
import com.hussein.domain.entity.SearchRecipesResponse
import com.hussein.domain.repo.RecipesRepo
import javax.inject.Inject

/*
why didn't call repo from viewModel directly?
    to protect the code from future changes, Screaming architecture
    read this article (https://proandroiddev.com/why-you-need-use-cases-interactors-142e8a6fe576)
 */
class GetRecipes @Inject constructor(private val recipesRepo: RecipesRepo) {
    suspend fun getRandomRecipes(apiKey: String, number: String, tags: String) =
        recipesRepo.getRandomRecipesRemote(apiKey, number, tags)

    suspend fun getDetailsRecipe(id: Int, apiKey: String) =
        recipesRepo.getDetailsRecipeRemote(id, apiKey)

    suspend fun getInstructionsRecipe(id: Int, apiKey: String) =
        recipesRepo.getInstructionRecipeRemote(id, apiKey)

    suspend fun getSearchRecipes(query: String, apiKey: String) =
        recipesRepo.getSearchRecipes(query, apiKey)

    fun getAllRecipes() = recipesRepo.getAllRecipes()

    suspend fun insertRecipe(recipe: Recipe?) {
        recipesRepo.insertRecipe(recipe)
    }

    suspend fun insertList(recipes: List<Recipe?>) {
        recipesRepo.insertList(recipes)
    }

    suspend fun deleteRecipe(recipe: Recipe?) {
        recipesRepo.deleteRecipe(recipe)
    }

    suspend fun deleteAllRecipes() {
        recipesRepo.deleteAllRecipes()
    }

}