package com.example.kotlin.domain

interface IApi {
    suspend fun getUsername(token: String): UserResponse?
    suspend fun fetchInstagramAccessToken(authCode: String): AccessTokenResponse?
    suspend fun refreshToken(oldToken: String): refreshTokenResponse?
}