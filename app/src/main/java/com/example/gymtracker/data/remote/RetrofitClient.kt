package com.example.gymtracker.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://69319fb311a8738467cfce30.mockapi.io/api/v1/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: WorkoutApiService by lazy {
        retrofit.create(WorkoutApiService::class.java)
    }
}