package com.srgrsj.tyb.presentation.screens.generatorsScreens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.srgrsj.tyb.domain.workout.model.Workout
import com.srgrsj.tyb.presentation.screens.generatorsScreens.defaultGeneratorScreen.DefaultGeneratorScreenViewModel
import com.srgrsj.tyb.presentation.theme.AlphaWhiteColor
import com.srgrsj.tyb.presentation.theme.AppTheme
import com.srgrsj.tyb.presentation.theme.Green
import com.srgrsj.tyb.presentation.theme.MainBackground
import com.srgrsj.tyb.presentation.theme.Red
import com.srgrsj.tyb.R

@Preview
@Composable
fun SaveWorkoutAlertDialog(
    viewModel: DefaultGeneratorScreenViewModel = hiltViewModel(),
    navigateToWorkoutScreen: (() -> Unit)? = null
) {
    var title: String by remember { mutableStateOf("") }
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = {
            viewModel.hideSaveWorkoutAlertDialog()
        },
        backgroundColor = MainBackground,
        text = {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(2.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.save_workout),
                    style = AppTheme.typography.title,
                    color = Color.White
                )

                Spacer(modifier = Modifier.padding(vertical = 2.dp))

                OutlinedTextField(
                    value = title,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = AlphaWhiteColor,
                    ),
                    onValueChange = {
                        title = it
                    },
                    label = {
                        Text(
                            text = stringResource(id = R.string.title),
                            style = AppTheme.typography.text16sp,
                            color = AlphaWhiteColor
                        )
                    }
                )
            }
        },
        confirmButton = {
            Button(
                modifier = Modifier.padding(4.dp),
                onClick = {

                    if (title.isEmpty()) {
                        title = context.resources.getString(R.string.no_name)
                    }

                    viewModel.saveWorkoutToRealtimeDatabase(
                        Workout(
                            title = title,
                            exerciseList = viewModel.exerciseList.value
                        )
                    )

                    viewModel.hideSaveWorkoutAlertDialog()

                    navigateToWorkoutScreen?.invoke()


                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MainBackground
                )
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .padding(top = 4.dp),
                    text = stringResource(id = R.string.add),
                    style = AppTheme.typography.text16sp,
                    color = AlphaWhiteColor
                )
                Icon(
                    Icons.Outlined.Check,
                    contentDescription = null,
                    tint = Green
                )
            }
        },
        dismissButton = {
            Button(
                modifier = Modifier.padding(4.dp),
                onClick = {
                    viewModel.hideSaveWorkoutAlertDialog()
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MainBackground
                )
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .padding(top = 4.dp),
                    text = stringResource(id = R.string.close),
                    style = AppTheme.typography.text16sp,
                    color = AlphaWhiteColor
                )
                Icon(
                    Icons.Outlined.Close,
                    contentDescription = null,
                    tint = Red
                )
            }
        }
    )
}