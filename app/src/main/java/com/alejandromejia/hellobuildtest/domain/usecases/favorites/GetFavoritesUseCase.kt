package com.alejandromejia.hellobuildtest.domain.usecases.favorites

import com.alejandromejia.hellobuildtest.data.local.repository.LocalFavoriteRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repository: LocalFavoriteRepository
) {

    operator fun invoke() = repository.getFavorites()

}