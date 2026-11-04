package com.example.konekinhanivia.network

import com.example.konekinhanivia.model.Users
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("employees")
    fun getAllUsers(): Call<Users>
}