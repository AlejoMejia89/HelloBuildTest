package com.alejandromejia.hellobuildtest.ui.main.components.bottombar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.alejandromejia.hellobuildtest.domain.utils.BottomBarScreen
import com.alejandromejia.hellobuildtest.domain.utils.NoRippleInteractionSource
import com.alejandromejia.hellobuildtest.ui.main.models.ViewModels

@Composable
fun BottomBarComponent(viewModels: ViewModels) {

    var index by rememberSaveable {
        mutableIntStateOf(0)
    }

    BottomNavigation(
        backgroundColor = Color.White,
    ) {

        val screens = BottomBarScreen.ScreensList.screens

        screens.forEach { screen ->
            BottomNavigationItem(
                selected = index == screen.selected,
                onClick = {
                    index = screen.selected
                    viewModels.mainViewModel.setScreen(screen.screenComponent)
                    when (index) {
                        0 -> viewModels.usersViewModel.getUsers()
                        1 -> viewModels.favoritesViewModel.getFavorites()
                    }
                },
                icon = {
                    BottomBarIcons(screen)
                },
                label = {
                    Text(text = stringResource(screen.label), color = Color.Black)
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Gray,
                interactionSource = NoRippleInteractionSource()
            )
        }
    }


}