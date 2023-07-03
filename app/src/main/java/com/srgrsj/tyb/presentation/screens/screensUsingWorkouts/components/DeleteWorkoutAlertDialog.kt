package com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.srgrsj.tyb.R
import com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.ScreensUsingWorkoutViewModel
import com.srgrsj.tyb.presentation.theme.AlphaWhiteColor
import com.srgrsj.tyb.presentation.theme.AppTheme
import com.srgrsj.tyb.presentation.theme.Green
import com.srgrsj.tyb.presentation.theme.MainBackground
import com.srgrsj.tyb.presentation.theme.Red

@Composable
fun DeleteWorkoutAlertDialog(
    viewModel: ScreensUsingWorkoutViewModel = hiltViewModel()
) {
    AlertDialog(
        onDismissRequest = {
            viewModel.hideDeleteWorkoutAlertDialog()
        },
        backgroundColor = MainBackground,
        text = {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(2.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.delete_workout),
                    style = AppTheme.typography.title,
                    color = Color.White
                )

                Spacer(modifier = Modifier.padding(vertical = 2.dp))
            }
        },
        confirmButton = {
            Button(
                modifier = Modifier.padding(4.dp),
                onClick = {
                    viewModel.deleteWorkout(workout = viewModel.workoutToDelete!!)
//                    println(viewModel.workoutToDelete)
                    viewModel.hideDeleteWorkoutAlertDialog()

                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MainBackground
                )
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .padding(top = 4.dp),
                    text = stringResource(id = R.string.yes),
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
                    viewModel.hideDeleteWorkoutAlertDialog()
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MainBackground
                )
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .padding(top = 4.dp),
                    text = stringResource(id = R.string.no),
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