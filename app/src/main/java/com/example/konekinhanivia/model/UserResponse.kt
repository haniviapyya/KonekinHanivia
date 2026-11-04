package com.example.konekinhanivia.model

import android.os.Message
import com.google.gson.annotations.SerializedName

data class UserResponse (
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: Data,
    @SerializedName("message")
    val message: String
)