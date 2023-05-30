package com.srgrsj.tyb.presentation.screens.components

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.srgrsj.tyb.R
import com.srgrsj.tyb.data.firebase.auth.AccountData
import com.srgrsj.tyb.presentation.navigation.NavConstants
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {

    val defineStartDestination = if (AccountData.EMAIL == null) {
        NavConstants.SIGN_IN
    } else {
        NavConstants.WORKOUTS
    }

    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    // AnimationEffect
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(1000L)
        navController.navigate(defineStartDestination)
    }

    // Image
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        Image(painter = painterResource(id = R.drawable.logo1),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value))
    }
}