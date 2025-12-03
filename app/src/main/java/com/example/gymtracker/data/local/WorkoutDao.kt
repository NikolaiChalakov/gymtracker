package com.example.gymtracker.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gymtracker.data.model.Workout // Импopтиpaйтe вaшия клac Workout

@Dao
interface WorkoutDao {

    // 1. CREATE/UPDATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: Workout)

    // 2. READ
    @Query("SELECT * FROM workouts ORDER BY timestamp DESC")
    fun getAllWorkouts(): LiveData<List<Workout>>

    // 3. DELETE ONE (Изтриване на един запис)
    @Delete
    suspend fun deleteWorkout(workout: Workout)

    // 4. DELETE ALL (Изтриване на всички записи - Полезно за тестване)
    @Query("DELETE FROM workouts")
    suspend fun deleteAllWorkouts()
}