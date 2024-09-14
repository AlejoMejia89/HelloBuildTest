package com.alejandromejia.hellobuildtest.data.local.repository

import com.alejandromejia.hellobuildtest.data.local.dao.FavoriteDao
import com.alejandromejia.hellobuildtest.data.local.mappers.MapperFavorite
import com.alejandromejia.hellobuildtest.data.local.models.favorite.FavoritesEntity
import com.alejandromejia.hellobuildtest.domain.models.user.DUser
import com.alejandromejia.hellobuildtest.domain.repository.ILocalFavoriteRepository

class LocalFavoriteRepository(
    private val favoriteDao: FavoriteDao
) : ILocalFavoriteRepository {

    private val mapper = MapperFavorite

    override fun saveFavorite(model: DUser) {
        val entity: FavoritesEntity = mapper.domainToEntity(model)
        favoriteDao.insertFavorite(entity)
    }

    override fun removeFavorite(model: DUser) {
        val entity: FavoritesEntity = mapper.domainToEntity(model)
        favoriteDao.removeFavorite(entity)
    }

    override fun getFavorites(): List<DUser> {
        return mapper.entityToDomainList(favoriteDao.getFavorites())
    }
}