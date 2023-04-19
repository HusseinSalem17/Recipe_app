package com.hussein.domain.repo

import com.hussein.domain.entity.DetailsRecipeResponse
import kotlinx.coroutines.flow.Flow

interface LikedRecipesRepo {

    fun getAllLikedRecipes(): Flow<List<DetailsRecipeResponse?>>

    fun exists(id: Int): Boolean

    suspend fun insertLikedRecipe(recipe: DetailsRecipeResponse?)

    suspend fun insertLikedList(recipes: List<DetailsRecipeResponse?>)

    suspend fun deleteLikedRecipe(recipe: DetailsRecipeResponse?)

    suspend fun deleteAllLikedRecipes()

}