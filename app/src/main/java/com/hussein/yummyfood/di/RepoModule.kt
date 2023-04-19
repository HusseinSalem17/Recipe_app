package com.hussein.yummyfood.di

import com.hussein.data.remote.ApiService
import com.hussein.data.repo.LikedRecipesRepoImpl
import com.hussein.data.repo.RecipesRepoImpl
import com.hussein.domain.dao.LikedRecipeDao
import com.hussein.domain.dao.RecipeDao
import com.hussein.domain.repo.LikedRecipesRepo
import com.hussein.domain.repo.RecipesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    fun provideRepo(apiService: ApiService, recipeDao: RecipeDao): RecipesRepo {
        return RecipesRepoImpl(apiService, recipeDao)
    }

    @Provides
    fun provideLikedRecipeRepo(likedRecipeDao: LikedRecipeDao):LikedRecipesRepo{
        return LikedRecipesRepoImpl(likedRecipeDao)
    }
}