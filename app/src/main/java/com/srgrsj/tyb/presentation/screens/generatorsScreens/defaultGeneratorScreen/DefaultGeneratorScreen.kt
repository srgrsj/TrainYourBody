package com.srgrsj.tyb.presentation.screens.generatorsScreens.defaultGeneratorScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.srgrsj.tyb.presentation.screens.generatorsScreens.components.ExercisePreview
import com.srgrsj.tyb.presentation.screens.generatorsScreens.components.AddExerciseAlertDialog
import com.srgrsj.tyb.presentation.screens.generatorsScreens.components.SaveWorkoutAlertDialog
import com.srgrsj.tyb.presentation.theme.AppTheme
import com.srgrsj.tyb.R
import com.srgrsj.tyb.presentation.theme.MainBackground
import com.srgrsj.tyb.presentation.theme.Red
import com.srgrsj.tyb.presentation.theme.TopBarColor
import com.srgrsj.tyb.presentation.theme.TopBarText

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GeneratorScreen(
    navigateToWorkoutScreen: (() -> Unit)? = null,
    viewModel: DefaultGeneratorScreenViewModel = hiltViewModel()
) {

    val showAddExerciseDialogState: Boolean by viewModel.showAddExerciseDialog.collectAsState()
    if (showAddExerciseDialogState) {
        AddExerciseAlertDialog(viewModel)
    }

    val showSaveWorkoutDialogState: Boolean by viewModel.showSaveWorkoutDialog.collectAsState()
    if (showSaveWorkoutDialogState) {
        SaveWorkoutAlertDialog(viewModel, navigateToWorkoutScreen)
    }

    val displayingExerciseList by viewModel.exerciseList.collectAsState()


    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.showAddExerciseAlertDialog()
                },
                backgroundColor = Red,
                elevation = FloatingActionButtonDefaults.elevation(),
            ) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        },
        topBar = {
            TopAppBar(
                backgroundColor = TopBarColor,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.create_workout),
                    style = AppTheme.typography.title,
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

            Column {
                Spacer(modifier = Modifier.height(20.dp))

                Row() {
                    Spacer(modifier = Modifier.width(5.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,

                        ) {
                        Icon(
                            Icons.Filled.Check,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .height(40.dp)
                                .width(40.dp)
                                .clickable {
                                    viewModel.showSaveWorkoutAlertDialog()
                                }
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))


                Column(
                    modifier = Modifier
                        .verticalScroll(ScrollState(0), true)
                ) {
                    displayingExerciseList.forEach {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            ExercisePreview(exercise = it, viewModel = viewModel)
                        }

                        Spacer(modifier = Modifier.height(15.dp))

                    }
                }

            }
        }
    }
}