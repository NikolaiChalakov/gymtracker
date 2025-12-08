package com.example.gymtracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.gymtracker.data.GymRepository
import com.example.gymtracker.data.local.GymDatabase
import com.example.gymtracker.data.model.Workout
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    // Repository и LiveData са видими само за ViewModel
    private val repository: GymRepository

    // LiveData
    val readAllData: LiveData<List<Workout>>

    fun getWorkoutById(workoutId: Long) = repository.getWorkoutById(workoutId)
    init {

        val workoutDao = GymDatabase.getDatabase(application).workoutDao()

        repository = GymRepository(workoutDao)

        readAllData = repository.readAllData
    }

    // 1. CREATE/UPDATE
    fun addWorkout(workout: Workout) {
        viewModelScope.launch {
            repository.addWorkout(workout)
        }
    }

    // 2. DELETE
    fun deleteWorkout(workout: Workout) {
        viewModelScope.launch {
            repository.deleteWorkout(workout)
        }
    }

    // 3. СИНХРОНИЗАЦИЯ
    fun syncWithCloud() {
      viewModelScope.launch {
            repository.syncCloud()
        }
    }
}