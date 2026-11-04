package com.example.konekinhanivia.network

import com.example.konekinhanivia.model.UserDetail
import com.example.konekinhanivia.model.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("employees")
    fun getAllUsers(): Call<Users>

    // Endpoint untuk mendapatkan detail karyawan berdasarkan ID
    @GET("employee/{id}")
    fun getUserDetail(@Path("id") id: Int): Call<UserDetail>
}