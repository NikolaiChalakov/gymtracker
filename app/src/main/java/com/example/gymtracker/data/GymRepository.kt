package com.example.gymtracker.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.gymtracker.data.local.WorkoutDao
import com.example.gymtracker.data.model.Workout
import com.example.gymtracker.data.remote.ApiExercise
import com.example.gymtracker.data.remote.RetrofitClient
import com.example.gymtracker.data.remote.WorkoutRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GymRepository(private val workoutDao: WorkoutDao) {

    val readAllData: LiveData<List<Workout>> = workoutDao.getAllWorkouts()

    suspend fun addWorkout(workout: Workout) {
        withContext(Dispatchers.IO) {
            workoutDao.insertWorkout(workout)
        }
    }

    // 1. СВАЛЯНЕ ОТ ОБЛАКА (DOWNLOAD)
    suspend fun loadFromInternetAndSave() {
        try {
            val response = RetrofitClient.apiService.getWorkouts()

            if (response.isSuccessful && response.body() != null) {
                val apiExercises = response.body()!!
                Log.d("SYNC_DOWNLOAD", "Успешно изтеглени: ${apiExercises.size} записа.")

                withContext(Dispatchers.IO) {
                    var newRecords = 0
                    var updatedRecords = 0

                    apiExercises.forEach { apiExercise ->
                        // 1. ВАЖНО: Проверка дали записът вече съществува локално по неговия apiId
                        // apiExercise.id трябва да е ID-то от външното API (String)
                        val apiIdString = apiExercise.id.toString()
                        // Използваме workoutDao от конструктора
                        val existingLocalId = workoutDao.getLocalWorkoutIdByApiId(apiIdString)

                        val workoutToSave = if (existingLocalId != null) {
                            // СЛУЧАЙ 1: Записът съществува - Използваме локалното ID за Update
                            updatedRecords++
                            Workout(
                                id = existingLocalId, // <-- Това е КЛЮЧЪТ за UPDATE
                                name = apiExercise.exerciseName,
                                muscleGroup = apiExercise.muscle ?: "Unknown",
                                timestamp = System.currentTimeMillis(),
                                apiId = apiIdString
                            )
                        } else {
                            // СЛУЧАЙ 2: Записът е нов - Room ще генерира ново Primary Key
                            newRecords++
                            Workout(
                                id = 0L, // <-- 0L казва на Room да генерира новото ID
                                name = apiExercise.exerciseName,
                                muscleGroup = apiExercise.muscle ?: "Unknown",
                                timestamp = System.currentTimeMillis(),
                                apiId = apiIdString
                            )
                        }

                        // Insert с OnConflictStrategy.REPLACE ще обнови съществуващия запис
                        // или ще вмъкне новия.
                        workoutDao.insertWorkout(workoutToSave)
                    }
                    Log.d("SYNC_DOWNLOAD", "Синхронизация завършена. Нови: $newRecords, Обновени: $updatedRecords")
                }
            } else {
                val errorBodyString = response.errorBody()?.string()
                Log.e("SYNC_DOWNLOAD", "ГРЕШКА ПРИ GET: Код ${response.code()}")
                Log.e("SYNC_DOWNLOAD", "Тяло на грешката: $errorBodyString")
            }

        } catch (e: Exception) {
            Log.e("SYNC_DOWNLOAD", "Мрежова грешка: ${e.message}")
            e.printStackTrace()
        }
    }

    // 2. КАЧВАНЕ КЪМ ОБЛАКА (UPLOAD)
    suspend fun uploadLocalWorkouts() {
        withContext(Dispatchers.IO) {
            val unsyncedWorkouts = workoutDao.getUnsyncedWorkouts()
            Log.d("SYNC_UPLOAD", "Намерени несинхронизирани записи: ${unsyncedWorkouts.size}")

            unsyncedWorkouts.forEach { localWorkout ->
                try {
                    // ⬇️ СЪЗДАВАНЕ НА ЗАЯВКА (FIX: Безопасно справяне с null)
                    val request = WorkoutRequest(
                        exerciseName = localWorkout.name ?: "", // Ако е null, пращаме празен низ
                        muscle = localWorkout.muscleGroup ?: "Unknown", // Ако е null, пращаме "Unknown"
                        type = "strength",
                        difficulty = "medium"
                    )

                    val response = RetrofitClient.apiService.createWorkout(request)

                    if (response.isSuccessful && response.body() != null) {
                        val createdApiWorkout = response.body()!!
                        Log.d("SYNC_UPLOAD", "УСПЕХ: Качен запис с ID: ${createdApiWorkout.id}")

                        // Обновяваме локалния запис с новото ID от облака
                        val updatedWorkout = localWorkout.copy(apiId = createdApiWorkout.id)
                        workoutDao.insertWorkout(updatedWorkout)
                    } else {
                        Log.e("SYNC_UPLOAD", "ГРЕШКА ПРИ POST: Код ${response.code()}")
                    }
                } catch (e: Exception) {
                    Log.e("SYNC_UPLOAD", "Грешка при качване: ${e.message}")
                    e.printStackTrace()
                }
            }
        }
    }

    // 3. ОБЩА ФУНКЦИЯ ЗА СИНХРОНИЗАЦИЯ
    suspend fun syncCloud() {
        loadFromInternetAndSave()
        uploadLocalWorkouts()
    }

    fun getWorkoutById(workoutId: Long): LiveData<Workout> {
        return workoutDao.getWorkoutById(workoutId)
    }

    suspend fun deleteWorkout(workout: Workout) {
        withContext(Dispatchers.IO) {
            workoutDao.deleteWorkout(workout)
        }
    }
}