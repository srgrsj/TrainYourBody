package com.srgrsj.tyb.domain.exercise.usecase

import com.srgrsj.tyb.domain.exercise.model.Exercise
import com.srgrsj.tyb.domain.exercise.repository.ExerciseRepository

class GetExercisesUseCase(private val repository: ExerciseRepository) {
    suspend fun invoke(): List<Exercise>? {
        return repository.getExercises()
    }
}