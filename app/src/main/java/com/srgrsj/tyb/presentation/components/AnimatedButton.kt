package com.srgrsj.tyb.presentation.components

import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInteropFilter

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AnimatedButton(
    onClick: (() -> Unit)? = null,
    color: Color,
    modifier: Modifier,
    shape: Shape,
    content: @Composable RowScope.() -> Unit,

) {
    val selected = remember { mutableStateOf(false) }
    val scale = animateFloatAsState(if (selected.value) 1.1f else 1.0f)

    OutlinedButton(
        onClick = { onClick?.invoke() },
        shape = shape,
        colors = ButtonDefaults.buttonColors(color),
        modifier = Modifier
            .scale(scale.value)
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        selected.value = true
                    }

                    MotionEvent.ACTION_UP -> {
                        selected.value = false
                    }
                }
                true
            }

    ) {
        Row(
            content = content
        )
    }
}
