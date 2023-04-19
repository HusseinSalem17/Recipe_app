package com.hussein.domain.converters

import androidx.room.TypeConverter
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.hussein.domain.entity.*

class Converters {

    @TypeConverter
    fun toRecipe(recipeJson: String): Recipe? {
        val listType = object : TypeToken<Recipe>() {}.type
        return GsonBuilder().create().fromJson(recipeJson, listType)
    }

    @TypeConverter
    fun fromRecipe(recipe: Recipe?): String {
        return GsonBuilder().create().toJson(recipe)
    }

    @TypeConverter
    fun toDetailsRecipeResponse(recipeJson: String): DetailsRecipeResponse? {
        val listType = object : TypeToken<DetailsRecipeResponse>() {}.type
        return GsonBuilder().create().fromJson(recipeJson, listType)
    }

    @TypeConverter
    fun fromDetailsRecipeResponse(recipe: DetailsRecipeResponse?): String {
        return GsonBuilder().create().toJson(recipe)
    }

    @TypeConverter
    fun toWinePairing(recipeJson: String): WinePairing? {
        val listType = object : TypeToken<WinePairing>() {}.type
        return GsonBuilder().create().fromJson(recipeJson, listType)
    }

    @TypeConverter
    fun fromWinePairing(recipe: WinePairing?): String {
        return GsonBuilder().create().toJson(recipe)
    }

    @TypeConverter
    fun fromAnalyzedInstructionToGson(analyzedInstruction: List<AnalyzedInstruction>?): String? {
        return GsonBuilder().create().toJson(analyzedInstruction)
    }

    @TypeConverter
    fun fromGsonToAnalyzedInstruction(string: String?): List<AnalyzedInstruction>? {
        val listType = object : TypeToken<List<AnalyzedInstruction>>() {}.type
        return GsonBuilder().create().fromJson(string, listType)
    }


    @TypeConverter
    fun fromExtendedIngredientToGson(extendedIngredient: List<ExtendedIngredient>?): String? {
        return GsonBuilder().create().toJson(extendedIngredient)
    }

    @TypeConverter
    fun fromGsonToExtendedIngredient(string: String?): List<ExtendedIngredient>? {
        val listType = object : TypeToken<List<ExtendedIngredient>>() {}.type
        return GsonBuilder().create().fromJson(string, listType)
    }

    @TypeConverter
    fun fromExtendedIngredientXToGson(extendedIngredient: List<ExtendedIngredientX>?): String? {
        return GsonBuilder().create().toJson(extendedIngredient)
    }

    @TypeConverter
    fun fromGsonToExtendedIngredientX(string: String?): List<ExtendedIngredientX>? {
        val listType = object : TypeToken<List<ExtendedIngredient>>() {}.type
        return GsonBuilder().create().fromJson(string, listType)
    }

    @TypeConverter
    fun fromListAnyToGson(any: List<Any>?): String? {
        return GsonBuilder().create().toJson(any)
    }

    @TypeConverter
    fun fromGsonToListAny(string: String?): List<Any>? {
        val listType = object : TypeToken<List<Any>>() {}.type
        return GsonBuilder().create().fromJson(string, listType)
    }

    @TypeConverter
    fun fromListStringToGson(listString: List<String>?): String? {
        return GsonBuilder().create().toJson(listString)
    }

    @TypeConverter
    fun fromGsonToListString(string: String?): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return GsonBuilder().create().fromJson(string, listType)
    }

    @TypeConverter
    fun fromAnyToGson(any: Any?): String? {
        return GsonBuilder().create().toJson(any)
    }

    @TypeConverter
    fun fromGsonToAny(string: String?): Any? {
        val listType = object : TypeToken<Any>() {}.type
        return GsonBuilder().create().fromJson(string, listType)
    }

}