package com.srgrsj.tyb.presentation.screens.generatorsScreens.selectGeneratorScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.srgrsj.tyb.R
import com.srgrsj.tyb.domain.workout.model.Workout
import com.srgrsj.tyb.presentation.navigation.NavConstants
import com.srgrsj.tyb.presentation.screens.generatorsScreens.defaultGeneratorScreen.GeneratorScreen
import com.srgrsj.tyb.presentation.screens.generatorsScreens.gptGeneratorScreen.GPTGeneratorScreen
import com.srgrsj.tyb.presentation.screens.workoutPreviewScreen.WorkoutPreviewScreenType
import com.srgrsj.tyb.presentation.theme.Blue
import com.srgrsj.tyb.presentation.theme.MainBackground
import com.srgrsj.tyb.presentation.theme.Red

@Composable
fun SelectGeneratorScreen(
    navController: NavController,
    navigateToWorkoutPreviewScreen: ((Workout, WorkoutPreviewScreenType) -> Unit)? = null,
) {
    var switchStatus by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackground)
    ) {
        Box {
            if (!switchStatus) {
                GeneratorScreen({ navController.navigate(NavConstants.WORKOUTS) })
            } else {
                GPTGeneratorScreen(navigateToWorkoutPreviewScreen)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
//                    .clip(RoundedCornerShape(20))
//                    .background(Blue)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .height(60.dp)
                        .width(90.dp)
                ) {
                    Switch(
                        modifier = Modifier
                            .size(50.dp),
                        checked = switchStatus,
                        onCheckedChange = {
                            switchStatus = !switchStatus
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Blue,
                            checkedTrackColor = Blue,
                            uncheckedThumbColor = Red,
                            uncheckedTrackColor = Red,
                        )
                    )

                    Image(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20))
                            .size(40.dp),
                        painter = painterResource(
                            id =
                            if (switchStatus) R.drawable.bluegptlogo else R.drawable.graygptlogo
                        ),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

