package com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.allWorkouts

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.navigation.NavController
import com.srgrsj.tyb.R
import com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.ScreensUsingWorkoutViewModel
import com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.components.WorkoutCard
import com.srgrsj.tyb.presentation.theme.AlphaWhiteColor
import com.srgrsj.tyb.presentation.theme.AppTheme
import com.srgrsj.tyb.presentation.theme.MainBackground
import com.srgrsj.tyb.presentation.theme.TopBarColor
import com.srgrsj.tyb.presentation.theme.TopBarText

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AllWorkouts(
    viewModel: ScreensUsingWorkoutViewModel = hiltViewModel()
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
                    text = stringResource(id = R.string.all_workouts_title),
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
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                displayingWorkoutList.forEach {
                    WorkoutCard(workout = it)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }

}