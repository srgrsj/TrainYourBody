package com.srgrsj.tyb.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.srgrsj.tyb.R
import com.srgrsj.tyb.presentation.screens.mainScreen.MainScreen
import com.srgrsj.tyb.presentation.theme.Gray
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)

        setContent {
            window.statusBarColor = ContextCompat.getColor(this, R.color.gray)
            MainScreen()
        }
    }
}
