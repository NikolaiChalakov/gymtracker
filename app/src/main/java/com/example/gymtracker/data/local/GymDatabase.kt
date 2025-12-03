package com.example.gymtracker.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gymtracker.data.model.Workout

// Анотацията @Database свързва Entity-то (Workout) с Database класа.
// version = 1 е задължително, exportSchema = false е за по-лесно дебъгване.
@Database(entities = [Workout::class], version = 1, exportSchema = false)
abstract class GymDatabase : RoomDatabase() {

    // Абстрактен метод за достъп до DAO
    abstract fun workoutDao(): WorkoutDao

    companion object {
        // Singleton инстанция на базата (за да има само една)
        @Volatile
        private var INSTANCE: GymDatabase? = null

        fun getDatabase(context: Context): GymDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            // Синхронизиран блок, който гарантира, че само една нишка създава базата.
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GymDatabase::class.java,
                    "gym_database" // Името на файла на базата данни в устройството
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}