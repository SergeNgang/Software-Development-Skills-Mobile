package com.example.taskcraft

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val spDay = findViewById<Spinner>(R.id.spDay)
        val etStartTime = findViewById<EditText>(R.id.etStartTime)
        val etEndTime = findViewById<EditText>(R.id.etEndTime)
        val etNotes = findViewById<EditText>(R.id.etNotes)
        val btnSave = findViewById<Button>(R.id.btnSave)

        val days = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, days)
        spDay.adapter = spinnerAdapter

        btnSave.setOnClickListener {
            val title = etTitle.text.toString().trim()
            val day = spDay.selectedItem.toString()
            val startTime = etStartTime.text.toString().trim()
            val endTime = etEndTime.text.toString().trim()
            val notes = etNotes.text.toString().trim()

            if (title.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
                Toast.makeText(this, "Please enter a title and time frame", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val resultIntent = Intent().apply {
                putExtra("NEW_TITLE", title)
                putExtra("NEW_DAY", day)
                putExtra("NEW_START_TIME", startTime)
                putExtra("NEW_END_TIME", endTime)
                putExtra("NEW_NOTES", notes)
            }

            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}