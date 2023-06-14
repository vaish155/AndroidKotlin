package com.vaishnav.mvvmrecyclerapi.network

import retrofit2.Call
import com.vaishnav.mvvmrecyclerapi.RecyclerList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("repositories")
    fun getDatafromAPI(@Query("q") query: String) : Call<RecyclerList>


}