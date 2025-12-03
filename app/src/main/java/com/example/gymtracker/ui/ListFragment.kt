package com.example.gymtracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

// Импорти от вашия проект
import com.example.gymtracker.R
import com.example.gymtracker.databinding.FragmentListBinding
import com.example.gymtracker.viewmodel.MainViewModel
import com.example.gymtracker.data.model.Workout // Може да е нужен
import com.example.gymtracker.ui.ListAdapter

class ListFragment : Fragment() {

    // ... (Променливи _binding, binding, mViewModel) ...
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var mViewModel: MainViewModel

    // Нова променлива за адаптера
    private val adapter: ListAdapter by lazy { ListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // 6. Сетъп на RecyclerView, Обзървъри и т.н.
        setupRecyclerView()
        observeData()

        // Свързване на бутоните
        // Трябва да имате FloatingActionButton с ID 'floatingActionButton' във fragment_list.xml
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        // Трябва да имате бутон с ID 'syncButton' във fragment_list.xml за оценка 6
        // binding.syncButton.setOnClickListener {
        //    mViewModel.syncWithCloud()
        // }
    }

    // 7. КЛЮЧОВО! Нулиране на binding-а
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        // Свързване на адаптера и задаване на LayoutManager
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeData() {
        // Наблюдаване на LiveData от ViewModel-а
        mViewModel.readAllData.observe(viewLifecycleOwner) { workouts ->
            // Обновява адаптера с новите данни от базата
            adapter.setData(workouts)
        }
    }
}