package com.example.kotlin.domain

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiFunction {
    @FormUrlEncoded
    @POST("/oauth/access_token")
    suspend fun getAccessToken(
        @Field("BASE_URL") BASE_URL: String,
        @Field("WORKSPACE_PATH") WORKSPACE_PATH: String,
        @Field("COLLECTION_PATH") COLLECTION_PATH: String,
        @Field("ACTION_SHARE") ACTION_SHARE: String,
        @Field("SOURCE_COPY_LINK") SOURCE_COPY_LINK: String,
        @Field("CREATOR_ID") CREATOR_ID: String

    ): Response<AuthCodeResponse>





    @GET("/me")
    suspend fun getInstagramUsername(
        @Query("fields") fields: String = "id, username",
        @Query("access_token") accessToken: String
    ): Response<UserResponse>

    @GET("/refresh_access_token")
    suspend fun refreshToken(
        @Query("grant_type") fields: String = "ig_refresh_token",
        @Query("access_token") accessToken: String
    ): Response<refreshTokenResponse>




}

