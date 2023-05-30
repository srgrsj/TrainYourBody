package com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.workoutRealizationScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.srgrsj.tyb.R
import com.srgrsj.tyb.domain.exercise.model.Exercise
import com.srgrsj.tyb.domain.exercise.model.ExerciseType
import com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.workoutRealizationScreen.WorkoutRealizationScreenViewModel
import com.srgrsj.tyb.presentation.theme.AppTheme
import com.srgrsj.tyb.presentation.theme.Red

@Composable
fun ExerciseRealization(
    exercise: Exercise,
    viewModel: WorkoutRealizationScreenViewModel = hiltViewModel()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20))
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.4f)
                .background(Red)
                .border(3.dp, Color.Black, RoundedCornerShape(20))
        ) {
            Spacer(modifier = Modifier.height(5.dp))

            when (exercise.exerciseType) {
                ExerciseType.TIME -> {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = exercise.title.toString(),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 16.dp),
                            style = AppTheme.typography.subtitle,
                            color = Color.Black
                        )
                    }

                    Spacer(modifier = Modifier.height(35.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.exercise_circles),
                                style = AppTheme.typography.text16sp
                            )
                            Text(
                                text = "${viewModel.currentCircle} / ${exercise.numberOfCircles ?: 1}",
                                style = AppTheme.typography.text16sp
                            )
                        }

                        if (viewModel.currentCircle <= exercise.numberOfCircles!!) {
                            if (viewModel.isInRest) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    exercise.durationOfRest?.let {
                                        CountDownTimer(
                                            totalTime = it,
                                            isRest = true
                                        )
                                    }
                                }

                            } else {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    exercise.durationOfOneCircle?.let {
                                        CountDownTimer(
                                            totalTime = it,
                                            isRest = false
                                        )
                                    }
                                }
                            }
                        } else {
                            viewModel.goToNextExercise()
                        }
                    }
                }

                ExerciseType.REPETITION -> {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = exercise.title.toString(),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 16.dp),
                            style = AppTheme.typography.subtitle,
                            color = Color.Black
                        )
                    }


                    Spacer(modifier = Modifier.height(35.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = stringResource(id = R.string.exercise_repetitions) + " " + exercise.numberOfRepetitions.toString() +
                                    " " + stringResource(id = R.string.exercise_circles) + " " + exercise.numberOfCircles.toString(),
                            style = AppTheme.typography.subtitle,
                            color = Color.Black
                        )
                    }
                }

                else -> {}
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { viewModel.goToNextExercise() },
                colors = ButtonDefaults.buttonColors(Red),
                modifier = Modifier
                    .height(50.dp)
                    .clip(RoundedCornerShape(20)),
                //                enabled = exercise.exerciseType != ExerciseType.REPETITION
            ) {
                Text(
                    text = stringResource(id = R.string.next_exercise),
                    style = AppTheme.typography.text16sp
                )
            }

            Button(
                onClick = { viewModel.startStopProgress() },
                colors = ButtonDefaults.buttonColors(Red),
                modifier = Modifier
                    .height(50.dp)
                    .clip(RoundedCornerShape(20))
            ) {
                Icon(
                    painter = painterResource(
                        id = if (viewModel.isProgressStop) R.drawable.baseline_play_arrow_24
                        else R.drawable.baseline_pause_24
                    ),
                    contentDescription = null
                )
            }
        }
    }
}
