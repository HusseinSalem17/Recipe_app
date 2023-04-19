package com.hussein.yummyfood.di

import android.content.Context
import androidx.room.Room
import com.hussein.domain.database.RecipeRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    //Hilt needs to know how to create an instance of NoteDatabase. For that add another method below provideDao.    @Provides
    @Provides
    @Singleton
    fun provide(@ApplicationContext context: Context) = Room.databaseBuilder(
        context.applicationContext, RecipeRoomDatabase::class.java, "recipes_database")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()//This annotation marks the method provideDao as a provider of noteDoa.    @Provides

    @Provides
    @Singleton
    fun provideDao(db: RecipeRoomDatabase) = db.recipeDao()

    @Provides
    @Singleton
    fun provideLikedRecipeDao(db: RecipeRoomDatabase) = db.likedRecipesDao()

}