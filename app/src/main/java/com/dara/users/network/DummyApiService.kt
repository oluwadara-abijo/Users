package com.dara.users.network

import com.dara.users.data.ApiResponse
import retrofit2.http.GET

/**
 * This interface defines the endpoints used in the DummyApi backend
 */

interface DummyApiService {

    @GET("user")
    suspend fun getUsers(): ApiResponse
}