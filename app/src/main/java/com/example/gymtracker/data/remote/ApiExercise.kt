package com.example.gymtracker.data.remote

import com.google.gson.annotations.SerializedName

data class ApiExercise(
    val id: String,

    @SerializedName("name")
    val exerciseName: String,

    val type: String?,
    val muscle: String?,
    val difficulty: String?,

    val apiId: String? = null
)