package com.example.recyclerviewcolors

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    val colorsList = mutableListOf<Int>()
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        colorsList.add(Color.RED)
        colorsList.add(Color.BLUE)
        colorsList.add(Color.MAGENTA)
        colorsList.add(Color.GREEN)
        colorsList.add(Color.YELLOW)
        colorsList.add(Color.WHITE)
        colorsList.add(Color.BLUE)
        colorsList.add(Color.BLACK)
        colorsList.add(Color.LTGRAY)
        colorsList.add(Color.CYAN)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter = ColorsAdapter(colorsList){
            Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
        }

    }
}