package com.alejandromejia.hellobuildtest.domain.usecases.search

import com.alejandromejia.hellobuildtest.data.local.repository.LocalRecentSearchRepository
import javax.inject.Inject

class GetRecentSearchUseCase @Inject constructor(
    private val repository: LocalRecentSearchRepository
) {

    operator fun invoke() = repository.getRecentSearch()

}