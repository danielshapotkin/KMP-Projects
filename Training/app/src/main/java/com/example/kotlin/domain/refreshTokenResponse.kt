package com.example.kotlin.domain

data class refreshTokenResponse (
    val access_token: String,
    val token_type: String,
    val expires_in: Int,
    val permissions: String
)
