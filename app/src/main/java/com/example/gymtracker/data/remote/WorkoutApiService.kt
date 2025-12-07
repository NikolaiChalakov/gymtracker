package com.example.gymtracker.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WorkoutApiService {

    @GET("workouts")
    suspend fun getWorkouts(): Response<List<ApiExercise>>

    @POST("workouts")
    suspend fun createWorkout(@Body request: WorkoutRequest): Response<ApiExercise>
}