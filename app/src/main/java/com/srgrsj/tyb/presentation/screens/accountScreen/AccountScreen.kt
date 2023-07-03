package com.srgrsj.tyb.presentation.screens.accountScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.srgrsj.tyb.R
import com.srgrsj.tyb.presentation.theme.MainBackground
import com.srgrsj.tyb.data.user.AccountData.EMAIL
import com.srgrsj.tyb.presentation.theme.AlphaWhiteColor
import com.srgrsj.tyb.presentation.theme.AppTheme
import com.srgrsj.tyb.presentation.theme.Blue
import com.srgrsj.tyb.presentation.theme.Red
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AccountScreen(
    navigateToSignInScreen: (() -> Unit)? = null,
    viewModel: AccountScreenViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
//    val isApiKeyEmpty by viewModel.isApiKeyEmpty.collectAsState()
    val isApiKeyEmpty = false

    Scaffold(
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MainBackground)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Column(
                        modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

//                        Avatar(avatar = ACCOUNT_AVATAR)

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = EMAIL.toString(),
                            textAlign = TextAlign.Center,
                            style = AppTheme.typography.accountName,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                    }
                }


                if (isApiKeyEmpty) {
                    var apiKeyTextField by remember { mutableStateOf("") }

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.8f),
                        text = stringResource(id = R.string.is_api_key_empty),
                        style = AppTheme.typography.text16sp,
                        color = AlphaWhiteColor
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(0.8f),
                        value = apiKeyTextField,
                        onValueChange = {
                            apiKeyTextField = it
                        },
                        label = {
                            Text(
                                text = "API key",
                                style = AppTheme.typography.text16sp,
                                color = AlphaWhiteColor
                            )
                        },
                        textStyle = AppTheme.typography.textFieldStyle
                    )

                    Button(
                        onClick = {
//                            viewModel.setApiKey(apiKeyTextField)
                        },
                        colors = ButtonDefaults.buttonColors(Blue)
                    ) {
                        Text(
                            text = "set api key",
                            style = AppTheme.typography.text16sp
                        )
                    }

                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.3f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            scope.launch {
                            viewModel.singOutUser()
                            }
                            navigateToSignInScreen?.invoke()
                        },
                        modifier = Modifier
                            .height(50.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(30)),
                        colors = ButtonDefaults.buttonColors(Red)
                    ) {
                        Text(
                            text = stringResource(id = R.string.logout),
                            style = AppTheme.typography.text16sp
                        )
                    }
                }
            }
        }
    }
}
