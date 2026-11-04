package com.example.exercise3_listapp

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myListView = findViewById<ListView>(R.id.myListView)

        val items = arrayOf("peach", "tomato", "squash")
        val prices = arrayOf("$0.99", "$1.49", "$0.89")
        val descriptions = arrayOf(
            "Fresh peaches from Georgia",
            "Fresh salad tomatoes from Ohio",
            "Fresh yellow squash from California"
        )
        val images = intArrayOf(
            R.drawable.peach,
            R.drawable.tomato,
            R.drawable.squash
        )

        val itemAdapter = ItemAdapter(this, items, prices, descriptions)
        myListView.adapter = itemAdapter

        myListView.setOnItemClickListener { _, _, position, _ ->
            val showDetailIntent = Intent(applicationContext, DetailActivity::class.java).apply {
                putExtra("com.example.IMAGE_RES_ID", images[position])
            }
            startActivity(showDetailIntent)
        }
    }
}