package com.artemqq5.workoutapp.domain.usecases

import com.artemqq5.workoutapp.domain.ExerciseRepository
import com.artemqq5.workoutapp.domain.model.Muscles
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMuscleGroupsUseCase @Inject constructor(
    private val repository: ExerciseRepository
) {
    // Executes fetching of muscle groups as a Flow with error handling
    fun execute(): Flow<Result<List<Muscles>>> = flow {
        try {
            val muscles = repository.getMuscleGroups()
            emit(Result.success(muscles))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}
