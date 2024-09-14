package com.alejandromejia.hellobuildtest.ui.users.model

import com.alejandromejia.hellobuildtest.domain.models.user.DUser

data class UsersItemsView(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val isEmpty: Boolean = false,
    val favoriteAction: Boolean = false,
    val users:  Map<Int, List<DUser>>
)
