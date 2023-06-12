package com.example.mvvmquotes.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FakeQuoteDao { // this is a fake database
    private val quoteList = mutableListOf<Quote>() // A fake database table
    // MutableLiveData is from the Architecture Components Library
    // LiveData can be observed for changes
    private val quotes = MutableLiveData<List<Quote>>()

    init {
        // Immediately connect the now empty quoteList
        // to the MutableLiveData which can be observed
        quotes.value = quoteList
    }

    fun addQuote(quote: Quote)
    {
        quoteList.add(quote)
        quotes.value = quoteList
    }

    fun getQuotes() : LiveData<List<Quote>>
    {
        return quotes as LiveData<List<Quote>>
    }
}