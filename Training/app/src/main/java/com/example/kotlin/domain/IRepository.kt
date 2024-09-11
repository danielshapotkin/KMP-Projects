package com.example.kotlin.domain

import android.net.Uri

interface IRepository {
    fun getAuthCode()
    suspend fun getToken(authCode: String): String
}
