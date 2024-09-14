package com.alejandromejia.hellobuildtest.ui.users.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.alejandromejia.hellobuildtest.R
import com.alejandromejia.hellobuildtest.ui.main.components.EmptyScreen
import com.alejandromejia.hellobuildtest.ui.main.models.ViewModels
import com.alejandromejia.hellobuildtest.ui.search.components.RecentSearchScreenView
import com.alejandromejia.hellobuildtest.ui.search.components.SearchComponent
import com.alejandromejia.hellobuildtest.ui.users.model.UsersItemsView
import com.alejandromejia.hellobuildtest.utils.NO_USERS_FOUND
import com.alejandromejia.hellobuildtest.utils.orZeroInt

@Composable
fun UsersScreen(viewModels: ViewModels, data: UsersItemsView?, page: Int, focused: Boolean) {

    val users = data?.users?.get(page - 1).orEmpty()
    val totalPages = data?.users?.size.orZeroInt()
    val recentSearch by viewModels.searchViewModel.recentSearch.observeAsState()
    val sortedView by viewModels.usersViewModel.isSorted.observeAsState()

    Column {
        SearchComponent(viewModels = viewModels)
        when {
            focused -> RecentSearchScreenView(viewModels = viewModels, recentSearch = recentSearch)
            users.isEmpty() -> EmptyScreen(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = NO_USERS_FOUND,
                resource = R.drawable.ic_users_empty
            )

            !focused -> UsersComponent(
                users = users,
                viewModels = viewModels,
                totalPages = totalPages,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                page = page,
                sortedView = sortedView
            )
        }
    }
}

