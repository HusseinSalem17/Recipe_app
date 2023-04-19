package com.hussein.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe(
    @ColumnInfo(name = "aggregate_likes") val aggregateLikes: Int?,
    @ColumnInfo(name = "analyzed_instructions") val analyzedInstructions: List<AnalyzedInstruction>?,
    val cheap: Boolean?,
    @ColumnInfo(name = "cooking_minutes") val cookingMinutes: Int?,
    @ColumnInfo(name = "credits_text") val creditsText: String?,
    val cuisines: List<Any>?,
    @ColumnInfo(name = "dairy_free") val dairyFree: Boolean?,
    val diets: List<String>?,
    @ColumnInfo(name = "dish_types") val dishTypes: List<String>?,
    @ColumnInfo(name = "extended_ingredients") val extendedIngredients: List<ExtendedIngredient>?,
    val gaps: String?,
    @ColumnInfo(name = "gluten_free") val glutenFree: Boolean?,
    @ColumnInfo(name = "health_score") val healthScore: Int?,
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val image: String?,
    @ColumnInfo(name = "image_type") val imageType: String?,
    val instructions: String?,
    val license: String?,
    @ColumnInfo(name = "low_fodmap") val lowFodmap: Boolean?,
    val occasions: List<Any>?,
    @ColumnInfo(name = "original_id") val originalId: Any?,
    @ColumnInfo(name = "preparation_minutes") val preparationMinutes: Int?,
    @ColumnInfo(name = "price_per_serving") val pricePerServing: Double?,
    @ColumnInfo(name = "ready_in_minutes") val readyInMinutes: Int?,
    val servings: Int?,
    @ColumnInfo(name = "source_name") val sourceName: String?,
    @ColumnInfo(name = "source_url") val sourceUrl: String?,
    @ColumnInfo(name = "spoonacular_source_url") val spoonacularSourceUrl: String?,
    val summary: String?,
    val sustainable: Boolean?,
    @ColumnInfo(name = "title") val title: String?,
    val vegan: Boolean?,
    val vegetarian: Boolean?,
    @ColumnInfo(name = "very_healthy") val veryHealthy: Boolean?,
    @ColumnInfo(name = "very_popular") val veryPopular: Boolean?,
    @ColumnInfo(name = "weight_watcher_smart_points") val weightWatcherSmartPoints: Int?,
)
