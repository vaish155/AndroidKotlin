package com.example.retrofitquotes

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quotesApi = RetrofitHelper.getInstance().create(QuotesApi :: class.java)

        // Luanching a new coroutine:
        GlobalScope.launch {
            val result = quotesApi.getQuotes()
            if(result != null)
            {
                Log.e(TAG, "Result body: " + result.body().toString())
                Log.e(TAG, "Result Headers "+ result.headers().toString())
                Log.e(TAG, "Result Message: " + result.message())
            }
        }

    }
}