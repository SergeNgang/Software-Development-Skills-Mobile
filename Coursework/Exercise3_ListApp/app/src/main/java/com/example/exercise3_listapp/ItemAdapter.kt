package com.example.exercise3_listapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ItemAdapter(
    private val context: Context,
    private val items: Array<String>,
    private val prices: Array<String>,
    private val descriptions: Array<String>
) : BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int = items.size
    override fun getItem(position: Int): Any = items[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = convertView ?: inflater.inflate(R.layout.my_listview_detail, parent, false)

        val nameTextView = rowView.findViewById<TextView>(R.id.nameTextView)
        val priceTextView = rowView.findViewById<TextView>(R.id.priceTextView)
        val descriptionTextView = rowView.findViewById<TextView>(R.id.descriptionTextView)

        nameTextView?.text = items[position]
        priceTextView?.text = prices[position]
        descriptionTextView?.text = descriptions[position]

        return rowView
    }
}