package com.srgrsj.tyb.presentation.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf

//val MainBackground = Color(0xFFFFC373)


//val MainBackground = Color(0xff363438)
//
//val CardsBackground = Color(0xfff96f4d)
//
//val TopBarColor = Color(0xFF222222)
//
//val GeneratorWidgetColor = Color(0xfff96f4d)
//val GeneratorButtonBackground = Color(0xff757575)
//
//val GptGeneratorWidgetColor = Color(0xFF673AB7)
//val GptGeneratorButtonBackground = Color(0xFF03A9F4)
//
//val AvatarBorderColor = Color(0xfff96f4d)
//
//val AlphaWhiteColor = Color(0xB2FFFFFF)
//
////NavBarColors
//val NavBarColor = Color(0xFF242326)
//val SelectedNavBarItem = Color(0xfff96f4d)
//val UnselectedNavBarItem = Color(0xFFFFFFFF)
//
////Colors
//val Gray = Color(0xff757575)
//val Red = Color(0xfff96f4d)
//val Green = Color(0xFF098F04)
//val LightRed = Color(0xFFFF0000)
//val DarkGray = Color(0xFF5E5E5E)
//val LightGray = Color(0xFF8F8F8F)



val TopBarText = Color(0xFFFF5025)

val MainBackground = Color(0xFF1F1F1F)

val CardsBackground = Color(0xFFFF5025)

val TopBarColor = Color(0xFF292929)

val GeneratorWidgetColor = Color(0xFFFF5025)
val GeneratorButtonBackground = Color(0xff757575)

val GptGeneratorWidgetColor = Color(0xFF4C34D5)
val GptGeneratedWorkoutCardsBackground= Color(0xFF4C34D5)
val GptGeneratorButtonBackground = Color(0xFF03A9F4)

val AvatarBorderColor = Color(0xfff96f4d)

val AlphaWhiteColor = Color(0xB2FFFFFF)

//NavBarColors
val NavBarColor = Color(0xFF1F1F1F)
val SelectedNavBarItem = Color(0xFFFF5025)
val UnselectedNavBarItem = Color(0xFFFFFFFF)

//Colors
val Gray = Color(0xff757575)
val Red = Color(0xFFFF5025)
val Green = Color(0xFF098F04)
val LightRed = Color(0xFFFF0000)
val DarkGray = Color(0xFF5E5E5E)
val LightGray = Color(0xFF8F8F8F)




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


