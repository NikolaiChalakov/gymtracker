package com.example.gymtracker.data

import androidx.lifecycle.LiveData
import com.example.gymtracker.data.local.WorkoutDao
import com.example.gymtracker.data.model.Workout
import com.example.gymtracker.data.remote.ApiExercise
import com.example.gymtracker.data.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// Repository-то приема DAO като зависимост (Dependency Injection)
class GymRepository(private val workoutDao: WorkoutDao) {

    // Read All Data (Четене от Room)
    val readAllData: LiveData<List<Workout>> = workoutDao.getAllWorkouts()

    // Insert/Create (Запис в Room)
    suspend fun addWorkout(workout: Workout) {
        // Използваме Dispatchers.IO за блокиращи операции (най-добре е да се използва в Repository)
        withContext(Dispatchers.IO) {
            workoutDao.insertWorkout(workout)
        }
    }

    // ФУНКЦИЯ ЗА ОЦЕНКА 6: Сваляне от Интернет и Кеширане в Room
    suspend fun loadFromInternetAndSave() {
        try {
            // Викаме мрежовата заявка
            val apiExercises = RetrofitInstance.api.getExercises()

            // Записваме във базата, използвайки IO нишка
            withContext(Dispatchers.IO) {
                apiExercises.forEach { apiExercise ->
                    // Преобразуване (Mapping) на ApiExercise към Workout Entity
                    val workout = Workout(
                        name = apiExercise.exerciseName,
                        muscleGroup = apiExercise.muscle ?: "Неизвестна",
                        timestamp = System.currentTimeMillis()
                    )
                    workoutDao.insertWorkout(workout)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun getWorkoutById(workoutId: Long): LiveData<Workout> {
        // ➡️ Просто предаваме извикването към DAO
        return workoutDao.getWorkoutById(workoutId)
    }
    // Delete (по желание)
    suspend fun deleteWorkout(workout: Workout) {
        withContext(Dispatchers.IO) {
            workoutDao.deleteWorkout(workout)
        }
    }
}