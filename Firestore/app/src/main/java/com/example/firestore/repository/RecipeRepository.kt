package com.example.firestore.repository

import com.example.firestore.api.RetrofitInstance
import com.example.firestore.db.RecipeDatabase
import com.example.firestore.models.data.RecipeItem


class RecipeRepository(val db: RecipeDatabase) {
    suspend fun getRecipeTutorial(id: Int) = RetrofitInstance.api.getRecipeTutorial(id)
    suspend fun getRecipesByIngridients(query: String) = RetrofitInstance.api.getRecipesByIngridients(query)

    fun getAllRecipes() = db.getArticleDao().getAllRecipes()
    suspend fun deleteRecipe(recipeItem: RecipeItem) = db.getArticleDao().deleteRecipe(recipeItem)
    suspend fun upsert(recipeItem: RecipeItem) = db.getArticleDao().upsert(recipeItem)
}