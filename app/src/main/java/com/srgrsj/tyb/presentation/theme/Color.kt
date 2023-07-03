package com.srgrsj.tyb.presentation.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


//Colors

val RedOrangeGradient = Brush.horizontalGradient(listOf(Color(0xFFFF222A), Color(0xFFFF7B55)))
val BlueBlueGradient = Brush.horizontalGradient(listOf(Color(0xFF0066FC), Color(0xFF00BC93)))
val OrangeYellowGradient = Brush.horizontalGradient(listOf(Color(0xFFFFBE49), Color(0xFFF9F871)))
val Green = Color(0xFF098F04)
val Orange = Color(0xFFFF7B55)
val DarkGray = Color(0xFF5E5E5E)
val AlphaWhiteColor = Color(0xB2FFFFFF)
val Beige = Color(0xFFD57F6D)
//val Blue = Color(0xFF03A9F4)
val Blue = Color(0xFF4C34D5)
//val LightGreen = Color(0xFF8BC34A)
val Gray = Color(0xFF1F1F1F)
val Red = Color(0xFFFF222A)

val WheelPicker = Color(0xFF1c1a1c)


//val Red = Color(0xFFd07387)
//val Green = Color(0xFF098F04)
//val DarkGray = Color(0xFF5E5E5E)
//val AlphaWhiteColor = Color(0xB2FFF-FFF)
////val Blue = Color(0xFF03A9F4)
//val Blue = Color(0xFF354c78)
//val LightGreen = Color(0xFF8f9fae)
//val Gray = Color(0xFF1F1F1F)


//val CreateWorkoutButtonGradient = Brush.horizontalGradient(listOf(Red, Blue))

//val TopBarText = Color(0xE6FFF-FFF)
val MainBackground = Gray

//val TopBarColor = Color(0xFF292929)

val UserCardsBackground = RedOrangeGradient
val GptCardsBackground = BlueBlueGradient
val AuthorCardBackground = OrangeYellowGradient


//NavBarColors
val NavBarColor = Color(0xFF1F1F1F)
val SelectedNavBarItem = Red
val UnselectedNavBarItem = Color(0xFFFFFFFF)


class AppColors(
    primary: Color,
    secondary: Color,
    textPrimary: Color,
    textSecondary: Color,
    background: Color,
    error: Color,
    isLight: Boolean
) {
    var primary by mutableStateOf(primary)
        private set
    var secondary by mutableStateOf(secondary)
        private set
    var textSecondary by mutableStateOf(textSecondary)
        private set
    var textPrimary by mutableStateOf(textPrimary)
        private set
    var error by mutableStateOf(error)
        private set
    var background by mutableStateOf(background)
        private set
    var isLight by mutableStateOf(isLight)
        internal set

    fun copy(
        primary: Color = this.primary,
        secondary: Color = this.secondary,
        textPrimary: Color = this.textPrimary,
        textSecondary: Color = this.textSecondary,
        error: Color = this.error,
        background: Color = this.background,
        isLight: Boolean = this.isLight
    ): AppColors = AppColors(
        primary,
        secondary,
        textPrimary,
        textSecondary,
        error,
        background,
        isLight
    )

    fun updateColorsFrom(other: AppColors) {
        primary = other.primary
        secondary = other.secondary
        textPrimary = other.textPrimary
        textSecondary = other.textSecondary
        background = other.background
        error = other.error
    }
}


//Light
private val colorLightPrimary = Color(0xffffdab9)
private val colorLightSecondary = Color(0xFF947859)
private val colorLightTextPrimary = Color(0xff1C6758)
private val colorLightTextSecondary = Color(0xFF000000)
private val colorLightBackground = Color(0xffAEE2FF)
private val colorLightError = Color(0xFFD62222)

fun lightColors(
    primary: Color = colorLightPrimary,
    secondary: Color = colorLightSecondary,
    textPrimary: Color = colorLightTextPrimary,
    textSecondary: Color = colorLightTextSecondary,
    background: Color = colorLightBackground,
    error: Color = colorLightError
): AppColors = AppColors(
    primary = primary,
    secondary = secondary,
    textPrimary = textPrimary,
    textSecondary = textSecondary,
    background = background,
    error = error,
    isLight = true
)


val LocalColors = staticCompositionLocalOf { lightColors() }


