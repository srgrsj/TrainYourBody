package com.srgrsj.tyb.presentation.screens.workoutPreviewScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.srgrsj.tyb.domain.workout.model.Workout
import com.srgrsj.tyb.presentation.screens.generatorsScreens.components.ExercisePreview
import com.srgrsj.tyb.presentation.screens.workoutPreviewScreen.components.SaveSection
import com.srgrsj.tyb.presentation.screens.workoutPreviewScreen.components.StartSection
import com.srgrsj.tyb.presentation.theme.AlphaWhiteColor
import com.srgrsj.tyb.presentation.theme.AppTheme
import com.srgrsj.tyb.presentation.theme.MainBackground
import com.srgrsj.tyb.presentation.theme.TopBarColor
import com.srgrsj.tyb.presentation.theme.TopBarText

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WorkoutPreviewScreen(
//    type: WorkoutPreviewScreenType,
    navController: NavController,
    navigateToWorkoutRealizationScreen: ((Workout) -> Unit)? = null,
    viewModel: WorkoutPreviewScreenViewModel = hiltViewModel()
) {

    val workout by remember {
        mutableStateOf(navController.previousBackStackEntry?.savedStateHandle?.get<Workout>("workout"))
    }

    val type by remember {
        mutableStateOf(
            navController.previousBackStackEntry?.savedStateHandle?.get<WorkoutPreviewScreenType>(
                "type"
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = TopBarColor,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = workout?.title.toString(),
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
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.width(25.dp))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {

                    Spacer(modifier = Modifier.height(20.dp))

                    workout?.description?.let { it1 ->
                        Text(
                            text = it1,
                            style = AppTheme.typography.subtitle,
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    workout?.exerciseList?.forEach {
                        ExercisePreview(false, exercise = it)
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    when (type) {
                        WorkoutPreviewScreenType.SAVE -> {
                            workout?.let {
                                SaveSection(
                                    workout = it,
                                    navigationController = navController
                                )
                            }
                        }

                        WorkoutPreviewScreenType.NAVIGATE -> {
                            workout?.let {
                                StartSection(
                                    workout = it,
                                    navigationController = navController,
                                    navigateToWorkoutRealizationScreen = navigateToWorkoutRealizationScreen
                                )
                            }
                        }

                        else -> {}
                    }
                }
            }
        }
    }
}