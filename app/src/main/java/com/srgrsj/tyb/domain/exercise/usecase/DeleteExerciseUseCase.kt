package com.srgrsj.tyb.domain.exercise.usecase

import com.srgrsj.tyb.domain.exercise.model.Exercise
import com.srgrsj.tyb.domain.exercise.repository.ExerciseRepository

class DeleteExerciseUseCase(private val repository: ExerciseRepository) {
    suspend fun invoke(exercise: Exercise) {
        repository.deleteExercise(exercise = exercise)
    }
}