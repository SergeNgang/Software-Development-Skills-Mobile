package com.example.taskcraft

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val allActivities = mutableListOf(
        ActivityItem(1, "Mobile App Lecture", "Monday", "09:00", "11:00", false, "Review Kotlin layout fundamentals."),
        ActivityItem(2, "Gym & Cardio", "Monday", "17:00", "18:30", true, "Leg day workout."),
        ActivityItem(3, "Software Testing Prep", "Wednesday", "14:00", "16:00", false, "Prepare unit test cases."),
        ActivityItem(4, "Learning Diary Update", "Friday", "10:00", "11:30", false, "Log weekly development progress.")
    )

    private val displayedActivities = mutableListOf<ActivityItem>()
    private lateinit var adapter: ActivityAdapter
    private var currentFilterDay = "All"
    private val addActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK && result.data != null) {
            val data = result.data!!
            val title = data.getStringExtra("NEW_TITLE") ?: ""
            val day = data.getStringExtra("NEW_DAY") ?: "Monday"
            val startTime = data.getStringExtra("NEW_START_TIME") ?: ""
            val endTime = data.getStringExtra("NEW_END_TIME") ?: ""
            val notes = data.getStringExtra("NEW_NOTES") ?: ""

            val newItem = ActivityItem(
                id = allActivities.size + 1,
                title = title,
                dayOfWeek = day,
                startTime = startTime,
                endTime = endTime,
                notes = notes
            )

            allActivities.add(newItem)
            filterByDay(currentFilterDay)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayedActivities.addAll(allActivities)

        val listView = findViewById<ListView>(R.id.lvActivities)
        adapter = ActivityAdapter(this, displayedActivities)
        listView.adapter = adapter

        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAddActivity)
        fabAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            addActivityLauncher.launch(intent)
        }

        setupDayFilters()

        listView.setOnItemClickListener { _, _, position, _ ->
            val selected = displayedActivities[position]
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("TITLE", selected.title)
                putExtra("DAY", selected.dayOfWeek)
                putExtra("TIME", "${selected.startTime} - ${selected.endTime}")
                putExtra("NOTES", selected.notes)
                putExtra("URL", selected.referenceUrl)
            }
            startActivity(intent)
        }
    }

    private fun setupDayFilters() {
        val filterMap = mapOf(
            R.id.btnAll to "All",
            R.id.btnMon to "Monday",
            R.id.btnTue to "Tuesday",
            R.id.btnWed to "Wednesday",
            R.id.btnThu to "Thursday",
            R.id.btnFri to "Friday",
            R.id.btnSat to "Saturday",
            R.id.btnSun to "Sunday"
        )

        filterMap.forEach { (btnId, day) ->
            findViewById<Button>(btnId).setOnClickListener {
                filterByDay(day)
            }
        }
    }

    private fun filterByDay(day: String) {
        currentFilterDay = day
        displayedActivities.clear()
        if (day == "All") {
            displayedActivities.addAll(allActivities)
        } else {
            displayedActivities.addAll(allActivities.filter { it.dayOfWeek.equals(day, ignoreCase = true) })
        }
        adapter.notifyDataSetChanged()
    }
}