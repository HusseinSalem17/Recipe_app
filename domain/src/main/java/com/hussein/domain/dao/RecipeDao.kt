package com.hussein.domain.dao
import com.hussein.domain.entity.Recipe
import androidx.room.*

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipes")
    fun getAllRecipes(): List<Recipe?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe?)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe?)

    @Query("DELETE FROM recipes")
    suspend fun deleteAllRecipes()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(recipes: List<Recipe?>)
}