package com.srgrsj.tyb.presentation.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Avatar(avatar: Int) {
    Image(
        painter = painterResource(avatar),
        contentDescription = "Circle Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
//            .border(5.dp, AvatarBorderColor, CircleShape)
    )
}