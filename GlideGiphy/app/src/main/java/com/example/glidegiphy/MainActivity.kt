package com.example.glidegiphy

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestOptions
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.nio.ByteBuffer


class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.image)
        Glide
                .with(this)
                .load("https://media2.giphy.com/media/cfuL5gqFDreXxkWQ4o/giphy.gif?cid=bbaedf9e46" +
                        "ukue1dt75ww0fpsoedihp1fxi4235f27ltna9v&rid=giphy.gif")
                .apply(RequestOptions()
                    .placeholder(R.drawable.progress_bar)
                    .diskCacheStrategy(DiskCacheStrategy.NONE))
            .into(imageView)


    }
}