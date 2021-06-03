package com.example.theme

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private val retrofit by lazy {
            val logging  = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient
                .Builder()
                .addInterceptor(logging)
                .build()

            Retrofit.Builder()
                .baseUrl("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes" +
                        "/findByIngredients?ingredients=apples%2Cflour%2Csugar&number=5&ranking=1&ignorePantry=true")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        }
        val api by lazy {
            retrofit.create(Api::class.java)
        }
    }
}
