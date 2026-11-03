package com.example.exercise2_quicklauncher
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSecondActivity = findViewById<Button>(R.id.btnSecondActivity)
        val btnGoogle = findViewById<Button>(R.id.btnGoogle)
        val btnMaps = findViewById<Button>(R.id.btnMaps)

        btnSecondActivity.setOnClickListener {
            val startIntent = Intent(applicationContext, SecondActivity::class.java).apply {
                putExtra("com.example.exercise2_quicklauncher.SOMETHING", "Hello from MainActivity!")
            }
            startActivity(startIntent)
        }
        btnGoogle.setOnClickListener {
            val googleUrl = "https://www.google.com"
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(googleUrl))
            startActivity(webIntent)
        }
        btnMaps.setOnClickListener {
            val geoUri = Uri.parse("geo:0,0?q=LUT+University+Lahti")
            val mapIntent = Intent(Intent.ACTION_VIEW, geoUri)
            startActivity(mapIntent)
        }
    }
}