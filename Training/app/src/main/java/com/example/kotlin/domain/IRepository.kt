package com.example.kotlin.domain

import android.net.Uri
import kotlinx.coroutines.Deferred

interface IRepository {
    suspend fun getToken(authCode: String): String
    suspend fun getUsername():String
}
