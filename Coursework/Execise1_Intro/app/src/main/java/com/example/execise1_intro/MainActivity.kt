package com.example.execise1_intro

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNum1 = findViewById<EditText>(R.id.etNumber1)
        val etNum2 = findViewById<EditText>(R.id.etNumber2)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnAdd.setOnClickListener {
            val input1 = etNum1.text.toString()
            val input2 = etNum2.text.toString()
            if (input1.isNotEmpty() && input2.isNotEmpty()) {
                val sum = input1.toInt() + input2.toInt()
                tvResult.text = "Result: $sum"
                Log.d("AdditionApp", "Calculated sum: $sum")
            } else {
                tvResult.text = "Please enter both numbers"
                Log.e("AdditionApp", "Empty input error caught safely!")
            }
        }
    }
}