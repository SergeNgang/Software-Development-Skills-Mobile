package com.example.exercise2_quicklauncher

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val tvReceivedMessage = findViewById<TextView>(R.id.tvReceivedMessage)

        if (intent.hasExtra("com.example.exercise2_quicklauncher.SOMETHING")) {
            val text = intent.getStringExtra("com.example.exercise2_quicklauncher.SOMETHING")
            tvReceivedMessage.text = text
        }
    }
}