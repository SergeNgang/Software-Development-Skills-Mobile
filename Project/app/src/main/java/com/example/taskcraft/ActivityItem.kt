package com.example.taskcraft

data class ActivityItem(
    val id: Int,
    val title: String,
    val dayOfWeek: String,
    val startTime: String,
    val endTime: String,
    var isDone: Boolean = false,
    val notes: String = "",
    val referenceUrl: String = "https://developer.android.com"
)