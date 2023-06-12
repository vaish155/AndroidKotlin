package com.example.mvvmquotes.utilities

import com.example.mvvmquotes.data.FakeDatabase
import com.example.mvvmquotes.data.QuoteRepository
import com.example.mvvmquotes.ui.quotes.QuotesViewModelFactory

object InjectorUtils {
    fun providerQuotesViewModelFactory() : QuotesViewModelFactory
    {
        val quoteRepository = QuoteRepository.getInstance(FakeDatabase.getInstance().quoteDao)
        return QuotesViewModelFactory(quoteRepository)
    }
}