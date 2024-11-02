package com.artemqq5.workoutapp.domain

import com.artemqq5.workoutapp.domain.model.Muscles

interface ExerciseRepository {
    fun getMuscleGroups(): List<Muscles>
}
