package com.srgrsj.tyb.presentation.screens.workoutPreviewScreen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.srgrsj.tyb.domain.workout.model.Workout
import com.srgrsj.tyb.presentation.navigation.NavConstants
import com.srgrsj.tyb.presentation.screens.workoutPreviewScreen.WorkoutPreviewScreenViewModel
import com.srgrsj.tyb.presentation.theme.AppTheme
import com.srgrsj.tyb.presentation.theme.DarkGray
import com.srgrsj.tyb.R

@Composable
fun SaveSection(
    workout: Workout,
    navigationController: NavController,
    viewModel: WorkoutPreviewScreenViewModel = hiltViewModel()
) {
    Column() {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
                    navigationController.navigateUp()
                },
                colors = ButtonDefaults.buttonColors(DarkGray),
                modifier = Modifier
                    .height(50.dp)
                    .width(150.dp)
                    .clip(RoundedCornerShape(20))
                    .border(2.dp, Color.Black, RoundedCornerShape(20))
            ) {
                Text(
                    text = stringResource(id = R.string.dont_save),
                    style = AppTheme.typography.text16sp,
                    color = Color.White
                )
            }

            Button(
                onClick = {
                    viewModel.saveWorkoutToRealtimeDatabase(workout = workout)
                    navigationController.navigate(NavConstants.WORKOUTS)
                },
                colors = ButtonDefaults.buttonColors(DarkGray),
                modifier = Modifier
                    .height(50.dp)
                    .width(150.dp)
                    .clip(RoundedCornerShape(20))
                    .border(2.dp, Color.Black, RoundedCornerShape(20))
            ) {
                Text(
                    text = stringResource(id = R.string.save),
                    style = AppTheme.typography.text16sp,
                    color = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}