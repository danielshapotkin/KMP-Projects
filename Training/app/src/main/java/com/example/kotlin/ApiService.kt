package com.example.kotlin

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("token")
    fun getToken(): Call<String>
}