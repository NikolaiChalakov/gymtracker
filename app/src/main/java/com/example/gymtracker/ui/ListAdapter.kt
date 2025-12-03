package com.example.gymtracker.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtracker.databinding.CustomRowBinding // <-- Генерира се от custom_row.xml
import com.example.gymtracker.data.model.Workout // <-- Вашият модел
import androidx.recyclerview.widget.DiffUtil // (По-добър стил)
class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var workoutList = emptyList<Workout>()

    inner class MyViewHolder(private val binding: CustomRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(workout: Workout) {
            // Свързване на данните с елементите от custom_row.xml
            // ID-тата name_txt и muscle_txt са от custom_row.xml
            binding.nameTxt.text = workout.name
            binding.muscleTxt.text = workout.muscleGroup
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // "Напомпване" на layout-а с View Binding
        val binding = CustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(workoutList[position])
    }

    override fun getItemCount(): Int = workoutList.size

    // Тази функция се вика от LiveData в ListFragment
    fun setData(newWorkouts: List<Workout>) {
        this.workoutList = newWorkouts
        notifyDataSetChanged() // Може да се замени с DiffUtil за оптимизация
    }
}