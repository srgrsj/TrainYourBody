package com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.workoutScreen

import com.srgrsj.tyb.domain.workout.usecases.WorkoutUseCase
import com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.ScreensUsingWorkoutViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkoutScreenViewModel @Inject constructor(
    workoutUseCase: WorkoutUseCase
) : ScreensUsingWorkoutViewModel(workoutUseCase) {

}