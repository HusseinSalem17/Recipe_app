package com.hussein.domain.entity

data class SearchRecipesResponse(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
)