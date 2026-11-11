package com.example.taskcraft

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etDay = findViewById<EditText>(R.id.etDay)
        val etStartTime = findViewById<EditText>(R.id.etStartTime)
        val etEndTime = findViewById<EditText>(R.id.etEndTime)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val titleText = etTitle.text.toString().trim()
            val dayText = etDay.text.toString().trim()
            val startText = etStartTime.text.toString().trim()
            val endText = etEndTime.text.toString().trim()

            if (titleText.isNotEmpty() && dayText.isNotEmpty()) {
                val newActivity = ActivityItem(
                    title = titleText,
                    dayOfWeek = dayText,
                    startTime = startText,
                    endTime = endText
                )

                val resultIntent = Intent().apply {
                    putExtra("NEW_ACTIVITY", newActivity)
                }
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}