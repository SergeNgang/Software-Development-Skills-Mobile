package com.example.taskcraft

import java.io.Serializable

data class ActivityItem(
    val title: String,
    val dayOfWeek: String,
    val startTime: String,
    val endTime: String,
    var isCompleted: Boolean = false,
    val referenceUrl: String = "https://developer.android.com"
) : Serializable