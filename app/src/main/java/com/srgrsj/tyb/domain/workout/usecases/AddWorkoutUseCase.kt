package com.srgrsj.tyb.domain.workout.usecases

import com.srgrsj.tyb.domain.workout.model.Workout
import com.srgrsj.tyb.domain.workout.repository.WorkoutRepository

class AddWorkoutUseCase(private val repository: WorkoutRepository) {
    suspend fun invoke(workout: Workout) {
        repository.insertWorkout(workout = workout)
    }
}