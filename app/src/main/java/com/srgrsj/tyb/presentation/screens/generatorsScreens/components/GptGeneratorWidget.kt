package com.srgrsj.tyb.presentation.screens.generatorsScreens.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.srgrsj.tyb.R
import com.srgrsj.tyb.presentation.theme.AppTheme
import com.srgrsj.tyb.presentation.theme.GptGeneratorButtonBackground
import com.srgrsj.tyb.presentation.theme.GptGeneratorWidgetColor

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GptGeneratorWidget(
    navigateToGptGeneratorScreen: (() -> Unit)? = null
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
//            .width(400.dp)
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth(0.93f)
//                .height(200.dp)
                .fillMaxWidth(0.6f)
                .clip(RoundedCornerShape(10))
                .background(GptGeneratorWidgetColor)
                .border(2.dp, Color.Black, RoundedCornerShape(10))
        ) {
            Spacer(modifier = Modifier.height(15.dp))

            Row {
                Text(
                    text = stringResource(id = R.string.workouts_description),
                    textAlign = TextAlign.Center,
                    style = AppTheme.typography.text20sp,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .height(50.dp),
                horizontalArrangement = Arrangement.End
            ) {

                Button(
                    onClick = {
                        navigateToGptGeneratorScreen?.invoke()
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = GptGeneratorButtonBackground),
                    modifier = Modifier
                        .height(50.dp)
                        .width(100.dp)
                        .clip(RoundedCornerShape(30))
                ) {
                    Text(
                        text = stringResource(id = R.string.start),
                        style = AppTheme.typography.text16sp
                    )
                }

                Spacer(modifier = Modifier.width(15.dp))
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
            )

        }
    }
}
