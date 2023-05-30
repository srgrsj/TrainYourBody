package com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.favScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.srgrsj.tyb.R
import com.srgrsj.tyb.domain.workout.model.Workout
import com.srgrsj.tyb.domain.workout.model.WorkoutGenerationType
import com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.components.WorkoutCard
import com.srgrsj.tyb.presentation.screens.workoutPreviewScreen.WorkoutPreviewScreenType
import com.srgrsj.tyb.presentation.theme.AppTheme
import com.srgrsj.tyb.presentation.theme.MainBackground
import com.srgrsj.tyb.presentation.theme.TopBarColor
import com.srgrsj.tyb.presentation.theme.TopBarText

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavScreen(
    navigateToWorkoutPreviewScreen: ((Workout, WorkoutPreviewScreenType) -> Unit)? = null,
    viewModel: FavScreenViewModel = hiltViewModel()
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
                    text = stringResource(id = R.string.fav_screen_title),
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
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                Row {
                    Spacer(modifier = Modifier.width(15.dp))


                    Text(
                        text = stringResource(id = R.string.favorite_workouts),
                        style = AppTheme.typography.subtitle
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .horizontalScroll(ScrollState(0), true)
                ) {
                    displayingWorkoutList.forEach { workout ->
                        if (workout.isInFav == true) {
                            Spacer(modifier = Modifier.width(8.dp))
                            WorkoutCard(
                                workout = workout,
                                viewModel = viewModel,
                                navigateToWorkoutPreviewScreen = {
                                    navigateToWorkoutPreviewScreen?.invoke(
                                        workout,
                                        WorkoutPreviewScreenType.NAVIGATE
                                    )
                                })
                        }
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                Row {
                    Spacer(modifier = Modifier.width(15.dp))


                    Text(
                        text = stringResource(id = R.string.created_workouts),
                        style = AppTheme.typography.subtitle
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .horizontalScroll(ScrollState(0), true)
                ) {
                    displayingWorkoutList.forEach { workout ->
                        if (
                            workout.workoutGenerationType == WorkoutGenerationType.USER
                            || workout.workoutGenerationType == WorkoutGenerationType.GPT
                        ) {
                            Spacer(modifier = Modifier.width(8.dp))
                            WorkoutCard(
                                workout = workout,
                                viewModel = viewModel,
                                navigateToWorkoutPreviewScreen = {
                                    navigateToWorkoutPreviewScreen?.invoke(
                                        workout,
                                        WorkoutPreviewScreenType.NAVIGATE
                                    )
                                })
                        }
                    }
                }

            }
        }
    }
}
