package com.artemqq5.workoutapp.presentation.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.artemqq5.workoutapp.R
import com.artemqq5.workoutapp.databinding.FragmentStartBinding
import com.artemqq5.workoutapp.domain.model.Muscles
import com.artemqq5.workoutapp.presentation.adapters.ExercisesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private var muscle: Muscles? = null
    private lateinit var exercisesAdapter: ExercisesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        muscle = getMuscleArgument()
        if (muscle == null) {
            findNavController().navigate(R.id.action_startFragment_to_menuFragment)
            return
        }

        setupUI()
        setupSpinner()
        setupRecyclerView()
        setupStartWorkoutButton()
    }

    // Extract muscle argument with backward compatibility
    private fun getMuscleArgument(): Muscles? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("muscle", Muscles::class.java)
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable("muscle")
        }
    }

    // Initialize UI elements based on muscle data
    @SuppressLint("SetTextI18n")
    private fun setupUI() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_menuFragment)
        }

        muscle?.let {
            binding.textWorkoutName.text = it.nameMusclesGroup
            binding.textWorkoutDetails.text = "${it.exercises.size} exercises - ${it.kcal} Kcal"
            binding.textWorkoutTime.text = formatTime(it.exercises.sumOf { exercise -> exercise.time })
        }
    }

    // Set up Spinner for difficulty selection
    private fun setupSpinner() {
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.text_spinner_item,
            resources.getStringArray(R.array.difficulty_levels)
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerDifficulty.adapter = adapter

        binding.spinnerDifficulty.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedDifficulty = parent.getItemAtPosition(position).toString()
                Log.d("StartFragment", "Selected difficulty: $selectedDifficulty")
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle no selection case if needed
            }
        }
    }

    // Set up RecyclerView for exercises
    private fun setupRecyclerView() {
        exercisesAdapter = ExercisesAdapter()
        binding.recyclerExercises.adapter = exercisesAdapter

        // Передаємо список вправ до адаптера через submitList
        muscle?.let {
            exercisesAdapter.submitList(it.exercises)
        }
    }

    // Configure Start Workout button with navigation
    private fun setupStartWorkoutButton() {
        binding.btnStartWorkout.setOnClickListener {
            val exercises = exercisesAdapter.currentList.toList()
            findNavController().navigate(
                R.id.action_startFragment_to_workoutFragment,
                bundleOf("exercises" to exercises)
            )
        }
    }

    // Format time in minutes with 0 or 1 decimal places
    @SuppressLint("DefaultLocale")
    private fun formatTime(timeInSeconds: Int): String {
        val timeInMinutes = timeInSeconds / 60F
        return if (timeInMinutes % 1 == 0F) {
            String.format("%.0f min", timeInMinutes)
        } else {
            String.format("%.1f min", timeInMinutes)
        }
    }
}
