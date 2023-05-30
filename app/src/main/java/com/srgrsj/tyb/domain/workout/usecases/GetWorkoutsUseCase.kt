package com.srgrsj.tyb.domain.workout.usecases

import com.srgrsj.tyb.domain.workout.model.Workout
import com.srgrsj.tyb.domain.workout.repository.WorkoutRepository

class GetWorkoutsUseCase(private val repository: WorkoutRepository) {
    fun invoke(onComplete: ((List<Workout>?) -> Unit)? = null) {
        return repository.getWorkoutsLegacy(onComplete)
    }
}