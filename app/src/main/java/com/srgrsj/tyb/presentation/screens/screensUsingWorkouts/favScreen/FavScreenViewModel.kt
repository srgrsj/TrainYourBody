package com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.favScreen

import com.srgrsj.tyb.domain.workout.usecases.WorkoutUseCase
import com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.ScreensUsingWorkoutViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavScreenViewModel @Inject constructor(
    workoutUseCase: WorkoutUseCase
) : ScreensUsingWorkoutViewModel(workoutUseCase) {

}