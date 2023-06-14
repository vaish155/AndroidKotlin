package com.vaishnav.mvvmrecyclerapi.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    companion object{
        val baseUrl = "https://api.github.com/search/"
        fun getRetroInstance() : Retrofit{
            return Retrofit.Builder()
                .baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}