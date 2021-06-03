package com.example.database

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    companion object{
        private const val HAS_BEFORE = "was before"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        getPreferences(Context.MODE_PRIVATE).edit().apply(){

        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}