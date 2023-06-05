package com.example.galleryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView

class MainActivity : AppCompatActivity() {

    private val images = listOf(
        R.drawable.art1,
        R.drawable.bird1,
        R.drawable.scenery1
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gridView = findViewById<GridView>(R.id.gridView)
        val adapter = ImageAdapter(this, images)
        gridView.adapter = adapter
    }
}