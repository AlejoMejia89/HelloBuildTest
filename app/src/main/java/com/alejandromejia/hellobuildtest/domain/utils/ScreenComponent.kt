package com.alejandromejia.hellobuildtest.domain.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.alejandromejia.hellobuildtest.R
import com.alejandromejia.hellobuildtest.utils.FAVORITES
import com.alejandromejia.hellobuildtest.utils.USERS

sealed class ScreenComponent(
    val route: String,
    val title: String,
    val navigationIcon: Boolean,
    val actionIcon: Boolean,
    @get:Composable val actionIcon1: ImageVector?,
    val actionIcon2: Int?
) {

    data object Users : ScreenComponent(
        route = RoutesEnum.USERS_VIEW.name,
        title = USERS,
        navigationIcon = false,
        actionIcon = true,
        actionIcon1 = null,
        actionIcon2 = R.drawable.ic_sort
    )

    data object Favorites : ScreenComponent(
        route = RoutesEnum.FAVORITES_VIEW.name,
        title = FAVORITES,
        navigationIcon = false,
        actionIcon = false,
        actionIcon1 = null,
        actionIcon2 = null
    )


}