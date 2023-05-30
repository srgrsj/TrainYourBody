package com.srgrsj.tyb.presentation.screens.generatorsScreens.gptGeneratorScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.srgrsj.tyb.R
import com.srgrsj.tyb.domain.workout.model.Workout
import com.srgrsj.tyb.domain.workout.model.WorkoutGenerationType
import com.srgrsj.tyb.presentation.screens.workoutPreviewScreen.WorkoutPreviewScreenType
import com.srgrsj.tyb.presentation.theme.AlphaWhiteColor
import com.srgrsj.tyb.presentation.theme.AppTheme
import com.srgrsj.tyb.presentation.theme.MainBackground
import com.srgrsj.tyb.presentation.theme.Red
import com.srgrsj.tyb.presentation.theme.TopBarColor
import com.srgrsj.tyb.presentation.theme.TopBarText


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GPTGeneratorScreen(
    navigateToWorkoutPreviewScreen: ((Workout, WorkoutPreviewScreenType) -> Unit)? = null,
    viewModel: GPTGeneratorScreenViewModel = hiltViewModel(),

    ) {

    var muscleGroupsTextField by remember {
        mutableStateOf("")
    }

    var desiredDurationOfTrainingTextField by remember {
        mutableStateOf("")
    }

    var additionalWorkoutRequirementsTextField by remember {
        mutableStateOf("")
    }

    val isWorkoutGenerate by viewModel.isWorkoutGenerate.collectAsState()
    val generatedWorkout = viewModel.generatedWorkout.value

    var isInProgress by remember {
        mutableStateOf(false)
    }

    if (isWorkoutGenerate) {
        if (generatedWorkout != null && navigateToWorkoutPreviewScreen != null) {
            generatedWorkout.workoutGenerationType = WorkoutGenerationType.GPT
            viewModel.setIsWorkoutGenerateFalse()
            navigateToWorkoutPreviewScreen(generatedWorkout, WorkoutPreviewScreenType.SAVE)
        }
    }

    Scaffold(
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(TopBarColor)
            ) {
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(id = R.string.gpt_generator_title),
                    style = AppTheme.typography.title,
                    color = TopBarText
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MainBackground)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    TextField(
                        value = muscleGroupsTextField,
                        onValueChange = {
                            muscleGroupsTextField = it
                        },
                        label = {
                            Text(
                                text = stringResource(id = R.string.muscle_groups),
                                style = AppTheme.typography.text16sp,
                                color = AlphaWhiteColor
                            )
                        },
                        textStyle = AppTheme.typography.textFieldStyle
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(
                        value = desiredDurationOfTrainingTextField,
                        onValueChange = {
                            desiredDurationOfTrainingTextField = it
                        },
                        label = {
                            Text(
                                text = stringResource(id = R.string.desired_duration),
                                style = AppTheme.typography.text16sp,
                                color = AlphaWhiteColor
                            )
                        },
                        textStyle = AppTheme.typography.textFieldStyle
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(
                        value = additionalWorkoutRequirementsTextField,
                        onValueChange = {
                            additionalWorkoutRequirementsTextField = it
                        },
                        label = {
                            Text(
                                text = stringResource(id = R.string.additional_requirements),
                                style = AppTheme.typography.text16sp,
                                color = AlphaWhiteColor
                            )
                        },
                        textStyle = AppTheme.typography.textFieldStyle
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        modifier = Modifier
                            .height(50.dp),
                        onClick = {
                            viewModel.generateGptQuery(
                                muscleGroupsTextField,
                                desiredDurationOfTrainingTextField,
                                additionalWorkoutRequirementsTextField
                            )
//                    viewModel.gptQuery = "напиши, что это тестовове сообщение"
                            viewModel.getGPTResponse()
                            isInProgress = true
                        },
                        colors = ButtonDefaults.buttonColors(Red)
                    ) {
                        Text(
                            text = stringResource(id = R.string.generate_workout),
                            style = AppTheme.typography.text16sp
                        )
                    }

                    Spacer(modifier = Modifier.height(25.dp))

                    if (isInProgress) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {

                        }
                        CircularProgressIndicator()
                    }
                }
//            Text(text = viewModel.gptResponse)
            }
        }
    }
}
