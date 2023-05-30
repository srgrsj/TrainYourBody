package com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.components

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.material.ExperimentalMaterialApi
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
import androidx.compose.runtime.rememberCoroutineScope
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
import com.srgrsj.tyb.presentation.theme.CardsBackground
import com.srgrsj.tyb.presentation.theme.GptGeneratedWorkoutCardsBackground


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun WorkoutCard(
    workout: Workout,
    canBeDeleted: Boolean? = true,
    navigateToWorkoutPreviewScreen: ((Workout) -> Unit)? = null,
    viewModel: ScreensUsingWorkoutViewModel = hiltViewModel()
) {
    val showAddExerciseDialogState: Boolean by viewModel.showDeleteWorkoutDialog.collectAsState()
    if (showAddExerciseDialogState) {
        DeleteWorkoutAlertDialog(workout, viewModel)
    }

    var cardBackgroundColor by remember {
        mutableStateOf(CardsBackground)
    }
    when (workout.workoutGenerationType) {
        WorkoutGenerationType.GPT -> {
            cardBackgroundColor = GptGeneratedWorkoutCardsBackground
        }

        WorkoutGenerationType.USER -> {
            cardBackgroundColor = CardsBackground
        }

        else -> {}
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

        if (workout.title == "legend") {
            Image(
                modifier = Modifier
                    .clip(RoundedCornerShape(10)),
                painter = painterResource(id = R.drawable.legend),
                contentDescription = null
            )

            if (canBeDeleted == true) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
//                Spacer(modifier = Modifier.width(10.dp))

                    IconButton(
                        onClick = {
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

//                Spacer(modifier = Modifier.width(10.dp))
                }
            }
        } else {
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
                        val maxLength = 40 // Максимальная длина описания
                        val truncatedDescription = if (it.length > maxLength) {
                            it.substring(
                                0,
                                maxLength
                            ) + "..." // Обрезаем описание и добавляем многоточие
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

                if (canBeDeleted == true) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
//                Spacer(modifier = Modifier.width(10.dp))

                        IconButton(
                            onClick = {
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

//                Spacer(modifier = Modifier.width(10.dp))
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
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

//                Spacer(modifier = Modifier.width(10.dp))
                    }
                }
            }
        }
    }
}


//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun WorkoutCard(
//    workout: Workout,
//    canBeDeleted: Boolean? = true,
//    navigateToWorkoutPreviewScreen: (() -> Unit)? = null,
//    viewModel: ScreensUsingWorkoutViewModel = hiltViewModel()
//) {
//    val showAddExerciseDialogState: Boolean by viewModel.showDeleteWorkoutDialog.collectAsState()
//    if (showAddExerciseDialogState) {
//        DeleteWorkoutAlertDialog(workout, viewModel)
//    }
//
//    Box(
//        modifier = Modifier
//            .width(250.dp)
//            .height(150.dp)
//            .clip(RoundedCornerShape(20))
//            .border(2.dp, Color.Black, RoundedCornerShape(20))
//            .background(CardsBackground)
//            .combinedClickable(
//                onLongClick = {
//                    if (canBeDeleted == true) {
//                        viewModel.showDeleteWorkoutAlertDialog()
//                    }
//                }
//            ) {}
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Bottom,
//            modifier = Modifier
//                .fillMaxSize()
//        ) {
//            Column(
//                verticalArrangement = Arrangement.Top,
//                modifier = Modifier
//                    .height(100.dp)
//            ) {
//                Spacer(modifier = Modifier.height(2.dp))
//
//                workout.title?.let {
//                    Text(
//                        modifier = Modifier
//                            .fillMaxWidth(),
//                        textAlign = TextAlign.Center,
//                        text = it,
//                        style = AppTheme.typography.name
//                    )
//                }
//
//                Spacer(modifier = Modifier.width(10.dp))
//
//
//                workout.description?.let {
//                    Text(
//                        modifier = Modifier
//                            .fillMaxWidth(),
//                        textAlign = TextAlign.Center,
//                        text = it,
//                        style = AppTheme.typography.text16sp
//                    )
//                }
//            }
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth(0.90f)
//                    .height(100.dp),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
//
//            ) {
//
//                TextButton(
//                    onClick = { navigateToWorkoutPreviewScreen?.invoke() }
//                ) {
//                    Text(
//                        text = "Начать",
//                        style = AppTheme.typography.subtitle
//                    )
//
//                    Icon(
//                        Icons.Outlined.PlayArrow,
//                        null,
//                        tint = Green
//                    )
//                }
//
//                val defaultStar = R.drawable.baseline_star_24
//                val borderStar = R.drawable.baseline_star_border_24
//
//                val starIcon = remember {
//                    if (workout.isInFav == true) {
//                        mutableStateOf(defaultStar)
//                    } else {
//                        mutableStateOf(borderStar)
//                    }
//                }
//
//
//                val isEnabled = remember { mutableStateOf(true) }
//                val isRotated = remember { mutableStateOf(false) }
//
//                val angle: Float by animateFloatAsState(
//                    targetValue = if (isRotated.value) 360F else 0F,
//                    animationSpec = tween(
//                        durationMillis = 200,
//                        easing = FastOutSlowInEasing
//                    ),
//                    finishedListener = {
//                        isEnabled.value = true
//                    }
//                )
//
//                Icon(
//                    painter = painterResource(starIcon.value),
//                    contentDescription = "Add to Fav",
//                    modifier = Modifier
//                        .rotate(angle)
//                        .clickable {
//                            viewModel.changeWorkoutFavState(workout)
//                            when (starIcon.value) {
//                                defaultStar -> starIcon.value = borderStar
//                                borderStar -> starIcon.value = defaultStar
//                            }
//
//                            isRotated.value = !isRotated.value
//                            isEnabled.value = false
//                        }
//
//                )
//            }
//
//            Spacer(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(10.dp)
//            )
//        }
//    }
//}