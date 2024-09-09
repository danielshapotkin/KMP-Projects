package com.example.kotlin

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InstagramApi {

        private val BASE_URL = "https://api.instagram.com/"

        val apiService: ApiService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }


    // Добавим companion object для хранения неизменяемых констант
    companion object {
        private const val CLIENT_ID = "438863165864035"
        private const val CLIENT_SECRET = "d8cb059ce4956ed1ff24225771e4e9f8"
        private const val REDIRECT_URI = "https://localhost/callback"
        private const val GRANT_TYPE = "authorization_code"
    }

    // Основной метод для получения токена доступа
    suspend fun fetchInstagramAccessToken(authCode: String): AccessTokenResponse? {
        return try {
            val response = apiService.getAccessToken(
                clientId = CLIENT_ID,
                clientSecret = CLIENT_SECRET,
                grantType = GRANT_TYPE,
                redirectUri = REDIRECT_URI,
                code = authCode
            )
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
