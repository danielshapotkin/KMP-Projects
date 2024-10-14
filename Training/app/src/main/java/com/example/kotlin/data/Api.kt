package com.example.kotlin.data

import com.example.kotlin.domain.AuthCodeResponse
import com.example.kotlin.domain.ApiFunction
import com.example.kotlin.domain.IApi
import com.example.kotlin.domain.UserResponse
import com.example.kotlin.domain.refreshTokenResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api: IApi{
    private val BASE_URL = "https://web.postman.co/"
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



    companion object {
        const val BASE_URL = "https://web.postman.co/"
        const val WORKSPACE_PATH = "workspace/My-Workspace~169da7dc-5430-4cbd-8697-48333522cf7d/"
        const val COLLECTION_PATH = "collection/38177814-c5d0220f-253a-450e-9df9-30b9c33c919c/"
        const val ACTION_SHARE = "action=share"
        const val SOURCE_COPY_LINK = "source=copy-link"
        const val CREATOR_ID = "creator=38177814"


        const val FULL_LINK = "$BASE_URL$WORKSPACE_PATH$COLLECTION_PATH?$ACTION_SHARE&$SOURCE_COPY_LINK&$CREATOR_ID"
    }

    // Основной метод для получения authcode
    override suspend fun fetchInstagramAccessToken(authCode: String): AuthCodeResponse? {
        return try {
            val response = apiService.getAccessToken(
                BASE_URL = BASE_URL,
                WORKSPACE_PATH = WORKSPACE_PATH,
                COLLECTION_PATH = COLLECTION_PATH,
                ACTION_SHARE = ACTION_SHARE,
                SOURCE_COPY_LINK = SOURCE_COPY_LINK,
                CREATOR_ID = CREATOR_ID,
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


