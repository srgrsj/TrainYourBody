package com.srgrsj.tyb.presentation.screens.signInScreen

import android.annotation.SuppressLint
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.srgrsj.tyb.R
import com.srgrsj.tyb.presentation.theme.AppTheme
import com.srgrsj.tyb.presentation.theme.MainBackground
import com.srgrsj.tyb.presentation.theme.Red
import com.srgrsj.tyb.presentation.theme.AlphaWhiteColor
import com.srgrsj.tyb.presentation.theme.TopBarColor
import com.srgrsj.tyb.presentation.theme.TopBarText
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignInScreen(
    navigateToWorkoutsScreen: (() -> Unit)? = null,
    navigateToSignUpScreen: (() -> Unit)? = null,
    viewModel: SignInScreenViewModel = hiltViewModel()
) {
    var passwordVisible by remember { mutableStateOf(false) }
    var eyeIconColor by remember { mutableStateOf(AlphaWhiteColor) }
    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }

    val state = viewModel.signInState.collectAsState(initial = null)
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current


    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = TopBarColor,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.signin_title),
                    style = AppTheme.typography.title,
                    color = TopBarText,
                    modifier = Modifier
                        .padding(start = 12.dp)
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MainBackground)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {

                TextField(
                    value = userEmail,
                    onValueChange = { userEmail = it },
                    textStyle = AppTheme.typography.textFieldStyle,
                    label = {
                        Text(
                            text = stringResource(id = R.string.enter_email),
                            style = AppTheme.typography.text16sp,
                            color = AlphaWhiteColor
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = userPassword,
                    onValueChange = { userPassword = it },
//                isError = isError,
                    textStyle = AppTheme.typography.textFieldStyle,
                    visualTransformation = if (passwordVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.baseline_eye_24
                            ),
                            contentDescription = "",
                            tint = eyeIconColor,
                            modifier = Modifier
                                .clickable {
                                    eyeIconColor = if (eyeIconColor == AlphaWhiteColor) {
                                        Red
                                    } else {
                                        AlphaWhiteColor
                                    }
                                    passwordVisible = !passwordVisible
                                }
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(id = R.string.enter_password),
                            style = AppTheme.typography.text16sp,
                            color = AlphaWhiteColor
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                Spacer(modifier = Modifier.height(10.dp))


                Button(
                    onClick = {
                        keyboardController?.hide()
                        scope.launch {
                            viewModel.loginUser(userEmail, userPassword)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Red)
                ) {
                    Text(
                        text = stringResource(id = R.string.sign_in)
                    )
                }


                LaunchedEffect(key1 = state.value?.isSuccess) {
                    scope.launch {
                        if (state.value?.isSuccess?.isNotEmpty() == true) {
                            val success = state.value?.isSuccess

                            Toast.makeText(context, "$success", Toast.LENGTH_LONG).show()

                            navigateToWorkoutsScreen?.invoke()
                        }
                    }
                }

                LaunchedEffect(key1 = state.value?.isError) {
                    scope.launch {
                        if (state.value?.isError?.isNotEmpty() == true) {
                            val error = state.value?.isSuccess

                            Toast.makeText(context, error, Toast.LENGTH_LONG).show()

                        }
                    }
                }


                var selected by remember { mutableStateOf(false) }
                val scale = animateFloatAsState(if (selected) 1.1f else 1.0f)

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (state.value?.isLoading == true) {
                        CircularProgressIndicator()
                    }
                }


                TextButton(
                    onClick = {
                        print("test")
                        try {
                            navigateToSignUpScreen?.invoke()
                        } catch (e: Exception) {
                            Log.d("navtest", e.message.toString())
                        }
                    },
                    modifier = Modifier
                        .scale(scale.value)
                        .pointerInteropFilter {
                            when (it.action) {
                                MotionEvent.ACTION_DOWN -> selected = true
                                MotionEvent.ACTION_UP -> selected = false
                            }
                            false
                        }
                ) {
                    Text(
                        text = stringResource(id = R.string.no_account),
                        style = AppTheme.typography.miniText,
                        color = AlphaWhiteColor,
                        textDecoration = TextDecoration.Underline,
                    )
                }

            }
        }
    }
}