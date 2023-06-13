package com.srgrsj.tyb.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.srgrsj.tyb.domain.workout.model.Workout
import com.srgrsj.tyb.presentation.screens.accountScreen.AccountScreen
import com.srgrsj.tyb.presentation.components.SplashScreen
import com.srgrsj.tyb.presentation.screens.generatorsScreens.defaultGeneratorScreen.GeneratorScreen
import com.srgrsj.tyb.presentation.screens.generatorsScreens.gptGeneratorScreen.GPTGeneratorScreen
import com.srgrsj.tyb.presentation.screens.generatorsScreens.selectGeneratorScreen.SelectGeneratorScreen
import com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.favScreen.FavScreen
import com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.workoutRealizationScreen.WorkoutRealizationScreen
import com.srgrsj.tyb.presentation.screens.screensUsingWorkouts.workoutScreen.WorkoutsScreen
import com.srgrsj.tyb.presentation.screens.signInScreen.SignInScreen
import com.srgrsj.tyb.presentation.screens.signUpScreen.SignUpScreen
import com.srgrsj.tyb.presentation.screens.workoutPreviewScreen.WorkoutPreviewScreen
import com.srgrsj.tyb.presentation.screens.workoutPreviewScreen.WorkoutPreviewScreenType


@OptIn(ExperimentalAnimationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = NavConstants.SPLASH
    ) {
        composable(NavConstants.SPLASH) {
            SplashScreen(navController = navHostController)
        }
        composable(NavConstants.WORKOUTS) {
            WorkoutsScreen(
                navHostController,
                { workout: Workout, type: WorkoutPreviewScreenType ->
                    navHostController.apply {
                        currentBackStackEntry?.savedStateHandle?.set("workout", workout)
                        currentBackStackEntry?.savedStateHandle?.set("type", type)
                        navigate(NavConstants.WORKOUT_PREVIEW)
                    }
                }
            )
        }
        composable(NavConstants.FAV) {
            FavScreen(
                { workout: Workout, type: WorkoutPreviewScreenType ->
                    navHostController.apply {
                        currentBackStackEntry?.savedStateHandle?.set("workout", workout)
                        currentBackStackEntry?.savedStateHandle?.set("type", type)
                        navigate(NavConstants.WORKOUT_PREVIEW)
                    }
                }
            )
        }
        composable(NavConstants.ACCOUNT) {
            AccountScreen(
                { navHostController.navigate(NavConstants.SIGN_IN) }
            )
        }
//        composable(NavConstants.GENERATOR) {
//            GeneratorScreen(
//                { navHostController.navigate(NavConstants.WORKOUTS) }
//            )
//        }
        composable(NavConstants.SIGN_UP) {
            SignUpScreen(
                navigateToWorkoutsScreen = { navHostController.navigate(NavConstants.WORKOUTS) },
                navigateToSingInScreen = { navHostController.navigate(NavConstants.SIGN_IN) }
            )
        }
        composable(NavConstants.SIGN_IN) {
            SignInScreen(
                navigateToWorkoutsScreen = { navHostController.navigate(NavConstants.WORKOUTS) },
                navigateToSignUpScreen = { navHostController.navigate(NavConstants.SIGN_UP) }
            )
        }
        composable(NavConstants.WORKOUT_REALIZATION) {
            WorkoutRealizationScreen(
                navHostController
            )
        }
//        composable(NavConstants.GPT_GENERATOR) {
//            GPTGeneratorScreen(
//                { workout: Workout, type: WorkoutPreviewScreenType ->
//                    navHostController.apply {
//                        currentBackStackEntry?.savedStateHandle?.set("workout", workout)
//                        currentBackStackEntry?.savedStateHandle?.set("type", type)
//                        navigate(NavConstants.WORKOUT_PREVIEW)
//                    }
//                }
//            )
//        }
        composable(NavConstants.WORKOUT_PREVIEW) {
            WorkoutPreviewScreen(
                navController = navHostController,
                { workout: Workout ->
                    navHostController.apply {
                        currentBackStackEntry?.savedStateHandle?.set("workout", workout)
                        navigate(NavConstants.WORKOUT_REALIZATION)
                    }
                }
            )
        }
        composable(NavConstants.SELECT_GENERATOR_SCREEN) {
            SelectGeneratorScreen(
                navController = navHostController
            ) { workout: Workout, type: WorkoutPreviewScreenType ->
                navHostController.apply {
                    currentBackStackEntry?.savedStateHandle?.set("workout", workout)
                    currentBackStackEntry?.savedStateHandle?.set("type", type)
                    navigate(NavConstants.WORKOUT_PREVIEW)
                }
            }
        }
    }

}