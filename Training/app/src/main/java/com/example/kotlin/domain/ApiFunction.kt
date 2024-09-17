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

    @GET("/refresh_access_token")
    suspend fun refreshToken(
        @Query("grant_type") fields: String = "ig_refresh_token",
        @Query("access_token") accessToken: String
    ): Response<refreshTokenResponse>




}

