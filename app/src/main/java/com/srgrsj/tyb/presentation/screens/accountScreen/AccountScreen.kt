package com.srgrsj.tyb.presentation.screens.accountScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.srgrsj.tyb.R
import com.srgrsj.tyb.presentation.theme.MainBackground
import com.srgrsj.tyb.data.firebase.auth.AccountData.ACCOUNT_AVATAR
import com.srgrsj.tyb.data.firebase.auth.AccountData.EMAIL
import com.srgrsj.tyb.presentation.screens.components.Avatar
import com.srgrsj.tyb.presentation.components.VideoDisplayer
import com.srgrsj.tyb.presentation.theme.AppTheme
import com.srgrsj.tyb.presentation.theme.Red
import com.srgrsj.tyb.presentation.theme.TopBarColor
import com.srgrsj.tyb.presentation.theme.TopBarText
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AccountScreen(
    navigateToSignInScreen: (() -> Unit)? = null,
    viewModel: AccountScreenViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = TopBarColor,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.your_account_title),
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

            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Avatar(avatar = ACCOUNT_AVATAR)

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

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
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
