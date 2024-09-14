package com.alejandromejia.hellobuildtest.domain.usecases.favorites

import com.alejandromejia.hellobuildtest.data.local.repository.LocalFavoriteRepository
import com.alejandromejia.hellobuildtest.domain.models.user.DUser
import javax.inject.Inject

class SaveFavoriteUseCase @Inject constructor(
    private val repository: LocalFavoriteRepository
) {

    operator fun invoke(model: DUser) = repository.saveFavorite(model)

}