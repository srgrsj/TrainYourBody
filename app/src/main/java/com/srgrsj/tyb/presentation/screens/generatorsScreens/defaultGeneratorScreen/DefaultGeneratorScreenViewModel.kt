package com.srgrsj.tyb.presentation.screens.generatorsScreens.defaultGeneratorScreen

import androidx.lifecycle.viewModelScope
import com.srgrsj.tyb.domain.exercise.model.Exercise
import com.srgrsj.tyb.domain.exercise.usecase.ExerciseUseCase
import com.srgrsj.tyb.domain.workout.usecases.WorkoutUseCase
import com.srgrsj.tyb.presentation.screens.generatorsScreens.GeneratorsScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DefaultGeneratorScreenViewModel @Inject constructor(
    private val exerciseUseCase: ExerciseUseCase,
    workoutUseCase: WorkoutUseCase
) : GeneratorsScreenViewModel(workoutUseCase) {

    private var _exerciseList = MutableStateFlow(listOf<Exercise>())
    val exerciseList: StateFlow<List<Exercise>> = _exerciseList.asStateFlow()

    //Exercise
    private val _showAddExerciseDialog = MutableStateFlow(false)
    val showAddExerciseDialog: StateFlow<Boolean> = _showAddExerciseDialog.asStateFlow()

    fun showAddExerciseAlertDialog() {
        _showAddExerciseDialog.value = true
    }

    fun hideAddExerciseAlertDialog() {
        _showAddExerciseDialog.value = false
    }

    fun saveExerciseToExerciseList(exercise: Exercise) {
        val eList = exerciseList.value.toMutableList()
        eList.add(exercise)
        viewModelScope.launch {
            _exerciseList.emit(eList)
        }
    }

    fun deleteExerciseFromExerciseList(exercise: Exercise) {
        val eList = exerciseList.value.toMutableList()
        eList.remove(exercise)
        viewModelScope.launch {
            _exerciseList.emit(eList)
        }
    }


    //Workout
    private val _showSaveWorkoutDialog = MutableStateFlow(false)
    val showSaveWorkoutDialog: StateFlow<Boolean> = _showSaveWorkoutDialog.asStateFlow()

    fun showSaveWorkoutAlertDialog() {
        _showSaveWorkoutDialog.value = true
    }

    fun hideSaveWorkoutAlertDialog() {
        _showSaveWorkoutDialog.value = false
    }

}