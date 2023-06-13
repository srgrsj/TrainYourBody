package com.srgrsj.tyb.presentation.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush

//Colors
val Red = Color(0xFFf00038)
val Green = Color(0xFF098F04)
val DarkGray = Color(0xFF5E5E5E)
val AlphaWhiteColor = Color(0xB2FFFFFF)
//val Blue = Color(0xFF03A9F4)
val Blue = Color(0xFF4C34D5)
val LightGreen = Color(0xFF8BC34A)
val Gray = Color(0xFF1F1F1F)
val WheelPicker = Color(0xFF1c1a1c)


//val Red = Color(0xFFd07387)
//val Green = Color(0xFF098F04)
//val DarkGray = Color(0xFF5E5E5E)
//val AlphaWhiteColor = Color(0xB2FFFFFF)
////val Blue = Color(0xFF03A9F4)
//val Blue = Color(0xFF354c78)
//val LightGreen = Color(0xFF8f9fae)
//val Gray = Color(0xFF1F1F1F)


val CreateWorkoutButtonGradient = Brush.horizontalGradient(listOf(Red, Blue))

val TopBarText = Color(0xE6FFFFFF)
val MainBackground = Gray
val CardsBackground = Red
val TopBarColor = Color(0xFF292929)

val GptGeneratedWorkoutCardsColor= Blue

val AuthorWorkoutsCardColor = LightGreen


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
//Dark
private val colorDarkPrimary = Color(0xff90ee90)
private val colorDarkSecondary = Color(0xff876445)
private val colorDarkTextPrimary = Color(0xffFFF9B2)
private val colorDarkTextSecondary = Color(0xFFffffff)
private val colorDarkBackground = Color(0xff0B2447)
private val colorDarkError = Color(0xFFD62222)

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


fun darkColors(
    primary: Color = colorDarkPrimary,
    secondary: Color = colorDarkSecondary,
    textPrimary: Color = colorDarkTextPrimary,
    textSecondary: Color = colorDarkTextSecondary,
    background: Color = colorDarkBackground,
    error: Color = colorDarkError
): AppColors = AppColors(
    primary = primary,
    secondary = secondary,
    textPrimary = textPrimary,
    textSecondary = textSecondary,
    background = background,
    error = error,
    isLight = false
)

val LocalColors = staticCompositionLocalOf { lightColors() }


