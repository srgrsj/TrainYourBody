package com.srgrsj.tyb.presentation.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.srgrsj.tyb.presentation.theme.AppTheme


@Composable
fun AnimatedTextBlock(
    condition: Boolean,
    text: String
) {
    var expanded by remember { mutableStateOf(false) }
    val targetHeight = if (expanded) calculateTargetHeight(text) else 0.dp
    val height by animateDpAsState(
        targetValue = targetHeight,
        animationSpec = tween(durationMillis = 500)
    )
    val rotationState by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )

    if (condition) {
        Box(modifier = Modifier.padding(16.dp)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    onClick = { expanded = !expanded },
                    modifier = Modifier.graphicsLayer(rotationZ = rotationState)
                ) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.KeyboardArrowLeft else Icons.Default.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = text,
                        style = AppTheme.typography.text16sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    )
                }
            }
        }
    }
}


private fun calculateTargetHeight(text: String): Dp {
    val lineHeight = 4.dp

    var countOfSpaces = 0

    for (i in text) {
        if (i == ' ') {
            countOfSpaces++
        }
    }

    return lineHeight * countOfSpaces
}