package com.example.gymtracker.data.remote

import com.google.gson.annotations.SerializedName

data class ApiExercise(
    // ➡️ КОРЕКЦИЯ: Добавяме задължителното ID от MockAPI
    // То ще се използва като 'apiId' в GymRepository
    val id: String,

    // name в JSON-а ще бъде мапнат към exerciseName в Kotlin
    @SerializedName("name")
    val exerciseName: String,

    val type: String?,
    val muscle: String?, // Мускулна група
    val difficulty: String?,

    // 💡 ЗАБЕЛЕЖКА: Тъй като MockAPI не връща поле 'apiId',
    // това поле винаги ще бъде null при сваляне, освен ако не сте го добавили ръчно в схемата.
    val apiId: String? = null
)