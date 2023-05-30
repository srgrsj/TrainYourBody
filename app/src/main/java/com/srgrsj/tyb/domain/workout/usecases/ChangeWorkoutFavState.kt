package com.srgrsj.tyb.domain.workout.usecases

import com.srgrsj.tyb.domain.workout.model.Workout
import com.srgrsj.tyb.domain.workout.repository.WorkoutRepository

class ChangeWorkoutFavState(private val repository: WorkoutRepository) {
    suspend operator fun invoke(workout: Workout) {
        repository.changeWorkoutFavState(workout = workout)
    }
}