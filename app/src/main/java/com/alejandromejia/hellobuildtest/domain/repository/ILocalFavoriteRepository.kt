package com.alejandromejia.hellobuildtest.domain.repository

import com.alejandromejia.hellobuildtest.domain.models.user.DUser

interface ILocalFavoriteRepository {

    fun saveFavorite(model: DUser)

    fun removeFavorite(model: DUser)

    fun getFavorites(): List<DUser>

}