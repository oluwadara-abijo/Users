package com.dara.users.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * This creates a Retrofit object
 */

object DummyApiClient {

    private const val BASE_URL = "https://dummyapi.io/data/api/"

    // An OkHttp object for sending and receiving requests
    private val okHttpClient = OkHttpClient.Builder().apply {
        addInterceptor {
            val request =
                it.request().newBuilder()
                    .addHeader("app-id", "6001bb020e1ceeddd0211b7e")
                    .build()
            it.proceed(request)
        }
        connectTimeout(2, TimeUnit.MINUTES)
        readTimeout(2, TimeUnit.MINUTES)
        writeTimeout(1, TimeUnit.MINUTES)
    }.build()

    // Generates an implementation of the [DummyApiService] interface
    fun getService(): DummyApiService {
        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DummyApiService::class.java)
    }

}