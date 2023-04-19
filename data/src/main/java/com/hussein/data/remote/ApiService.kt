package com.hussein.data.remote

import com.hussein.domain.entity.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("recipes/random")
    suspend fun getRandomRecipes(
        @Query("apiKey") apiKey: String,
        @Query("number") number: String,
        @Query("tags") tags: String,
    ): RandomRecipesResponse

    @GET("recipes/{id}/information")
    suspend fun getDetailsRecipe(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String,
    ): DetailsRecipeResponse

    @GET("recipes/{id}/analyzedInstructions")
    suspend fun getInstructionsRecipe(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String,
    ): AnalyzedInstructionsResponse

    @GET("recipes/complexSearch")
    suspend fun getSearchRecipes(
        @Query("query") query: String,
        @Query("apiKey") apiKey: String,
    ): SearchRecipesResponse

}