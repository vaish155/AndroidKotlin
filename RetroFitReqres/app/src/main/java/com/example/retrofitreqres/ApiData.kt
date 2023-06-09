package com.example.retrofitreqres

data class ApiData(
    val page : Int,
    val per_page : Int,
    val total : Int,
    val data: List<UserData>
)
