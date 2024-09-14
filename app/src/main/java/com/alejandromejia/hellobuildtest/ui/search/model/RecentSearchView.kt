package com.alejandromejia.hellobuildtest.ui.search.model

import com.alejandromejia.hellobuildtest.domain.models.search.DRecentSearch

data class RecentSearchView(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val isEmpty: Boolean = false,
    val recentSearch: List<DRecentSearch>? = emptyList(),
)
