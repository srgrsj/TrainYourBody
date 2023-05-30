package com.srgrsj.tyb.presentation.screens.screensUsingWorkouts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srgrsj.tyb.domain.workout.model.Workout
import com.srgrsj.tyb.domain.workout.usecases.WorkoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class ScreensUsingWorkoutViewModel @Inject constructor(
    private val workoutUseCase: WorkoutUseCase
) : ViewModel() {

    private var _workoutList = MutableStateFlow(emptyList<Workout>())
    val workoutList: StateFlow<List<Workout>> = _workoutList.asStateFlow()

    init {
        saveWorkoutsFromRealtimeDatabaseToWorkoutList()
    }

    private fun saveWorkoutsFromRealtimeDatabaseToWorkoutList() {

        workoutUseCase.getWorkoutsUseCase.invoke {
            if (it != null) {
                viewModelScope.launch {
                    _workoutList.emit(it)
                }
            }
        }

    }

    fun changeWorkoutFavState(workout: Workout) {
        val updatedWorkoutList = workoutList.value.map { existingWorkout ->
            if (existingWorkout.id == workout.id) {
                existingWorkout.copy(isInFav = !(existingWorkout.isInFav ?: false))
            } else {
                existingWorkout
            }
        }
        viewModelScope.launch {
            _workoutList.emit(updatedWorkoutList)
            workoutUseCase.changeWorkoutFavState(workout = workout)
        }
    }


    fun deleteWorkoutFromRealtimeDatabase(workout: Workout) {
        viewModelScope.launch {
            workoutUseCase.deleteWorkoutUseCase.invoke(workout = workout)
        }
    }

    fun deleteWorkoutFromWorkoutList(workout: Workout) {
        val updatedWorkoutList = workoutList.value.toMutableList()
        updatedWorkoutList.remove(workout)
        viewModelScope.launch {
            _workoutList.emit(updatedWorkoutList)
        }
    }


    private val _showDeleteWorkoutDialog = MutableStateFlow(false)
    val showDeleteWorkoutDialog: StateFlow<Boolean> = _showDeleteWorkoutDialog.asStateFlow()

    fun showDeleteWorkoutAlertDialog() {
        _showDeleteWorkoutDialog.value = true
    }

    fun hideDeleteWorkoutAlertDialog() {
        _showDeleteWorkoutDialog.value = false
    }
}

