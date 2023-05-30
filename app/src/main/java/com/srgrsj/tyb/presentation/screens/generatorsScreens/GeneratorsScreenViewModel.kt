package com.srgrsj.tyb.presentation.screens.generatorsScreens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srgrsj.tyb.domain.workout.model.Workout
import com.srgrsj.tyb.domain.workout.usecases.WorkoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class GeneratorsScreenViewModel @Inject constructor(
    private val workoutUseCase: WorkoutUseCase
) : ViewModel() {
    fun saveWorkoutToRealtimeDatabase(workout: Workout) {
        viewModelScope.launch {
            workoutUseCase.addWorkoutUseCase.invoke(workout)
        }
    }

}