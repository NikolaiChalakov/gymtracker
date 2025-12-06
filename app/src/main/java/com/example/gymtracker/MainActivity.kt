package com.example.gymtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.gymtracker.databinding.ActivityMainBinding // ⬅️ Correct Import

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // 1. Декларирайте променливата

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        // 2. Инициализирайте binding обекта - Този ред "инфлейтва" layout-а.
        binding = ActivityMainBinding.inflate(layoutInflater) // <--- Тук се създава View йерархията

        // 3. Задайте View-то - Този ред го прави активното съдържание на Activity-то.
        setContentView(binding.root) // <--- View-то вече е зададено!

        // **РЕД 14 (ОРИГИНАЛЕН ПРОБЛЕМ):**
        // Ако тук е бил старият ред за Window Insets, той вече трябва да работи,
        // защото binding.root вече не е null.
        // Ако използвате по-новия ViewCompat.setOnApplyWindowInsetsListener,
        // той трябва да се приложи към коренния елемент (binding.root) или конкретен View.

        // Пример (ако това е бил ред 14):
        // ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
        //     // ... вашата логика
        // }
    }
}