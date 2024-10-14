package com.example.kotlin.domain

import com.google.gson.annotations.SerializedName

data class AuthCodeResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("user_id") val userId: String
)
