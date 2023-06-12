package com.example.mvvmquotes.data

data class Quote(val quoteText : String, val author : String) { // creating a class to represent a quote
    override fun toString(): String {
        return "$quoteText - $author"
    }
}