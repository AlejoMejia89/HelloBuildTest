package com.alejandromejia.hellobuildtest.ui.main.models

import com.alejandromejia.hellobuildtest.ui.favorites.viewmodel.FavoritesViewModel
import com.alejandromejia.hellobuildtest.ui.main.viewmodel.MainViewModel
import com.alejandromejia.hellobuildtest.ui.search.viewmodel.SearchViewModel
import com.alejandromejia.hellobuildtest.ui.users.viewmodel.UsersViewModel

data class ViewModels(
    val mainViewModel: MainViewModel,
    val usersViewModel: UsersViewModel,
    val searchViewModel: SearchViewModel,
    val favoritesViewModel: FavoritesViewModel
)
