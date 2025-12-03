package com.example.gymtracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// @Entity маркира този клас като таблица в Room Database.
// tableName="workouts" дефинира името на таблицата в SQLite.
@Entity(tableName = "workouts")
data class Workout(

    // id е уникален идентификатор и е Primary Key.
    // autoGenerate = true кара Room автоматично да увеличава ID-то при всяко ново вмъкване.
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    // Полетата на таблицата:
    val name: String,
    val muscleGroup: String,

    // timestamp обикновено се използва за съхранение на дата и час като Long (милисекунди от епохата).
    val timestamp: Long
)