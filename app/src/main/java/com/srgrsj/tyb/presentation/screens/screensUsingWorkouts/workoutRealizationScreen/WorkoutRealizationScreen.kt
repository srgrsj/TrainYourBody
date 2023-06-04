package com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.workoutRealizationScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.srgrsj.tyb.R
import com.srgrsj.tyb.domain.exercise.model.ExerciseType
import com.srgrsj.tyb.domain.workout.model.Workout
import com.srgrsj.tyb.presentation.navigation.NavConstants
import com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.workoutRealizationScreen.components.ExerciseRealization
import com.srgrsj.tyb.presentation.theme.AppTheme
import com.srgrsj.tyb.presentation.theme.MainBackground
import com.srgrsj.tyb.presentation.theme.Red
import com.srgrsj.tyb.presentation.theme.TopBarColor
import com.srgrsj.tyb.presentation.theme.TopBarText
import kotlinx.coroutines.delay


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WorkoutRealizationScreen(
    navigationController: NavController,
    viewModel: WorkoutRealizationScreenViewModel = hiltViewModel()
) {
    val displayingWorkout = remember {
        mutableStateOf(navigationController.previousBackStackEntry?.savedStateHandle?.get<Workout>("workout"))
    }
    val exerciseList = displayingWorkout.value?.exerciseList
    val currentExercise = exerciseList?.getOrNull(viewModel.currentExerciseIndex)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = TopBarColor,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = displayingWorkout.value?.title ?: "no data",
                    style = AppTheme.typography.subtitle,
                    color = TopBarText,
                    modifier = Modifier
                        .padding(start = 12.dp)
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MainBackground)
        ) {
            Column() {

                Spacer(modifier = Modifier.height(5.dp))

                if (exerciseList != null) {
                    if (viewModel.currentExerciseIndex < exerciseList.size) {
                        currentExercise?.let {
                            ExerciseRealization(
                                exercise = it,
                                viewModel = viewModel
                            )
                        }
                    } else {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20))
                                    .fillMaxWidth(0.95f)
                                    .fillMaxHeight(0.4f)
                                    .background(Red)
                                    .border(3.dp, Color.Black, RoundedCornerShape(20))

                            ) {
                                Text(
                                    text = stringResource(id = R.string.workout_is_over),
                                    style = AppTheme.typography.subtitle,
                                    color = Color.Black
                                )
                            }

                            Spacer(modifier = Modifier.height(25.dp))

                            Button(
                                onClick = {
                                    navigationController.navigate(NavConstants.WORKOUTS)
                                },
                                colors = ButtonDefaults.buttonColors(Red),
                                modifier = Modifier
                                    .height(50.dp)
                                    .clip(RoundedCornerShape(20))
                            ) {
                                Text(
                                    text = stringResource(id = R.string.back_to_workouts_screen),
                                    style = AppTheme.typography.text16sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
