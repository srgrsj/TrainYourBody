package com.srgrsj.tyb.presentation.screens.generatorsScreens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.srgrsj.tyb.R
import com.srgrsj.tyb.domain.exercise.model.Exercise
import com.srgrsj.tyb.domain.exercise.model.ExerciseType
import com.srgrsj.tyb.presentation.screens.generatorsScreens.defaultGeneratorScreen.DefaultGeneratorScreenViewModel
import com.srgrsj.tyb.presentation.theme.AlphaWhiteColor
import com.srgrsj.tyb.presentation.theme.AppTheme
import com.srgrsj.tyb.presentation.theme.Red
import com.srgrsj.tyb.util.DateTimeUtils
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun ExercisePreview(
    deletable: Boolean? = true,
    exercise: Exercise,
    viewModel: DefaultGeneratorScreenViewModel = hiltViewModel()
) {
    val swipeThreshold = 60.dp

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(10))
                .border(2.dp, Color.Black, RoundedCornerShape(10))
                .fillMaxWidth(0.95f)
                .background(color = Red)
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = exercise.title.toString(),
                    style = AppTheme.typography.text16sp,
                    fontWeight = FontWeight.Bold
                )

                exercise.description?.let {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = it,
                        style = AppTheme.typography.text16sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                when (exercise.exerciseType) {
                    ExerciseType.REPETITION -> {
                        Text(
                            text = "${stringResource(id = R.string.repetitions)} ${exercise.numberOfRepetitions}",
                            style = AppTheme.typography.text16sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${stringResource(id = R.string.circles)} ${exercise.numberOfCircles}",
                            style = AppTheme.typography.text16sp
                        )
                    }

                    else -> {
                        exercise.durationOfOneCircle?.let { durationOfOneCircle ->
                            Text(
                                text = "" +
                                        "${stringResource(id = R.string.duration_of_one_circle)} ${DateTimeUtils.formatDuration(exercise.durationOfOneCircle!!)}",
                                style = AppTheme.typography.text16sp
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        exercise.durationOfRest?.let {
                            Text(
                                text = "${stringResource(id = R.string.rest_duration)} ${DateTimeUtils.formatDuration(exercise.durationOfRest!!)}",
                                style = AppTheme.typography.text16sp
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${stringResource(id = R.string.circles)} ${exercise.numberOfCircles}",
                            style = AppTheme.typography.text16sp
                        )
                    }
                }
            }

            if (deletable == true) {
                val delete = SwipeAction(
                    onSwipe = { viewModel.deleteExerciseFromExerciseList(exercise = exercise) },
                    icon = {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    },
                    background = Color.Red
                )
                SwipeableActionsBox(
                    endActions = listOf(delete),
                    backgroundUntilSwipeThreshold = Color.Red,
                    swipeThreshold = swipeThreshold
                ) {
                    Spacer(modifier = Modifier.width(7.dp))
                }
            }
        }
    }
}
