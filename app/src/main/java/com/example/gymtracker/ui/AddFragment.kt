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

class AddFragment : Fragment() {

    private lateinit var mViewModel: MainViewModel
    private var _binding: FragmentAddBinding? = null
    // Използваме ViewBinding за достъп до елементите в layout-а
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment (fragment_add.xml)
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Инициализация на ViewModel
        mViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        // 2. Слушател за бутона "Запис"
        binding.saveButton.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        // Взимане на текста от EditText полетата
        val name = binding.nameEt.text.toString()
        val muscle = binding.muscleEt.text.toString()

        // Проверка дали полетата са празни
        if (name.isEmpty() || muscle.isEmpty()) {
            Toast.makeText(requireContext(), "Моля, попълнете всички полета.", Toast.LENGTH_SHORT).show()
            return
        }

        // 3. Създаване на обект Workout
        val workout = Workout(
            // id=0, тъй като Room ще го генерира автоматично
            id = 0,
            name = name,
            muscleGroup = muscle,
            timestamp = System.currentTimeMillis()
        )

        // 4. Изпращане на данните към ViewModel за запис в Room
        mViewModel.addWorkout(workout)

        // 5. Показване на съобщение за успех и навигация назад
        Toast.makeText(requireContext(), "Тренировката е добавена успешно!", Toast.LENGTH_SHORT).show()
        findNavController().popBackStack() // Връщане към ListFragment
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}