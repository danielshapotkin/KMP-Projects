package com.example.kotlin.domain

interface IApi {
    suspend fun getUsername(token: String): UserResponse?
    suspend fun fetchInstagramAccessToken(authCode: String): AuthCodeResponse?
    suspend fun refreshToken(oldToken: String): refreshTokenResponse?
}