package com.example.taskcraft

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView

class ActivityAdapter(
    private val context: Context,
    private val activityList: MutableList<ActivityItem>
) : BaseAdapter() {

    override fun getCount(): Int = activityList.size

    override fun getItem(position: Int): Any = activityList[position]

    override fun getItemId(position: Int): Long = activityList[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_activity, parent, false)
        val item = activityList[position]

        val tvTitle = view.findViewById<TextView>(R.id.tvActivityTitle)
        val tvTime = view.findViewById<TextView>(R.id.tvTimeFrame)
        val tvDay = view.findViewById<TextView>(R.id.tvDayBadge)
        val cbDone = view.findViewById<CheckBox>(R.id.cbIsDone)

        tvTitle.text = item.title
        tvTime.text = "${item.startTime} - ${item.endTime}"
        tvDay.text = item.dayOfWeek.take(3).uppercase()

        cbDone.setOnCheckedChangeListener(null)

        cbDone.isChecked = item.isDone
        updateStrikethrough(tvTitle, item.isDone)

        cbDone.setOnCheckedChangeListener { _, isChecked ->
            item.isDone = isChecked
            updateStrikethrough(tvTitle, isChecked)
        }

        return view
    }

    private fun updateStrikethrough(textView: TextView, isDone: Boolean) {
        if (isDone) {
            textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}