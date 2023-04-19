package com.hussein.data.remote

import com.hussein.domain.entity.RandomRecipesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("recipes/random")
    fun getRandomRecipes(
        @Query("apiKey") apiKey: String,
    ): RandomRecipesResponse
}