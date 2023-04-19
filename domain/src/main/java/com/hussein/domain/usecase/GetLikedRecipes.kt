package com.hussein.domain.usecase

import com.hussein.domain.entity.DetailsRecipeResponse
import com.hussein.domain.repo.LikedRecipesRepo
import javax.inject.Inject

class GetLikedRecipes @Inject constructor(private val likedRecipes: LikedRecipesRepo) {

    fun getAllLikedRecipes() = likedRecipes.getAllLikedRecipes()

    fun exists(id: Int): Boolean {
        return likedRecipes.exists(id)
    }


    suspend fun insertLikedRecipe(recipe: DetailsRecipeResponse?) {
        likedRecipes.insertLikedRecipe(recipe)
    }


    suspend fun deleteLikedRecipe(recipe: DetailsRecipeResponse?) {
        likedRecipes.deleteLikedRecipe(recipe)
    }

    suspend fun deleteAllRecipes() {
        likedRecipes.deleteAllLikedRecipes()
    }

    suspend fun insertLikedList(recipes: List<DetailsRecipeResponse?>) {
        likedRecipes.insertLikedList(recipes)
    }

}