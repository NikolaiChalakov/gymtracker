package com.example.gymtracker.data.remote

import com.google.gson.annotations.SerializedName

data class ApiExercise(
    // name в JSON-а ще бъде мапнат към exerciseName в Kotlin
    @SerializedName("name")
    val exerciseName: String,

    val type: String?,
    val muscle: String?, // Мускулна група
    val difficulty: String?,

    val apiId: String? // Уникален ID от API-то, ако има
)