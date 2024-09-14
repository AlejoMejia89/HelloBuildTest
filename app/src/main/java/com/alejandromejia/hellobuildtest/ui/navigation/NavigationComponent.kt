package com.alejandromejia.hellobuildtest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.alejandromejia.hellobuildtest.domain.utils.BottomBarScreen
import com.alejandromejia.hellobuildtest.domain.utils.ScreenComponent
import com.alejandromejia.hellobuildtest.domain.utils.ScrollStateManager
import com.alejandromejia.hellobuildtest.ui.favorites.components.FavoritesScreen
import com.alejandromejia.hellobuildtest.ui.main.components.ProgressBarApp
import com.alejandromejia.hellobuildtest.ui.main.models.ViewModels
import com.alejandromejia.hellobuildtest.ui.users.components.UsersScreen
import com.alejandromejia.hellobuildtest.utils.USER

@Composable
fun NavigationComponent(
    viewModels: ViewModels
) {
    val destination = viewModels.mainViewModel.screen.observeAsState(ScreenComponent.Users)

    when (destination.value.route) {
        BottomBarScreen.Users.route -> {
            val focused by viewModels.searchViewModel.usersFocused.observeAsState(false)
            val data by viewModels.usersViewModel.usersItemsView.observeAsState()
            val page by viewModels.usersViewModel.page.observeAsState(1)
            if (data?.loading == true) {
                ProgressBarApp()
            } else {
                UsersScreen(viewModels = viewModels, data = data, page = page, focused = focused)
            }

        }

        BottomBarScreen.Favorites.route -> {
            viewModels.favoritesViewModel.getFavorites()
            val data = viewModels.favoritesViewModel.favoriteItemsView.observeAsState().value
            if (data?.loading == true) {
                ProgressBarApp()
            } else {
                FavoritesScreen(
                    viewModels = viewModels,
                    listState = ScrollStateManager.getInstance().getScrollState(
                        key = USER
                    ),
                    data = data
                )
            }
        }

        else -> Unit

    }
}

