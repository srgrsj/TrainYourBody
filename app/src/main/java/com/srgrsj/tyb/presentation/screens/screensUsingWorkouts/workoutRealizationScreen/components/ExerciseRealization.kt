package com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.workoutRealizationScreen.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.srgrsj.tyb.presentation.components.AnimatedTextBlock
import com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.workoutRealizationScreen.WorkoutRealizationScreenViewModel
import com.srgrsj.tyb.presentation.theme.AppTheme
import com.srgrsj.tyb.presentation.theme.Orange
import com.srgrsj.tyb.presentation.theme.Red
import io.sanghun.compose.video.RepeatMode
import io.sanghun.compose.video.VideoPlayer
import io.sanghun.compose.video.controller.VideoPlayerControllerConfig
import io.sanghun.compose.video.uri.VideoPlayerMediaItem

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ExerciseRealization(
    exercise: Exercise,
    viewModel: WorkoutRealizationScreenViewModel = hiltViewModel()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .padding(top = 15.dp)
                .clip(RoundedCornerShape(10))
                .fillMaxWidth(0.95f)
//                .fillMaxHeight(0.5f)
                .background(Orange)
                .border(3.dp, Color.Black, RoundedCornerShape(10))

        ) {
            when (exercise.exerciseType) {
                ExerciseType.TIME -> {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = exercise.title.toString(),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(vertical = 5.dp, horizontal = 16.dp),
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
                                text = "${viewModel.currentRestCircle} / ${exercise.numberOfCircles ?: 1}",
                                style = AppTheme.typography.text16sp
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            Text(
                                text = stringResource(id = R.string.status),
                                style = AppTheme.typography.text16sp
                            )
                            Text(
                                text = if (viewModel.isInRest) stringResource(id = R.string.rest) else stringResource(
                                    id = R.string.work
                                ),
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
                            modifier = Modifier.padding(vertical = 5.dp, horizontal = 16.dp),
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

            AnimatedTextBlock(
                condition = exercise.description != null && exercise.description != "",
                text = exercise.description.toString()
            )

            Spacer(modifier = Modifier.height(40.dp))
        }

        exercise.demonstration?.let {
            Spacer(modifier = Modifier.height(25.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                VideoPlayer(
                    mediaItems = listOf(
                        VideoPlayerMediaItem.NetworkMediaItem(
                            url = it
                        )
                    ),
                    handleLifecycle = true,
                    autoPlay = true,
                    usePlayerController = true,
                    enablePip = false,
                    handleAudioFocus = true,
                    controllerConfig = VideoPlayerControllerConfig(
                        showSpeedAndPitchOverlay = false,
                        showSubtitleButton = false,
                        showCurrentTimeAndTotalTime = true,
                        showBufferingProgress = true,
                        showForwardIncrementButton = true,
                        showBackwardIncrementButton = true,
                        showBackTrackButton = false,
                        showNextTrackButton = false,
                        showRepeatModeButton = false,
                        controllerShowTimeMilliSeconds = 5_000,
                        controllerAutoShow = true,
                        showFullScreenButton = false
                    ),
                    volume = 0.5f,
                    repeatMode = RepeatMode.ONE,
                    modifier = Modifier
                        .height(215.dp)
                        .fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        if (exercise.exerciseType == ExerciseType.REPETITION) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { viewModel.goToNextExercise() },
                    colors = ButtonDefaults.buttonColors(Orange),
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
            }
        } else {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { viewModel.goToNextExercise() },
                    colors = ButtonDefaults.buttonColors(Orange),
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
                    colors = ButtonDefaults.buttonColors(Orange),
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
}
