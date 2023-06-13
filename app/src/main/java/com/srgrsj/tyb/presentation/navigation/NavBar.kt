package com.srgrsj.tyb.presentation.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.srgrsj.tyb.R
import com.srgrsj.tyb.presentation.theme.NavBarColor
import com.srgrsj.tyb.presentation.theme.SelectedNavBarItem
import com.srgrsj.tyb.presentation.theme.UnselectedNavBarItem

@Composable
fun NavBar(
    navController: NavController
) {
    val listItems = listOf(
        NavBarItem(
            stringResource(id = R.string.workouts),
            R.drawable.baseline_sports_martial_arts_24,
            NavConstants.WORKOUTS
        ),
        NavBarItem(
            stringResource(id = R.string.account),
            R.drawable.baseline_account_circle_24,
            NavConstants.ACCOUNT
        )
    )

    BottomNavigation(
        backgroundColor = NavBarColor,
        modifier = Modifier
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRout = backStackEntry?.destination?.route

        listItems.forEach() { item ->
            BottomNavigationItem(
                selected = currentRout == item.route,
                onClick = {
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = "Icon"
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 9.sp
                    )
                },
                selectedContentColor = SelectedNavBarItem,
                unselectedContentColor = UnselectedNavBarItem
            )
        }
    }
}