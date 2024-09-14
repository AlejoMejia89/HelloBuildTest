package com.alejandromejia.hellobuildtest.data.local.repository

import com.alejandromejia.hellobuildtest.data.local.dao.UsersDao
import com.alejandromejia.hellobuildtest.data.local.mappers.MapperRecentSearch
import com.alejandromejia.hellobuildtest.data.local.models.search.RecentSearchEntity
import com.alejandromejia.hellobuildtest.domain.models.search.DRecentSearch
import com.alejandromejia.hellobuildtest.domain.repository.ILocalRecentSearchRepository

class LocalRecentSearchRepository(
    private val userDao: UsersDao
) : ILocalRecentSearchRepository {

    private val mapperRecentSearch = MapperRecentSearch

    override fun saveRecentSearch(model: DRecentSearch) {
        val entity: RecentSearchEntity = mapperRecentSearch.domainToEntity(model)
        userDao.insertRecentSearch(entity)
    }

    override fun removeRecentSearch(model: DRecentSearch) {
        val entity: RecentSearchEntity = mapperRecentSearch.domainToEntity(model)
        userDao.removeRecentSearch(entity)
    }

    override fun getRecentSearch(): List<DRecentSearch> {
        return mapperRecentSearch.entityToDomainList(userDao.getRecentSearch())
    }
}