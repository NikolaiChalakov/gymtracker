package com.example.gymtracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gymtracker.data.model.Workout
import com.example.gymtracker.databinding.FragmentAddBinding
import com.example.gymtracker.viewmodel.MainViewModel
import androidx.navigation.fragment.navArgs // ⬅️ Добавете този импорт за Safe Args

class AddFragment : Fragment() {

    private lateinit var mViewModel: MainViewModel
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    // Съхраняваме ID-то на тренировката за режим Update
    private var currentWorkoutId: Long = 0L

    // Използване на Safe Args делегат за достъп до аргументите
    private val args: AddFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        // 1. ИЗВЛИЧАНЕ НА АРГУМЕНТА
        currentWorkoutId = args.workoutId

        if (currentWorkoutId != -1L) {
            // (Update)
            binding.saveButton.text = "Обнови Тренировката"

            // 2. ЗАРЕЖДАНЕ НА ДАННИТЕ ПО ID
            mViewModel.getWorkoutById(currentWorkoutId).observe(viewLifecycleOwner) { workout ->

                if (workout != null) {

                    binding.nameEt.setText(workout.name)
                    binding.muscleEt.setText(workout.muscleGroup)

                }
            }
        } else {
            // (Create)
            binding.saveButton.text = "Запис на Тренировката"
        }

        binding.saveButton.setOnClickListener {
            saveOrUpdateData()
        }
    }

    private fun saveOrUpdateData() {
        val name = binding.nameEt.text.toString()
        val muscle = binding.muscleEt.text.toString()

        if (name.isEmpty() || muscle.isEmpty()) {
            Toast.makeText(requireContext(), "Моля, попълнете всички полета.", Toast.LENGTH_SHORT).show()
            return
        }

        // 3.  (Insert/Update)
        val workout = Workout(


            id = if (currentWorkoutId != -1L) currentWorkoutId else 0L,
            name = name,
            muscleGroup = muscle,
            timestamp = System.currentTimeMillis()
        )

        mViewModel.addWorkout(workout)

        Toast.makeText(
            requireContext(),
            "Тренировката е ${if (currentWorkoutId != -1L) "обновена" else "добавена"} успешно!",
            Toast.LENGTH_SHORT
        ).show()
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}