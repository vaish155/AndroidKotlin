package com.example.retrofitquotes

data class QuoteClass(
    val count: Int,
    val totalCount : Int,
    val page : Int,
    val totalPages : Int,
    val lastItemIndex : Int,
    val results : List<Result>
)
