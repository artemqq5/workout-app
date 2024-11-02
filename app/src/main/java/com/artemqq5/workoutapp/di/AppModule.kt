package com.artemqq5.workoutapp.di

import com.artemqq5.workoutapp.data.ExerciseRepositoryImpl
import com.artemqq5.workoutapp.domain.ExerciseRepository
import com.artemqq5.workoutapp.domain.usecases.GetMuscleGroupsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {

    @Provides
    @Singleton
    fun provideExerciseRepository(): ExerciseRepository {
        return ExerciseRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideGetMuscleGroupsUseCase(exerciseRepository: ExerciseRepository): GetMuscleGroupsUseCase {
        return GetMuscleGroupsUseCase(exerciseRepository)
    }
}