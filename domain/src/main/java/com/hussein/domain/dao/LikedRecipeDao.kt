package com.hussein.domain.dao

import androidx.room.*
import com.hussein.domain.entity.DetailsRecipeResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface LikedRecipeDao {

    @Query("SELECT * FROM liked_recipes")
    fun getAllLikedRecipes(): Flow<List<DetailsRecipeResponse?>>

    @Query("SELECT EXISTS(SELECT 1 FROM liked_recipes WHERE id = :id)")
    fun exists(id: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLikedRecipe(recipe: DetailsRecipeResponse?)

    @Delete
    suspend fun deleteLikedRecipe(recipe: DetailsRecipeResponse?)

    @Query("DELETE FROM liked_recipes")
    suspend fun deleteAllLikedRecipes()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLikedList(recipes: List<DetailsRecipeResponse?>)
}