package com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.workoutRealizationScreen.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.workoutRealizationScreen.WorkoutRealizationScreenViewModel
import com.srgrsj.tyb.presentation.theme.Green
import kotlinx.coroutines.delay

@SuppressLint("UnrememberedMutableState")
@Composable
fun CountDownTimer(
    totalTime: Long,
    isRest: Boolean,
    viewModel: WorkoutRealizationScreenViewModel = hiltViewModel()
) {
    var timeLeft by remember { mutableStateOf(totalTime) }
    var progress by remember { mutableStateOf(1f) }

    Box(contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            progress = progress,
            modifier = Modifier.size(100.dp),
            color = if (isRest) Color.Blue else Green
        )
        Text(
            text = formatTime(timeLeft),
            fontWeight = FontWeight.Bold
        )
    }

    LaunchedEffect(Unit) {
        while (timeLeft > 0) {
            delay(100L)
            timeLeft -= 100L
            progress = timeLeft.toFloat() / totalTime.toFloat()
        }
        if (!isRest) {
            viewModel.currentCircle++
            viewModel.isInRest == true
        } else {
            viewModel.currentRestCircle++
            viewModel.isInRest == false
        }
    }
}

@Composable
private fun formatTime(time: Long): String {
    val seconds = (time / 1000) % 60
    val minutes = (time / (1000 * 60)) % 60
    return String.format("%02d:%02d", minutes, seconds)
}
