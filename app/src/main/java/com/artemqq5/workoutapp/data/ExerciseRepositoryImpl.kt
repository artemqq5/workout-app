package com.artemqq5.workoutapp.data

import com.artemqq5.workoutapp.R
import com.artemqq5.workoutapp.domain.ExerciseRepository
import com.artemqq5.workoutapp.domain.model.Exercise
import com.artemqq5.workoutapp.domain.model.Muscles
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor() : ExerciseRepository {

    // Imitates loading data
    override fun getMuscleGroups(): List<Muscles> {
        return listOf(
            Muscles(
                nameMusclesGroup = "Legs",
                icon = R.drawable.img1,
                exercises = arrayListOf(
                    Exercise(R.drawable.img4, "Squats", 10),
                    Exercise(R.drawable.img3, "Lunges", 10),
                    Exercise(R.drawable.img2, "Bulgarian lunges", 10),
                ),
                kcal = 45
            )
        )
    }
}
