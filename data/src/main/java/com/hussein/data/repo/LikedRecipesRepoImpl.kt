package com.hussein.data.repo

import com.hussein.domain.dao.LikedRecipeDao
import com.hussein.domain.entity.DetailsRecipeResponse
import com.hussein.domain.repo.LikedRecipesRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LikedRecipesRepoImpl @Inject constructor(private val likedRecipeDao: LikedRecipeDao) :
    LikedRecipesRepo {
    override fun getAllLikedRecipes(): Flow<List<DetailsRecipeResponse?>> {
        return likedRecipeDao.getAllLikedRecipes()
    }

    override fun exists(id: Int): Boolean {
        return likedRecipeDao.exists(id)
    }


    override suspend fun insertLikedRecipe(recipe: DetailsRecipeResponse?) {
        likedRecipeDao.insertLikedRecipe(recipe)
    }

    override suspend fun insertLikedList(recipes: List<DetailsRecipeResponse?>) {
        likedRecipeDao.insertLikedList(recipes)
    }

    override suspend fun deleteLikedRecipe(recipe: DetailsRecipeResponse?) {
        likedRecipeDao.deleteLikedRecipe(recipe)
    }

    override suspend fun deleteAllLikedRecipes() {
        likedRecipeDao.deleteAllLikedRecipes()
    }
}