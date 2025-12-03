package com.example.gymtracker.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    // Работещ примерен URL – промени го към твоето API
    private const val BASE_URL = "https://api.api-ninjas.com/v1/"

    val api: ApiService by lazy {

        // Logging Interceptor
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // OkHttpClient
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        // Retrofit
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
