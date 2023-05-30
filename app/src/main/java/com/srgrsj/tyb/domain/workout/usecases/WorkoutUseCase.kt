package com.srgrsj.tyb.domain.workout.usecases

data class WorkoutUseCase(
    val addWorkoutUseCase: AddWorkoutUseCase,
    val getWorkoutsUseCase: GetWorkoutsUseCase,
    val deleteWorkoutUseCase: DeleteWorkoutUseCase,
    val changeWorkoutFavState: ChangeWorkoutFavState
)