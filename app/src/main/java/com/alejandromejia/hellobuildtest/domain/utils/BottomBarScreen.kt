package com.alejandromejia.hellobuildtest.domain.utils

import androidx.annotation.StringRes
import com.alejandromejia.hellobuildtest.R

sealed class BottomBarScreen(
    val route: String,
    @StringRes val label: Int,
    val selected: Int,
    val screenComponent: ScreenComponent

) {
    data object Users : BottomBarScreen(
        route = RoutesEnum.USERS_VIEW.name,
        label = R.string.users,
        selected = 0,
        screenComponent = ScreenComponent.Users
    )

    data object Favorites : BottomBarScreen(
        route = RoutesEnum.FAVORITES_VIEW.name,
        label = R.string.favorites,
        selected = 2,
        screenComponent = ScreenComponent.Favorites
    )

    object ScreensList {
        val screens = listOf(Users, Favorites)
    }


}