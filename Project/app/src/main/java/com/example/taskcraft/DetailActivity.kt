package com.example.taskcraft

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvTitle = findViewById<TextView>(R.id.tvDetailTitle)
        val tvDay = findViewById<TextView>(R.id.tvDetailCategory)
        val tvTime = findViewById<TextView>(R.id.tvDetailPriority)
        val tvNotes = findViewById<TextView>(R.id.tvDetailDesc)
        val btnOpenDocs = findViewById<Button>(R.id.btnOpenDocs)
        val btnShare = findViewById<Button>(R.id.btnShareTask)

        val title = intent.getStringExtra("TITLE") ?: ""
        val day = intent.getStringExtra("DAY") ?: ""
        val time = intent.getStringExtra("TIME") ?: ""
        val notes = intent.getStringExtra("NOTES") ?: ""
        val url = intent.getStringExtra("URL") ?: "https://developer.android.com"

        tvTitle.text = title
        tvDay.text = "Day: $day"
        tvTime.text = "Time: $time"
        tvNotes.text = if (notes.isNotEmpty()) notes else "No additional notes."

        btnOpenDocs.text = "Open Reference Link"
        btnOpenDocs.setOnClickListener {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(webIntent)
        }

        btnShare.text = "Share Activity"
        btnShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "Activity: $title")
                putExtra(Intent.EXTRA_TEXT, "Scheduled Activity: $title\nDay: $day\nTime: $time\n\nNotes: $notes")
            }
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
    }
}