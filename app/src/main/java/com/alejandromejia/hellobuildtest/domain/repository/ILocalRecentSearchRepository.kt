package com.alejandromejia.hellobuildtest.domain.repository

import com.alejandromejia.hellobuildtest.domain.models.search.DRecentSearch

interface ILocalRecentSearchRepository {

    fun saveRecentSearch(model: DRecentSearch)

    fun removeRecentSearch(model: DRecentSearch)

    fun getRecentSearch(): List<DRecentSearch>

}