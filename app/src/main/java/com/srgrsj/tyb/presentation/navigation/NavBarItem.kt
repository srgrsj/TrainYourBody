package com.srgrsj.tyb.presentation.navigation

import com.srgrsj.tyb.R

sealed class NavBarItem(val title: String, val iconId: Int, val route: String) {
    object AccountScreen : NavBarItem(
        "Your account",
        R.drawable.baseline_pending_24,
        NavConstants.ACCOUNT
    )

    object FavScreen : NavBarItem(
        "My workouts",
        R.drawable.baseline_star_24,
        NavConstants.FAV
    )

    object WorkoutsScreen : NavBarItem(
        "Workouts",
        R.drawable.baseline_sports_martial_arts_24,
        NavConstants.WORKOUTS
    )
}


