package com.example.firestore.api

import com.example.firestore.models.data.RecipeTutorial
import com.example.firestore.models.data.Recipes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.lang.NumberFormatException

//324694/analyzedInstructions
interface RecipeApi {
//    @GET("632660/analyzedInstructions")
//    suspend fun getRecipes(@Query("apiKey")apiKey: String = "768d5c328b324432ad26d5285e226f69"): Response<RecipeTutorial>


    @GET("findByIngredients?number=2")
    suspend fun getRecipes(@Query("ingredients")ingredients: String = "apples,+flour,+sugar",
                           @Query("apiKey")apiKey: String = "768d5c328b324432ad26d5285e226f69"): Response<Recipes>

    @GET("{id}/information?includeNutrition=false")
    suspend fun getRecipeTutorial(@Path("id") id: Int,
                                  @Query("apiKey")apiKey: String = "768d5c328b324432ad26d5285e226f69"): Response<RecipeTutorial>

    @GET("findByIngredients?number=20")
    suspend fun getRecipesByIngridients(@Query("ingredients") ingredients: String,
                                        @Query("apiKey")apiKey: String = "768d5c328b324432ad26d5285e226f69") : Response<Recipes>
}