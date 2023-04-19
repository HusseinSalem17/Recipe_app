package com.hussein.yummyfood.di

import com.hussein.domain.repo.LikedRecipesRepo
import com.hussein.domain.repo.RecipesRepo
import com.hussein.domain.usecase.GetLikedRecipes
import com.hussein.domain.usecase.GetRecipes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideUseCase(recipesRepo: RecipesRepo): GetRecipes {
        return GetRecipes(recipesRepo)
    }

    @Provides
    fun provideLikedRecipeUseCase(likedRecipesRepo: LikedRecipesRepo): GetLikedRecipes {
        return GetLikedRecipes(likedRecipesRepo)
    }
}