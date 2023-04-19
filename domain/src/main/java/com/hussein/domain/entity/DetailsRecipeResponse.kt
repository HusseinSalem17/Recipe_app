package com.hussein.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "liked_recipes")
data class DetailsRecipeResponse(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "image")
    val image: String?,

    @ColumnInfo(name = "servings")
    val servings: Int?,

    @ColumnInfo(name = "ready_in_minutes")
    val readyInMinutes: Int?,

    @ColumnInfo(name = "aggregate_likes")
    val aggregateLikes: Int?,

    @ColumnInfo(name = "health_score")
    val healthScore: Int?,

    @ColumnInfo(name = "price_per_serving")
    val pricePerServing: Double?,

    @ColumnInfo(name = "extended_ingredients")
    val extendedIngredients: List<ExtendedIngredientX>?,

    @ColumnInfo(name = "analyzed_instructions")
    val analyzedInstructions: List<Any>?,

    @ColumnInfo(name = "cuisines")
    val cuisines: List<Any>?,

    @ColumnInfo(name = "dish_types")
    val dishTypes: List<String>?,

    @ColumnInfo(name = "diets")
    val diets: List<Any>?,

    @ColumnInfo(name = "occasions")
    val occasions: List<Any>?,

    @ColumnInfo(name = "wine_pairing")
    val winePairing: WinePairing?,

    @ColumnInfo(name = "instructions")
    val instructions: String?,

    @ColumnInfo(name = "summary")
    val summary: String?,

    @ColumnInfo(name = "source_name")
    val sourceName: String?,

    @ColumnInfo(name = "source_url")
    val sourceUrl: String?,

    @ColumnInfo(name = "spoonacular_source_url")
    val spoonacularSourceUrl: String?,

    @ColumnInfo(name = "credits_text")
    val creditsText: String?,

    @ColumnInfo(name = "license")
    val license: String?,

    @ColumnInfo(name = "vegan")
    val vegan: Boolean?,

    @ColumnInfo(name = "vegetarian")
    val vegetarian: Boolean?,

    @ColumnInfo(name = "gluten_free")
    val glutenFree: Boolean?,

    @ColumnInfo(name = "dairy_free")
    val dairyFree: Boolean?,

    @ColumnInfo(name = "very_healthy")
    val veryHealthy: Boolean?,

    @ColumnInfo(name = "cheap")
    val cheap: Boolean?,

    @ColumnInfo(name = "very_popular")
    val veryPopular: Boolean?,

    @ColumnInfo(name = "sustainable")
    val sustainable: Boolean?,

    @ColumnInfo(name = "weight_watcher_smart_points")
    val weightWatcherSmartPoints: Int?,

    @ColumnInfo(name = "gaps")
    val gaps: String?,

    @ColumnInfo(name = "low_fodmap")
    val lowFodmap: Boolean?,

    @ColumnInfo(name = "original_id")
    val originalId: Any?,

    @ColumnInfo(name = "preparation_minutes")
    val preparationMinutes: Int?,

    @ColumnInfo(name = "cooking_minutes")
    val cookingMinutes: Int?,

    @ColumnInfo(name = "image_type")
    val imageType: String?,
)

