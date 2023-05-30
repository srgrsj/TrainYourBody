package com.srgrsj.tyb.presentation.screens.workoutPreviewScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srgrsj.tyb.domain.workout.model.Workout
import com.srgrsj.tyb.domain.workout.usecases.WorkoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutPreviewScreenViewModel @Inject constructor(
    private val workoutUseCase: WorkoutUseCase
) : ViewModel() {
    fun saveWorkoutToRealtimeDatabase(workout: Workout) {
        viewModelScope.launch {
            workoutUseCase.addWorkoutUseCase.invoke(workout)
        }
    }
}