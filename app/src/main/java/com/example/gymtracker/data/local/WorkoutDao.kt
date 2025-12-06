package com.example.gymtracker.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gymtracker.data.model.Workout

@Dao
interface WorkoutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: Workout)

    @Query("SELECT * FROM workouts ORDER BY timestamp DESC")
    fun getAllWorkouts(): LiveData<List<Workout>>

    @Query("SELECT * FROM workouts WHERE id = :workoutId")
    fun getWorkoutById(workoutId: Long): LiveData<Workout>

    @Delete
    suspend fun deleteWorkout(workout: Workout)

    @Query("DELETE FROM workouts")
    suspend fun deleteAllWorkouts()

    // Метод за взимане на несинхронизираните записи
    @Query("SELECT * FROM workouts WHERE apiId IS NULL")
    suspend fun getUnsyncedWorkouts(): List<Workout>

    @Query("SELECT id FROM workouts WHERE apiId = :apiId")
    suspend fun getLocalWorkoutIdByApiId(apiId: String): Long?

}