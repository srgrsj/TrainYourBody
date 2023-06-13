package com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.components

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.srgrsj.tyb.R
import com.srgrsj.tyb.domain.workout.model.Workout
import com.srgrsj.tyb.domain.workout.model.WorkoutGenerationType
import com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.ScreensUsingWorkoutViewModel
import com.srgrsj.tyb.presentation.theme.AppTheme
import com.srgrsj.tyb.presentation.theme.AuthorWorkoutsCardColor
import com.srgrsj.tyb.presentation.theme.CardsBackground
import com.srgrsj.tyb.presentation.theme.GptGeneratedWorkoutCardsColor


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun WorkoutCard(
    workout: Workout,
//    canBeDeleted: Boolean? = true,
    navigateToWorkoutPreviewScreen: ((Workout) -> Unit)? = null,
    viewModel: ScreensUsingWorkoutViewModel = hiltViewModel()
) {
    val showDeleteWorkoutDialogState: Boolean by viewModel.showDeleteWorkoutDialog.collectAsState()

    if (showDeleteWorkoutDialogState) {
        DeleteWorkoutAlertDialog(viewModel)
    }

    var cardBackgroundColor by remember {
        mutableStateOf(CardsBackground)
    }

    cardBackgroundColor = when (workout.workoutGenerationType) {
        WorkoutGenerationType.GPT -> {
            GptGeneratedWorkoutCardsColor
        }

        WorkoutGenerationType.USER -> {
            CardsBackground
        }

        else -> {
            AuthorWorkoutsCardColor
        }
    }

    Card(
        modifier = Modifier
            .width(250.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(10))
            .border(2.dp, Color.Black, RoundedCornerShape(10))
            .clickable {
                navigateToWorkoutPreviewScreen?.invoke(workout)
            },
        backgroundColor = cardBackgroundColor,
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.Top) {
                Spacer(modifier = Modifier.height(2.dp))

                workout.title?.let {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = it,
                        style = AppTheme.typography.name
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                workout.description?.let {
                    val maxLength = 35
                    val truncatedDescription = if (it.length > maxLength) {
                        it.substring(0, maxLength) + "..."
                    } else {
                        it
                    }

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = truncatedDescription,
                        style = AppTheme.typography.text16sp
                    )
                }
            }

            if (workout.workoutGenerationType != WorkoutGenerationType.AUTHOR) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = {
                            viewModel.workoutToDelete = workout
                            viewModel.showDeleteWorkoutAlertDialog()
                        },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Удалить",
                            tint = Color.Black
                        )
                    }

                    IconButton(
                        onClick = {
                            viewModel.changeWorkoutFavState(workout)
                        },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (workout.isInFav == true) R.drawable.baseline_star_24 else R.drawable.baseline_star_border_24
                            ),
                            contentDescription = "Добавить в избранное",
                            tint = Color.Black
                        )
                    }
                }
            }
        }
    }
}
