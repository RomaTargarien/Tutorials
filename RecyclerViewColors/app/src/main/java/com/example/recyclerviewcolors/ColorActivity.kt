package com.example.recyclerviewcolors

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ColorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color)
    }
    companion object {
        private const val EXTRA_COLOR = "EXTRA"
        fun getStartIntent(context: Context,color: Int) = Intent(context, ColorActivity::class.java).apply {
            putExtra(EXTRA_COLOR, color)
        }
    }
}