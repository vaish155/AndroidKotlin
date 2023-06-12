package com.example.mvvmquotes.ui.quotes

import androidx.lifecycle.ViewModel
import com.example.mvvmquotes.data.Quote
import com.example.mvvmquotes.data.QuoteRepository

class QuotesViewModel(private val quoteRepository: QuoteRepository) : ViewModel(){
    fun getQuotes() = quoteRepository.getQuotes()

    fun addQuote(quote: Quote) = quoteRepository.addQuote(quote)


}