package com.srgrsj.tyb.presentation.screens.generatorsScreens.components

import android.content.res.Resources
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.srgrsj.tyb.R
import com.srgrsj.tyb.domain.exercise.model.Exercise
import com.srgrsj.tyb.domain.exercise.model.ExerciseType
import com.srgrsj.tyb.presentation.components.TimePicker
import com.srgrsj.tyb.presentation.screens.generatorsScreens.defaultGeneratorScreen.DefaultGeneratorScreenViewModel
import com.srgrsj.tyb.presentation.theme.AlphaWhiteColor
import com.srgrsj.tyb.presentation.theme.AppTheme
import com.srgrsj.tyb.presentation.theme.Green
import com.srgrsj.tyb.presentation.theme.MainBackground
import com.srgrsj.tyb.presentation.theme.Red

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun AddExerciseAlertDialog(
    viewModel: DefaultGeneratorScreenViewModel = hiltViewModel()
) {
    var title: String by remember { mutableStateOf("") }
    var numberOfRepetitions: String by remember { mutableStateOf("") }
    var numberOfRepetitionsIsError by remember { mutableStateOf(false) }
    var numberOfCircles: String by remember { mutableStateOf("") }
    var numberOfCirclesIsError by remember { mutableStateOf(false) }
    var durationOfOneCircle = remember { mutableStateOf(0L) }
    var durationOfOneCircleIsError by remember { mutableStateOf(false) }
    var durationOfRest = remember { mutableStateOf(0L) }
    var durationOfRestIsError by remember { mutableStateOf(false) }
    var exerciseType: ExerciseType by remember { mutableStateOf(ExerciseType.REPETITION) }
    var dropdownOpen: Boolean by remember { mutableStateOf(false) }
    val context = LocalContext.current

//    val targetHeight = if (exerciseType == ExerciseType.REPETITION) 500.dp else 800.dp
//    val height by animateDpAsState(
//        targetValue = targetHeight,
//        animationSpec = tween(durationMillis = 500)
//    )

    AlertDialog(
        modifier = Modifier
            .height(525.dp),
        onDismissRequest = {
            viewModel.hideAddExerciseAlertDialog()
        },
        backgroundColor = MainBackground,
        text = {
            Column(
                modifier = Modifier.fillMaxHeight(0.99f),
//                verticalArrangement = Arrangement.spacedBy(2.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = stringResource(id = R.string.add_exercise),
                    style = AppTheme.typography.title,
                    color = Color.White
                )

                Spacer(modifier = Modifier.padding(vertical = 2.dp))

                OutlinedTextField(
                    value = title,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = AlphaWhiteColor,
                    ),
                    onValueChange = {
                        title = it
                    },
                    label = {
                        Text(
                            text = stringResource(id = R.string.title),
                            style = AppTheme.typography.text16sp,
                            color = AlphaWhiteColor
                        )
                    }
                )

                Spacer(modifier = Modifier.padding(vertical = 2.dp))

                DropdownMenu(
                    expanded = dropdownOpen,
                    onDismissRequest = { dropdownOpen = false }
                ) {
                    ExerciseType.values().forEach { type ->
                        DropdownMenuItem(onClick = {
                            exerciseType = type
                            dropdownOpen = false
                        }) {
                            Text(text = type.name)
                        }
                    }
                }

                Button(
                    onClick = { dropdownOpen = !dropdownOpen },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MainBackground
                    )
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.Bottom)
                            .padding(top = 4.dp),
                        text = exerciseType.name,
                        style = AppTheme.typography.text16sp,
                        color = AlphaWhiteColor
                    )
                }

                Spacer(modifier = Modifier.padding(vertical = 2.dp))

                if (exerciseType == ExerciseType.REPETITION) {
                    OutlinedTextField(
                        value = numberOfRepetitions,
                        isError = numberOfRepetitionsIsError,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = AlphaWhiteColor,
                        ),
                        onValueChange = {
                            numberOfRepetitions = it
                        },
                        label = {
                            Text(
                                text = stringResource(id = R.string.repetitions),
                                style = AppTheme.typography.text16sp,
                                color = AlphaWhiteColor
                            )
                        }
                    )
                } else if (exerciseType == ExerciseType.TIME) {
//                    OutlinedTextField(
//                        value = durationOfOneCircle,
//                        isError = durationOfOneCircleIsError,
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                        colors = TextFieldDefaults.textFieldColors(
//                            textColor = AlphaWhiteColor
//                        ),
//                        onValueChange = {
//                            durationOfOneCircle = it
//                        },
//                        label = {
//                            Text(
//                                text = stringResource(id = R.string.duration_of_one_circle),
//                                style = AppTheme.typography.text16sp,
//                                color = AlphaWhiteColor
//                            )
//                        }
//                    )


                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(start = 5.dp, bottom = 5.dp),
                                text = stringResource(id = R.string.duration_of_one_circle),
                                style = AppTheme.typography.text16sp,
                                color = AlphaWhiteColor
                            )
                        }
                        TimePicker(value = durationOfOneCircle)
                    }


                    Spacer(modifier = Modifier.height(10.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(start = 5.dp, bottom = 5.dp),
                                text = stringResource(id = R.string.rest_duration),
                                style = AppTheme.typography.text16sp,
                                color = AlphaWhiteColor
                            )
                        }
                        TimePicker(value = durationOfRest)
                    }

