package com.alejandromejia.hellobuildtest.ui.favorites.model

import com.alejandromejia.hellobuildtest.domain.models.user.DUser

data class FavoriteItemsView(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val isEmpty: Boolean = false,
    val users:  List<DUser>
)
