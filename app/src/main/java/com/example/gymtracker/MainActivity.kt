package com.example.gymtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
// ➡️ ДОБАВЕТЕ ТОЗИ ИМПОРТ:
import com.example.gymtracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 1. Декларирайте променливата
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. Инициализирайте binding обекта (това се случва на ред 22, където е грешката)
        binding = ActivityMainBinding.inflate(layoutInflater)

        // 3. Задайте View-то
        setContentView(binding.root)

        // Тук вече не добавяме код, тъй като навигационният хост управлява показването на фрагментите
    }
}