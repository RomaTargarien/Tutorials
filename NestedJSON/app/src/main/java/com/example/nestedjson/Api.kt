package com.example.nestedjson

import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("search?api_key=ve7RPYk6h9zOuZWDyubSLm8uUtHB9mo9&q=cats&limit=5&offset=0&rating=g&lang=en")
    suspend fun getEmployeesNested(): Response<NestedJSONModel>
}