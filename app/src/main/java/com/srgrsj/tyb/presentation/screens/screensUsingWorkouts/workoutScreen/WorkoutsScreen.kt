package com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.workoutScreen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.srgrsj.tyb.R
import com.srgrsj.tyb.domain.workout.model.Workout
import com.srgrsj.tyb.presentation.navigation.NavConstants
import com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.components.WorkoutCard
import com.srgrsj.tyb.presentation.screens.workoutPreviewScreen.WorkoutPreviewScreenType
import com.srgrsj.tyb.presentation.theme.AppTheme
import com.srgrsj.tyb.presentation.theme.MainBackground
import com.srgrsj.tyb.presentation.theme.Red

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WorkoutsScreen(
    navController: NavController,
    navigateToWorkoutPreviewScreen: ((Workout, WorkoutPreviewScreenType) -> Unit)? = null,
    viewModel: WorkoutScreenViewModel = hiltViewModel()
) {
    val displayingWorkoutList by viewModel.workoutList.collectAsState()
    val displayingReadyWorkoutList by viewModel.readyWorkoutList.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getReadyWorkouts(context)
    }

    Scaffold(
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MainBackground)
        ) {
//            Image(
//                contentScale = ContentScale.FillWidth,
//                modifier = Modifier
//                    .fillMaxSize()
//                    .blur(5.dp),
//                painter = painterResource(id = R.drawable.testbg),
//                contentDescription = null
//            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 15.dp),
                        text = stringResource(id = R.string.all_workouts),
                        style = AppTheme.typography.subtitle
                    )

                    IconButton(
                        modifier = Modifier
                            .padding(end = 8.dp),
                        onClick = {
                            navController.navigate(NavConstants.SELECT_GENERATOR_SCREEN)
                        }
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(40.dp),
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = Red
                        )
                    }

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                        .horizontalScroll(rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.width(15.dp))

                    displayingWorkoutList.forEach { workout ->
                        WorkoutCard(
                            workout = workout,
                            viewModel = viewModel,
                            navigateToWorkoutPreviewScreen = {
                                navigateToWorkoutPreviewScreen?.invoke(
                                    workout,
                                    WorkoutPreviewScreenType.NAVIGATE
                                )
                            }
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 35.dp, start = 15.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.autors_workouts),
                        style = AppTheme.typography.subtitle
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .horizontalScroll(rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.width(15.dp))

                    displayingReadyWorkoutList.forEach { workout ->
                        WorkoutCard(
                            workout = workout,
                            viewModel = viewModel,
                            navigateToWorkoutPreviewScreen = {
                                navigateToWorkoutPreviewScreen?.invoke(
                                    workout,
                                    WorkoutPreviewScreenType.NAVIGATE
                                )
                            }
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 35.dp, start = 15.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.favorite_workouts),
                        style = AppTheme.typography.subtitle
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                        .horizontalScroll(rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.width(15.dp))

                    displayingWorkoutList.forEach { workout ->
                        if (workout.isInFav == true) {
                            WorkoutCard(
                                workout = workout,
                                viewModel = viewModel,
                                navigateToWorkoutPreviewScreen = {
                                    navigateToWorkoutPreviewScreen?.invoke(
                                        workout,
                                        WorkoutPreviewScreenType.NAVIGATE
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    }
}