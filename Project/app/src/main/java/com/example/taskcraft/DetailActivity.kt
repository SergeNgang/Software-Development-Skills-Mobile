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
        val tvCategory = findViewById<TextView>(R.id.tvDetailCategory)
        val tvPriority = findViewById<TextView>(R.id.tvDetailPriority)
        val tvDesc = findViewById<TextView>(R.id.tvDetailDesc)
        val btnOpenDocs = findViewById<Button>(R.id.btnOpenDocs)
        val btnShareTask = findViewById<Button>(R.id.btnShareTask)

        val title = intent.getStringExtra("TITLE") ?: ""
        val day = intent.getStringExtra("DAY") ?: ""
        val time = intent.getStringExtra("TIME") ?: ""
        val url = intent.getStringExtra("URL") ?: "https://developer.android.com"

        tvTitle.text = title
        tvCategory.text = "Day: $day"
        tvPriority.text = "Time: $time"
        tvDesc.text = "Weekly Activity Task"

        btnOpenDocs.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }

        btnShareTask.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "Task: $title on $day ($time)")
            }
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
    }
}