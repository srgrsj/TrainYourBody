package com.srgrsj.tyb.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.srgrsj.tyb.presentation.theme.AlphaWhiteColor
import com.srgrsj.tyb.presentation.theme.AppTheme

@Composable
fun CircularProgressWithTextInside(
    progress: Float,
    circleColor: Color,
    circleStrokeWidth: Dp,
    textSize: TextUnit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        CircularProgressIndicator(
            progress = progress,
            color = circleColor,
            strokeWidth = circleStrokeWidth,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
        )
        Text(
            text = "${(progress * 100).toInt()}%",
            style = AppTheme.typography.text16sp,
            color = AlphaWhiteColor,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
