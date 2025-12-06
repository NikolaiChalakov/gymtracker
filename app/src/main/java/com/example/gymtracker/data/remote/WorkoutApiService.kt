package com.example.gymtracker.data.remote

import retrofit2.Response
import retrofit2.http.Body // ⬅️ Трябва да се импортира
import retrofit2.http.GET
import retrofit2.http.POST // ⬅️ Трябва да се импортира

interface WorkoutApiService {

    // 1. GET заявка (Сваляне на всички тренировки)
    @GET("workouts")
    suspend fun getWorkouts(): Response<List<ApiExercise>>

    // 2. POST заявка (Качване на нова тренировка)
    // Използваме @Body, за да пратим данните като JSON
    @POST("workouts")
    suspend fun createWorkout(@Body request: WorkoutRequest): Response<ApiExercise>
}