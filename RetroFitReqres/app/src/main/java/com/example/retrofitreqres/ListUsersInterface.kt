package com.example.retrofitreqres

import retrofit2.Response
import retrofit2.http.GET

interface ListUsersInterface {
    @GET("/api/users?page=2")
    suspend fun getUserList() : Response<ApiData>
}