package com.srgrsj.tyb.presentation.screens.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color

@Composable
fun AnButton(text: String, onClick: () -> Unit) {
    var isPressed by remember { mutableStateOf(false) }
    val scale: Float by animateFloatAsState(
        targetValue = if (isPressed) 0.8f else 1.0f,
        spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )
    val backgroundColor by animateColorAsState(
        targetValue = if (isPressed) Color.Gray else Color.Green
    )
    Button(
        onClick = {
            isPressed = true
            onClick()
        },
        modifier = Modifier
            .scale(scale)
            .background(backgroundColor)
    ) {
        Text(text = text, color = Color.White)
    }
}