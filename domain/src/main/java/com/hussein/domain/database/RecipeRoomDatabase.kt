package com.hussein.domain.database

import com.hussein.domain.entity.Recipe
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hussein.domain.converters.Converters
import com.hussein.domain.dao.LikedRecipeDao
import com.hussein.domain.dao.RecipeDao
import com.hussein.domain.entity.DetailsRecipeResponse


@Database(entities = [Recipe::class, DetailsRecipeResponse::class], version = 7, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RecipeRoomDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun likedRecipesDao(): LikedRecipeDao

}