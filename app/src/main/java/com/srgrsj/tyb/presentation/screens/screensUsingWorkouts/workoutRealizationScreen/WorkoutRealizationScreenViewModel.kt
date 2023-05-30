package com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.workoutRealizationScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.srgrsj.tyb.domain.exercise.model.Exercise
import com.srgrsj.tyb.domain.workout.usecases.WorkoutUseCase
import com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.ScreensUsingWorkoutViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkoutRealizationScreenViewModel @Inject constructor(
    useCase: WorkoutUseCase
) : ScreensUsingWorkoutViewModel(useCase) {

    var isProgressStop by mutableStateOf(true)
    var exerciseList: List<Exercise> by mutableStateOf(emptyList())
    var currentExerciseIndex by mutableStateOf(0)
    var currentCircle by mutableStateOf(1)
    var currentRestCircle by mutableStateOf(1)
    var isInRest by mutableStateOf(false)

    fun startStopProgress() {
        isProgressStop = !isProgressStop
    }

    fun goToNextExercise() {
        currentExerciseIndex++
        isInRest = false
        currentCircle = 1
        currentRestCircle = 1
    }

    fun increaseCurrentCircle(){
        currentCircle++

        isInRest = true
    }

    fun increaseCurrentRestCircle(){
        currentRestCircle++

        isInRest = false
    }
}
