package com.alejandromejia.hellobuildtest.ui.main.components.bottombar

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import com.alejandromejia.hellobuildtest.domain.utils.BottomBarScreen

@Composable
fun BottomBarIcons(screen: BottomBarScreen) {
    when (screen) {
        BottomBarScreen.Users -> {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "users"
            )
        }

        BottomBarScreen.Favorites -> {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "favorites"
            )
        }
    }
}