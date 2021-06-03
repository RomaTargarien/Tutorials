package com.example.firestore.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.firestore.models.data.RecipeItem

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(recipeItem: RecipeItem): Long

    @Delete
    suspend fun deleteRecipe(recipeItem: RecipeItem)

    @Query("SELECT * FROM recipes")
    fun getAllRecipes(): LiveData<List<RecipeItem>>
}