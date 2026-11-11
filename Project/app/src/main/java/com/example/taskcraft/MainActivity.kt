package com.example.taskcraft

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val allActivities = mutableListOf(
        ActivityItem(
            title = "Mobile App Lecture",
            dayOfWeek = "MON",
            startTime = "09:00",
            endTime = "11:00",
            isCompleted = false
        ),
        ActivityItem(
            title = "Gym & Cardio",
            dayOfWeek = "MON",
            startTime = "17:00",
            endTime = "18:30",
            isCompleted = true
        ),
        ActivityItem(
            title = "Software Testing Prep",
            dayOfWeek = "WED",
            startTime = "14:00",
            endTime = "16:00",
            isCompleted = false
        ),
        ActivityItem(
            title = "Learning Diary Update",
            dayOfWeek = "FRI",
            startTime = "10:00",
            endTime = "11:30",
            isCompleted = false
        )
    )

    private val displayedActivities = ArrayList<ActivityItem>()
    private lateinit var adapter: ActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayedActivities.addAll(allActivities)

        val listView = findViewById<ListView>(R.id.listViewActivities)
        adapter = ActivityAdapter(this, displayedActivities)
        listView.adapter = adapter

        // Item click opens DetailActivity
        listView.setOnItemClickListener { _, _, position, _ ->
            val item = displayedActivities[position]
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("TITLE", item.title)
                putExtra("DAY", item.dayOfWeek)
                putExtra("TIME", "${item.startTime} - ${item.endTime}")
                putExtra("URL", item.referenceUrl)
            }
            startActivity(intent)
        }

        // Plus (+) FAB explicitly opens AddActivity
        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAdd)
        fabAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, 100)
        }

        setupFilterButtons()
    }

    private fun setupFilterButtons() {
        findViewById<Button>(R.id.btnFilterAll)?.setOnClickListener { filterByDay("ALL") }
        findViewById<Button>(R.id.btnFilterMon)?.setOnClickListener { filterByDay("MON") }
        findViewById<Button>(R.id.btnFilterTue)?.setOnClickListener { filterByDay("TUE") }
        findViewById<Button>(R.id.btnFilterWed)?.setOnClickListener { filterByDay("WED") }
        findViewById<Button>(R.id.btnFilterThu)?.setOnClickListener { filterByDay("THU") }
        findViewById<Button>(R.id.btnFilterFri)?.setOnClickListener { filterByDay("FRI") }
        findViewById<Button>(R.id.btnFilterSat)?.setOnClickListener { filterByDay("SAT") }
        findViewById<Button>(R.id.btnFilterSun)?.setOnClickListener { filterByDay("SUN") }
    }

    private fun filterByDay(day: String) {
        displayedActivities.clear()
        if (day == "ALL") {
            displayedActivities.addAll(allActivities)
        } else {
            displayedActivities.addAll(allActivities.filter { it.dayOfWeek.equals(day, ignoreCase = true) })
        }
        adapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK && data != null) {
            val newActivity = data.getSerializableExtra("NEW_ACTIVITY") as? ActivityItem
            newActivity?.let {
                allActivities.add(it)
                displayedActivities.add(it)
                adapter.notifyDataSetChanged()
            }
        }
    }
}