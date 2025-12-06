package com.example.gymtracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.gymtracker.data.GymRepository
import com.example.gymtracker.data.local.GymDatabase
import com.example.gymtracker.data.model.Workout
import kotlinx.coroutines.launch

// AndroidViewModel е необходим, защото трябва да имаме достъп до Application Context,
// за да инициализираме Room Database.
class MainViewModel(application: Application) : AndroidViewModel(application) {

    // Repository и LiveData са видими само за ViewModel
    private val repository: GymRepository

    // LiveData, която UI-ят ще наблюдава.
    val readAllData: LiveData<List<Workout>>
    // В MainViewModel.kt
    fun getWorkoutById(workoutId: Long) = repository.getWorkoutById(workoutId)
    init {
        // 1. Взимаме инстанция на Database
        val workoutDao = GymDatabase.getDatabase(application).workoutDao()

        // 2. Инициализираме Repository-то с DAO
        repository = GymRepository(workoutDao)

        // 3. Взимаме LiveData от Repository-то
        readAllData = repository.readAllData
    }

    // 1. CREATE/UPDATE (Вкарване на нова тренировка)
    fun addWorkout(workout: Workout) {
        // Изпълняваме блокиращата DB операция в Coroutine (задължително за suspend fun)
        viewModelScope.launch {
            repository.addWorkout(workout)
        }
    }

    // 2. DELETE (Изтриване на тренировка)
    fun deleteWorkout(workout: Workout) {
        viewModelScope.launch {
            repository.deleteWorkout(workout)
        }
    }

    // 3. СИНХРОНИЗАЦИЯ (Оценка 6)
    fun syncWithCloud() { // Може да я преименувате на syncAll, но ще използваме това име
        // ➡️ ИЗВИКВАМЕ ПЪЛНАТА ДВУПОСОЧНА СИНХРОНИЗАЦИЯ от Repository
        viewModelScope.launch {
            repository.syncCloud() // ⬅️ КОРЕКЦИЯ: Вече използва пълната логика
        }
    }
}