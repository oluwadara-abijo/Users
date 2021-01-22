package com.dara.users.network

import com.dara.users.data.ApiResponse
import com.dara.users.data.UserDetails
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * This interface defines the endpoints used in the DummyApi backend
 */

interface DummyApiService {

    @GET("user")
    suspend fun getUsers(): ApiResponse

    @GET("user/{userId}")
    suspend fun getUserDetails(@Path("userId") userId: String): UserDetails
}