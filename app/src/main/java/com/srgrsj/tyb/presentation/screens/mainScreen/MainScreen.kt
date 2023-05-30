package com.srgrsj.tyb.presentation.screens.mainScreen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.srgrsj.tyb.presentation.navigation.Navigation
import com.srgrsj.tyb.presentation.navigation.NavBar
import com.srgrsj.tyb.presentation.navigation.NavConstants


@Preview
@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val availableScreens = listOf(
        NavConstants.ACCOUNT,
        NavConstants.FAV,
        NavConstants.WORKOUTS,
    )
    Scaffold(
        bottomBar = {
            if (navBackStackEntry?.destination?.route in availableScreens) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                    .fillMaxWidth()
                ) {
                    NavBar(navController = navController)
                }
            }
        }
    ) {
        Navigation(navHostController = navController)
    }
}
