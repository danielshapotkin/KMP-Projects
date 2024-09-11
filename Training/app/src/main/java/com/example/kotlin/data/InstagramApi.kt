package com.example.kotlin.data

import android.util.Log
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

class InstagramApi {



    interface ApiService {
        @FormUrlEncoded
        @POST("/oauth/access_token")
        suspend fun getAccessToken(
            @Field("client_id") clientId: String,
            @Field("client_secret") clientSecret: String,
            @Field("grant_type") grantType: String,
            @Field("redirect_uri") redirectUri: String,
            @Field("code") code: String
        ): Response<AccessTokenResponse>

        @GET("/me")
        suspend fun getInstagramUsername(
        @Query("fields") fields: String = "id, username",
        @Query("access_token") accessToken: String
        ): Response<UserResponse>
    }

    private val BASE_URL = "https://api.instagram.com/"
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    private val BASE_GET_URL = "https://graph.instagram.com/me/"
    val GetApiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_GET_URL)
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
            response.body()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    suspend fun getUsername(token: String): UserResponse? {
     return try {
         val response = GetApiService.getInstagramUsername("id, username", token)
         response.body()
     } catch (e: Exception) {
         Log.d("Err", "MyErr")
         e.printStackTrace()
         null
     }
     }
    }


