package com.alejandromejia.hellobuildtest.domain.usecases.search

import com.alejandromejia.hellobuildtest.data.local.repository.LocalRecentSearchRepository
import com.alejandromejia.hellobuildtest.domain.models.search.DRecentSearch
import javax.inject.Inject

class SaveRecentSearchUseCase @Inject constructor(
    private val repository: LocalRecentSearchRepository
) {

    operator fun invoke(model: DRecentSearch) = repository.saveRecentSearch(model)

}