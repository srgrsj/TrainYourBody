package com.srgrsj.tyb.domain.workout.repository

import com.srgrsj.tyb.domain.workout.model.Workout

interface WorkoutRepository {

    fun getWorkoutsLegacy(onComplete: ((List<Workout>?) -> Unit)? = null)

//    suspend fun getFavWorkouts(): List<Workout>?

    suspend fun insertWorkout(workout: Workout)

    suspend fun changeWorkoutFavState(workout: Workout)

    suspend fun deleteWorkout(workout: Workout)
}