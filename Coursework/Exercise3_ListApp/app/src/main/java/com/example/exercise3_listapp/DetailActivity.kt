package com.example.exercise3_listapp

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val itemImageView = findViewById<ImageView>(R.id.itemImageView)

        val imageResId = intent.getIntExtra("com.example.IMAGE_RES_ID", -1)
        if (imageResId != -1) {
            itemImageView.setImageResource(imageResId)
        }
    }
}