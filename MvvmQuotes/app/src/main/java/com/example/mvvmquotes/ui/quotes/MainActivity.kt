package com.example.mvvmquotes.ui.quotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmquotes.R
import com.example.mvvmquotes.data.Quote
import com.example.mvvmquotes.utilities.InjectorUtils

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var textView_quotes : TextView
    private lateinit var editText_quote : EditText
    private lateinit var editText_author : EditText
    private lateinit var button_add_quote : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView_quotes = findViewById(R.id.textView_quotes)
        editText_quote = findViewById(R.id.editText_quote)
        editText_author = findViewById(R.id.editText_author)
        button_add_quote = findViewById(R.id.button_add_quote)

        initializeUi()

    }

    private fun initializeUi()
    {
        val factory = InjectorUtils.providerQuotesViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory).get(QuotesViewModel :: class.java)
        viewModel.getQuotes().observe(this, Observer { quotes ->
            val stringBuilder = StringBuilder()
            quotes.forEach{quote ->
                stringBuilder.append("$quote\n\n")
            }
            textView_quotes.text = stringBuilder.toString()
        })

        button_add_quote.setOnClickListener {
            val quote = Quote(editText_quote.text.toString(), editText_author.text.toString())
            viewModel.addQuote(quote)
            editText_quote.setText("")
            editText_author.setText("")
        }
    }

}