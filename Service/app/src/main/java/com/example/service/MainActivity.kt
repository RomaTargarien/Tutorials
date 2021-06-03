package com.example.service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bnStart.setOnClickListener {
            Intent(this,MyService::class.java).also {
                startService(it)
                tv.text = "Service Running"
            }
        }
        bnStop.setOnClickListener {
            Intent(this,MyService::class.java).also {
                stopService(it)
                tv.text = "Service stopped"
            }
        }
        bnSendData.setOnClickListener {
            Intent(this,MyService::class.java).also {
                val dataString = edData.text.toString()
                it.putExtra("EXTRA_DATA",dataString)
                startService(it)
            }
        }
    }
}