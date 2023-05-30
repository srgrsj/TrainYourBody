package com.srgrsj.tyb.domain.exercise.usecase

data class ExerciseUseCase(
    val addExerciseUseCase: AddExerciseUseCase,
    val getExercisesUseCase: GetExercisesUseCase,
    val deleteExerciseUseCase: DeleteExerciseUseCase
)