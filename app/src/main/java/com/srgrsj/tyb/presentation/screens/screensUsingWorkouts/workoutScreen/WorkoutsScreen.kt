package com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.workoutScreen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.srgrsj.tyb.presentation.theme.AlphaWhiteColor
import com.srgrsj.tyb.presentation.theme.AppTheme
import com.srgrsj.tyb.presentation.theme.MainBackground
import com.srgrsj.tyb.presentation.theme.TopBarColor
import com.srgrsj.tyb.presentation.theme.TopBarText


@SuppressLint("StateFlowValueCalledInComposition", "UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WorkoutsScreen(
    navController: NavController,
    navigateToWorkoutPreviewScreen: ((Workout, WorkoutPreviewScreenType) -> Unit)? = null,
    viewModel: WorkoutScreenViewModel = hiltViewModel()
) {
    val displayingWorkoutList by viewModel.workoutList.collectAsState()

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
                    text = stringResource(id = R.string.workouts_title),
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
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                WorkoutListSection(
                    displayingWorkoutList,
                    viewModel,
                    { navController.navigate(NavConstants.ALL_WORKOUTS) },
                    navigateToWorkoutPreviewScreen
                )
                Spacer(modifier = Modifier.height(30.dp))
                CreateWorkoutSection(
                    { navController.navigate(NavConstants.GENERATOR) },
                    { navController.navigate(NavConstants.GPT_GENERATOR) }
                )


//            Button(onClick = { navigateToGptGeneratorScreen?.invoke() }) {
//                Text(text = "TestButton")
//            }
            }
        }
    }
}

@Composable
fun WorkoutListSection(
    displayingWorkoutList: List<Workout>,
    viewModel: WorkoutScreenViewModel,
    navigateToAllWorkoutsScreen: (() -> Unit)?,
    navigateToWorkoutRealizationScreen: ((Workout, WorkoutPreviewScreenType) -> Unit)?
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
//            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = stringResource(id = R.string.all_workouts),
                style = AppTheme.typography.subtitle,
                modifier = Modifier.padding(start = 15.dp)
            )

            TextButton(
                onClick = { navigateToAllWorkoutsScreen?.invoke() },
                modifier = Modifier.padding(end = 7.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.see_all),
                    style = AppTheme.typography.text16sp,
                    color = AlphaWhiteColor
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.horizontalScroll(ScrollState(0), true)
        ) {
            displayingWorkoutList.forEach { workout ->
                Spacer(modifier = Modifier.width(15.dp))
                WorkoutCard(
                    workout = workout,
                    viewModel = viewModel,
                    navigateToWorkoutPreviewScreen = {
                        navigateToWorkoutRealizationScreen?.invoke(
                            workout,
                            WorkoutPreviewScreenType.NAVIGATE
                        )
                    }
                )
            }
            Spacer(modifier = Modifier.width(15.dp))
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateWorkoutSection(
    navigateToGeneratorScreen: (() -> Unit)?,
    navigateToGptGeneratorScreen: (() -> Unit)?
) {
    Row {
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            text = stringResource(id = R.string.create_workout),
            style = AppTheme.typography.subtitle
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column() {
            GeneratorWidget(navigateToGeneratorScreen)

            Spacer(modifier = Modifier.height(20.dp))

            GptGeneratorWidget(navigateToGptGeneratorScreen)

            Spacer(modifier = Modifier.height(70.dp))
        }
    }
}
