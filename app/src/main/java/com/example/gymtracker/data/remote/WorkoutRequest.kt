package com.example.gymtracker.data.remote

import com.google.gson.annotations.SerializedName

data class WorkoutRequest(
    @SerializedName("name")
    val exerciseName: String,
    val muscle: String?,
    val type: String? = "strength", // стойност по подразбиране
    val difficulty: String? = "medium"
)