package com.example.gymtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
// ➡️ ДОБАВЕТЕ ТОЗИ ИМПОРТ:
import com.example.gymtracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 1. Декларирайте променливата
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        // 2. Инициализирайте binding обекта (това се случва на ред 22, където е грешката)
        binding = ActivityMainBinding.inflate(layoutInflater)

        // 3. Задайте View-то
        setContentView(binding.root)


    }
}