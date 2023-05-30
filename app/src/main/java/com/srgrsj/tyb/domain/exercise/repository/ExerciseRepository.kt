package com.srgrsj.tyb.domain.exercise.repository

import com.srgrsj.tyb.domain.exercise.model.Exercise

interface ExerciseRepository {
    suspend fun getExercises(): List<Exercise>?

    suspend fun insertExercise(exercise: Exercise)

    suspend fun deleteExercise(exercise: Exercise)
}