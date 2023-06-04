package com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.workoutScreen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
import com.srgrsj.tyb.presentation.screens.generatorsScreens.components.GeneratorWidget
import com.srgrsj.tyb.presentation.screens.generatorsScreens.components.GptGeneratorWidget
import com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.components.WorkoutCard
import com.srgrsj.tyb.presentation.screens.workoutPreviewScreen.WorkoutPreviewScreenType
import com.srgrsj.tyb.presentation.theme.AppTheme
import com.srgrsj.tyb.presentation.theme.MainBackground
import com.srgrsj.tyb.presentation.theme.TopBarColor
import com.srgrsj.tyb.presentation.theme.TopBarText

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
        topBar = {
            TopAppBar(
                backgroundColor = TopBarColor,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.workouts_title),
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

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, start = 15.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.all_workouts),
                        style = AppTheme.typography.subtitle
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp)
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
                        .padding(top = 15.dp)
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
                        text = stringResource(id = R.string.create_workout),
                        style = AppTheme.typography.subtitle
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                GeneratorWidget { navController.navigate(NavConstants.GENERATOR) }

                Spacer(modifier = Modifier.height(20.dp))

                GptGeneratorWidget { navController.navigate(NavConstants.GPT_GENERATOR) }

                Spacer(modifier = Modifier.height(70.dp))
            }
        }
    }
}


//@SuppressLint("StateFlowValueCalledInComposition", "UnusedMaterialScaffoldPaddingParameter")
//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun WorkoutsScreen(
//    navController: NavController,
//    navigateToWorkoutPreviewScreen: ((Workout, WorkoutPreviewScreenType) -> Unit)? = null,
//    viewModel: WorkoutScreenViewModel = hiltViewModel()
//) {
//    val displayingWorkoutList by viewModel.workoutList.collectAsState()
//    val displayingReadyWorkoutList by viewModel.readyWorkoutList.collectAsState()
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                backgroundColor = TopBarColor,
//                modifier = Modifier
//                    .fillMaxWidth()
//            ) {
//                Text(
//                    text = stringResource(id = R.string.workouts_title),
//                    style = AppTheme.typography.title,
//                    color = TopBarText,
//                    modifier = Modifier
//                        .padding(start = 12.dp)
//                )
//            }
//        }
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(MainBackground)
//        ) {
//            Column(
//                verticalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier
//                    .verticalScroll(rememberScrollState())
//            ) {
//                Spacer(modifier = Modifier.height(20.dp))
//                WorkoutListsSection(
//                    displayingWorkoutList,
//                    displayingReadyWorkoutList,
//                    viewModel,
//                    navigateToWorkoutPreviewScreen
//                )
//                Spacer(modifier = Modifier.height(30.dp))
//                CreateWorkoutSection(
//                    { navController.navigate(NavConstants.GENERATOR) },
//                    { navController.navigate(NavConstants.GPT_GENERATOR) }
//                )
//
//
////            Button(onClick = { navigateToGptGeneratorScreen?.invoke() }) {
////                Text(text = "TestButton")
////            }
//            }
//        }
//    }
//}
//
//@Composable
//fun WorkoutListsSection(
//    displayingWorkoutList: List<Workout>,
//    displayingReadyWorkoutList: List<Workout>,
//    viewModel: WorkoutScreenViewModel,
//    navigateToWorkoutRealizationScreen: ((Workout, WorkoutPreviewScreenType) -> Unit)?
//) {
//    Column {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Start,
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            Text(
//                text = stringResource(id = R.string.all_workouts),
//                style = AppTheme.typography.subtitle,
//                modifier = Modifier.padding(start = 15.dp)
//            )
//        }
//
//        Spacer(modifier = Modifier.height(10.dp))
//
//        Row(
//            modifier = Modifier.horizontalScroll(ScrollState(0), true)
//        ) {
//            displayingWorkoutList.forEach { workout ->
//                Spacer(modifier = Modifier.width(15.dp))
//                WorkoutCard(
//                    workout = workout,
//                    viewModel = viewModel,
//                    navigateToWorkoutPreviewScreen = {
//                        navigateToWorkoutRealizationScreen?.invoke(
//                            workout,
//                            WorkoutPreviewScreenType.NAVIGATE
//                        )
//                    }
//                )
//            }
//            Spacer(modifier = Modifier.width(15.dp))
//        }
//
//        Spacer(modifier = Modifier.height(30.dp))
//
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Start,
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            Text(
//                text = stringResource(id = R.string.autors_workouts),
//                style = AppTheme.typography.subtitle,
//                modifier = Modifier.padding(start = 15.dp)
//            )
//
//        }
//
//        Spacer(modifier = Modifier.height(10.dp))
//
//        Row(
//            modifier = Modifier.horizontalScroll(ScrollState(0), true)
//        ) {
//            displayingReadyWorkoutList.forEach { workout ->
//                Spacer(modifier = Modifier.width(15.dp))
//                WorkoutCard(
//
//                    workout = workout,
//
//                    viewModel = viewModel,
//
//                    navigateToWorkoutPreviewScreen = {
//                        navigateToWorkoutRealizationScreen?.invoke(
//                            workout,
//                            WorkoutPreviewScreenType.NAVIGATE
//                        )
//                    }
//                )
//            }
//
//            Spacer(modifier = Modifier.width(15.dp))
//
//        }
//    }
//}
//
//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun CreateWorkoutSection(
//    navigateToGeneratorScreen: (() -> Unit)?,
//    navigateToGptGeneratorScreen: (() -> Unit)?
//) {
//    Row {
//        Spacer(modifier = Modifier.width(15.dp))
//        Text(
//            text = stringResource(id = R.string.create_workout),
//            style = AppTheme.typography.subtitle
//        )
//    }
//    Spacer(modifier = Modifier.height(10.dp))
//    Row(
//        horizontalArrangement = Arrangement.Center,
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        Column() {
//            GeneratorWidget(navigateToGeneratorScreen)
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            GptGeneratorWidget(navigateToGptGeneratorScreen)
//
//            Spacer(modifier = Modifier.height(70.dp))
//        }
//    }
//}
