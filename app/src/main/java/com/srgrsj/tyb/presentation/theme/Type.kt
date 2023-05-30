package com.srgrsj.tyb.presentation.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.srgrsj.tyb.R


private val ubuntu = FontFamily(
    Font(R.font.arimo_medium, FontWeight.Normal)
)

data class AppTypography(
    val title: TextStyle = TextStyle(
        fontFamily = ubuntu,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        color = Color.White

    ),
    val subtitle: TextStyle = TextStyle(
        fontFamily = ubuntu,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        color = Color.White
    ),
    val name: TextStyle = TextStyle(
        fontFamily = ubuntu,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        color = Color.Black,
//        color = Color.White
    ),
    val text16sp: TextStyle = TextStyle(
        fontFamily = ubuntu,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.Black,
//        color = Color.White
    ),
    val textFieldStyle: TextStyle = TextStyle(
        fontFamily = ubuntu,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.White,
//        color = Color.White
    ),
    val time: TextStyle = TextStyle(
        fontFamily = ubuntu,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.Black,
//        color = Color.White
    ),
    val accountName: TextStyle = TextStyle(
        fontFamily = ubuntu,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        color = Color.White
    ),
    val miniText: TextStyle = TextStyle(
        fontFamily = ubuntu,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = Color.White
    )

    )


internal val LocalTypography = staticCompositionLocalOf { AppTypography() }