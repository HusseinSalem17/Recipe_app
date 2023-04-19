package com.hussein.data.repo

import com.hussein.data.remote.ApiService
import com.hussein.domain.entity.RandomRecipesResponse
import com.hussein.domain.repo.RecipesRepo

class RecipesRepoImpl(private val apiService: ApiService) : RecipesRepo {
    override fun getRandomRecipesRemote(): RandomRecipesResponse {
        return apiService.getRandomRecipes("44de7e6dd3474f8580aeed49e0994c32")
    }


}