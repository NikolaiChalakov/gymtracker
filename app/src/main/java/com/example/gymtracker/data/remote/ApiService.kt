package com.example.gymtracker.data.remote

import retrofit2.http.GET

interface ApiService {

    // @GET дефинира типа на HTTP заявката (GET) и пътя ('endpoint')
    // suspend fun е задължително, защото Retrofit използва Coroutines
    @GET("exercises")
    suspend fun getExercises(): List<ApiExercise>
}