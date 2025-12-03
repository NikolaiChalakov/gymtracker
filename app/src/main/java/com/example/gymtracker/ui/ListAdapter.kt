package com.example.gymtracker.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gymtracker.databinding.CustomRowBinding
import com.example.gymtracker.data.model.Workout
import androidx.recyclerview.widget.DiffUtil

class ListAdapter(

    private val onWorkoutClicked: (Workout) -> Unit
) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {


    var workoutList = emptyList<Workout>()

    inner class MyViewHolder(private val binding: CustomRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(workout: Workout) {
            // Свързване на данните
            binding.nameTxt.text = workout.name
            binding.muscleTxt.text = workout.muscleGroup

            // ➡️ КЛИК СЛУШАТЕЛ за Редактиране (UPDATE)
            binding.root.setOnClickListener {
                onWorkoutClicked(workout)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(workoutList[position])
    }

    override fun getItemCount(): Int = workoutList.size

    fun setData(newWorkouts: List<Workout>) {
        this.workoutList = newWorkouts
        notifyDataSetChanged()
    }
}