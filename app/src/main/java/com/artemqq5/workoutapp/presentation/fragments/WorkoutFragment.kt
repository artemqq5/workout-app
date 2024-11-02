package com.artemqq5.workoutapp.presentation.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.artemqq5.workoutapp.R
import com.artemqq5.workoutapp.databinding.FragmentWorkoutBinding
import com.artemqq5.workoutapp.domain.model.Exercise
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkoutFragment : Fragment() {

    private lateinit var binding: FragmentWorkoutBinding
    private var isPaused = false
    private var timeRemaining: Long = 0L
    private lateinit var timer: CountDownTimer
    private lateinit var exercises: List<Exercise>
    private var currentExerciseIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkoutBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exercises = getExercisesArgument()
        if (exercises.isEmpty()) {
            navigateToMenu()
            return
        }

        initializeExercise()
        setupPauseButton()
        binding.backArrow.setOnClickListener { navigateToMenu() }
    }

    // Get exercises from arguments
    private fun getExercisesArgument(): List<Exercise> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelableArrayList("exercises", Exercise::class.java) ?: arrayListOf()
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelableArrayList("exercises") ?: arrayListOf()
        }
    }

    // Initialize the current exercise
    private fun initializeExercise() {
        currentExerciseIndex = 0
        setExercise(currentExerciseIndex)
        startTimer(timeRemaining)
    }

    // Set up the current exercise
    private fun setExercise(index: Int) {
        val exercise = exercises[index]
        binding.exerciseName.text = exercise.name
        timeRemaining = exercise.time * 1000L
    }

    // Start the timer for the current exercise
    private fun startTimer(timeInMillis: Long) {
        timer = object : CountDownTimer(timeInMillis, 1000) {
            @SuppressLint("DefaultLocale")
            override fun onTick(millisUntilFinished: Long) {
                timeRemaining = millisUntilFinished
                updateCountdownTimer(millisUntilFinished)
            }

            override fun onFinish() {
                advanceToNextExerciseOrEnd()
            }
        }
        timer.start()
    }

    // Update the countdown display
    @SuppressLint("DefaultLocale")
    private fun updateCountdownTimer(millisUntilFinished: Long) {
        val minutes = (millisUntilFinished / 1000) / 60
        val seconds = (millisUntilFinished / 1000) % 60
        binding.countdownTimer.text = String.format("%02d:%02d", minutes, seconds)
    }

    // Handle advancing to the next exercise or ending the workout
    private fun advanceToNextExerciseOrEnd() {
        currentExerciseIndex++
        if (currentExerciseIndex < exercises.size) {
            setExercise(currentExerciseIndex)
            startTimer(timeRemaining)
        } else {
            navigateToMenu()
        }
    }

    // Set up pause/resume button
    @SuppressLint("SetTextI18n")
    private fun setupPauseButton() {
        binding.pauseButton.setOnClickListener {
            isPaused = !isPaused
            if (isPaused) {
                timer.cancel()
                binding.pauseButton.text = "Resume"
            } else {
                startTimer(timeRemaining)
                binding.pauseButton.text = "Pause"
            }
        }
    }

    // Navigate back to menu fragment
    private fun navigateToMenu() {
        findNavController().navigate(R.id.action_workoutFragment_to_menuFragment)
    }
}