//                    OutlinedTextField(
//                        value = durationOfRest,
//                        isError = durationOfRestIsError,
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                        colors = TextFieldDefaults.textFieldColors(
//                            textColor = AlphaWhiteColor
//                        ),
//                        onValueChange = {
//                            durationOfRest = it
//                        },
//                        label = {
//                            Text(
//                                text = stringResource(id = R.string.rest_duration),
//                                style = AppTheme.typography.text16sp,
//                                color = AlphaWhiteColor
//                            )
//                        }
//                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = numberOfCircles,
                    isError = numberOfCirclesIsError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = AlphaWhiteColor
                    ),
                    onValueChange = {
                        numberOfCircles = it
                    },
                    label = {
                        Text(
                            text = stringResource(id = R.string.circles),
                            style = AppTheme.typography.text16sp,
                            color = AlphaWhiteColor
                        )
                    }
                )

//                Spacer(modifier = Modifier.padding(vertical = 30.dp))
            }
        },
        confirmButton = {
            Button(
                modifier = Modifier.padding(4.dp),
                onClick = {
                    if (numberOfCircles.isEmpty()) {
                        numberOfCirclesIsError = true
                    }

                    if (exerciseType == ExerciseType.REPETITION) {
                        if (numberOfRepetitions.isEmpty()) {
                            numberOfRepetitionsIsError = true
                        }

                        if (numberOfRepetitions.isNotEmpty() && numberOfCircles.isNotEmpty()) {
                            numberOfCirclesIsError = false
                            numberOfRepetitionsIsError = false

                            if (title.isEmpty()) {
                                val resources: Resources = context.resources

                                title = resources.getString(R.string.no_name)
                            }

                            viewModel.saveExerciseToExerciseList(
                                Exercise(
                                    title = title,
                                    numberOfRepetitions = numberOfRepetitions.toInt(),
                                    numberOfCircles = numberOfCircles.toInt(),
                                    exerciseType = exerciseType
                                )
                            )

                            viewModel.hideAddExerciseAlertDialog()
                        }

                    } else if (exerciseType == ExerciseType.TIME) {

                        if (numberOfCircles.isNotEmpty()) {
                            durationOfOneCircleIsError = false
                            durationOfRestIsError = false
                            numberOfCirclesIsError = false

                            if (title.isEmpty()) {
                                val resources: Resources = context.resources

                                title = resources.getString(R.string.no_name)
                            }

                            viewModel.saveExerciseToExerciseList(
                                Exercise(
                                    title = title,
                                    numberOfCircles = numberOfCircles.toInt(),
                                    durationOfOneCircle = durationOfOneCircle.value,
                                    durationOfRest = durationOfRest.value,
                                    exerciseType = exerciseType
                                )
                            )

                            viewModel.hideAddExerciseAlertDialog()
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MainBackground
                )
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .padding(top = 4.dp),
                    text = stringResource(id = R.string.add),
                    style = AppTheme.typography.text16sp,
                    color = AlphaWhiteColor
                )
                Icon(
                    Icons.Outlined.Check,
                    contentDescription = null,
                    tint = Green
                )
            }
        },
        dismissButton = {
            Button(
                modifier = Modifier.padding(4.dp),
                onClick = {
                    viewModel.hideAddExerciseAlertDialog()
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MainBackground
                )
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .padding(top = 4.dp),
                    text = stringResource(id = R.string.close),
                    style = AppTheme.typography.text16sp,
                    color = AlphaWhiteColor
                )
                Icon(
                    Icons.Outlined.Close,
                    contentDescription = null,
                    tint = Red
                )
            }
        }
    )
}

