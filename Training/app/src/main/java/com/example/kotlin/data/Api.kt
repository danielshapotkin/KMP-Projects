package com.example.kotlin.data

import android.util.Log
import com.example.kotlin.domain.AccessTokenResponse
import com.example.kotlin.domain.ApiFunction
import com.example.kotlin.domain.IApi
import com.example.kotlin.domain.UserResponse
import com.example.kotlin.domain.refreshTokenResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

class Api: IApi{
    private val BASE_URL = "https://api.instagram.com/"
    val apiService: ApiFunction by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiFunction::class.java)
    }

    private val BASE_GET_URL = "https://graph.instagram.com/"
    val GetApiService: ApiFunction by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_GET_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiFunction::class.java)
    }


    // Добавим companion object для хранения неизменяемых констант
    companion object {
        private const val CLIENT_ID = "438863165864035"
        private const val CLIENT_SECRET = "d8cb059ce4956ed1ff24225771e4e9f8"
        private const val REDIRECT_URI = "https://localhost/callback"
        private const val GRANT_TYPE = "authorization_code"
    }

    // Основной метод для получения токена доступа
    override suspend fun fetchInstagramAccessToken(authCode: String): AccessTokenResponse? {
        return try {
            val response = apiService.getAccessToken(
                clientId = CLIENT_ID,
                clientSecret = CLIENT_SECRET,
                grantType = GRANT_TYPE,
                redirectUri = REDIRECT_URI,
                code = authCode
            )
            response.body()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    override suspend fun getUsername(token: String): UserResponse? {
     return try {
         val response = GetApiService.getInstagramUsername("id, username", token)
         response.body()
     } catch (e: Exception) {
         e.printStackTrace()
         null
     }
     }
    override suspend fun refreshToken(oldToken: String): refreshTokenResponse? {
            val response =  GetApiService.refreshToken("ig_refresh_token", oldToken)
            return response.body()
    }
    }


