package com.alejandromejia.hellobuildtest.data.local.mappers

import com.alejandromejia.hellobuildtest.data.local.models.search.RecentSearchEntity
import com.alejandromejia.hellobuildtest.domain.models.search.DRecentSearch
import com.alejandromejia.hellobuildtest.utils.orZeroInt

object MapperRecentSearch {

    fun domainToEntity(domain: DRecentSearch) = RecentSearchEntity(
        id = domain.id,
        searchText = domain.name
    )

    fun entityToDomain(entity: RecentSearchEntity) = DRecentSearch(
        id = entity.id.orZeroInt(),
        name = entity.searchText.orEmpty()
    )

    fun entityToDomainList(entities: List<RecentSearchEntity>): List<DRecentSearch> {
        return entities.map { entity ->
            DRecentSearch(
                id = entity.id.orZeroInt(),
                name = entity.searchText.orEmpty()
            )
        }.distinctBy { it.name }
    }


}