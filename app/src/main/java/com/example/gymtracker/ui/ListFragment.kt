package com.example.gymtracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AlertDialog
import android.widget.Toast

import com.example.gymtracker.R
import com.example.gymtracker.databinding.FragmentListBinding
import com.example.gymtracker.viewmodel.MainViewModel
import com.example.gymtracker.data.model.Workout

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var mViewModel: MainViewModel

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

        setupRecyclerView()
        observeData()
        setupItemTouchHelper()

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeData() {
        mViewModel.readAllData.observe(viewLifecycleOwner) { workouts ->
            adapter.setData(workouts)
        }
    }

    private fun setupItemTouchHelper() {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val workoutToDelete = adapter.workoutList[position]

                AlertDialog.Builder(requireContext())
                    .setTitle("Изтриване на ${workoutToDelete.name}")
                    .setMessage("Сигурни ли сте, че искате да изтриете тази тренировка?")
                    .setPositiveButton("Да") { _, _ ->
                        mViewModel.deleteWorkout(workoutToDelete)
                        Toast.makeText(requireContext(), "${workoutToDelete.name} изтрита!", Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("Не") { dialog, _ ->
                        adapter.notifyItemChanged(position)
                        dialog.dismiss()
                    }
                    .show()
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }
}