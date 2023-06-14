package com.vaishnav.mvvmrecyclerapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonmain : Button = findViewById(R.id.buttonmain)
        buttonmain.setOnClickListener {
            startActivity(Intent(this, RecyclerView :: class.java))
        }
    }
}