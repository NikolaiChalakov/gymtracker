package com.example.gymtracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String?,
    val muscleGroup: String?,
    val timestamp: Long,
    val apiId: String? = null // Полето за синхронизация
)