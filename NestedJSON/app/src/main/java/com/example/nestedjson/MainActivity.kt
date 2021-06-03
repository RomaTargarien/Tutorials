package com.example.nestedjson

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        parseJSON()


    }
    @SuppressLint("LongLOgTag")
    fun parseJSON(){
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.giphy.com/v1/gifs/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(Api::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getEmployeesNested()
            Log.d("RESPONCE",response.body()?.data.toString())
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    val items = response.body()?.data
                    if (items != null){
                        for (i in 0 until items.count()){
                            //ID
                            val id = items[i].id ?: "N/A"
                            Log.d("ID",id)
                            val url = items[i].images?.original?.url
                            Log.d("URL",url.toString())
                        }
                    } else {
                        Log.e("Retrofit_ERROR",response.code().toString())
                    }
                }
            }
        }
    }
}