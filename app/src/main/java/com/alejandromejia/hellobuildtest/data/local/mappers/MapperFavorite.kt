package com.alejandromejia.hellobuildtest.data.local.mappers

import com.alejandromejia.hellobuildtest.data.local.models.favorite.FavoritesEntity
import com.alejandromejia.hellobuildtest.domain.models.user.DUser
import com.alejandromejia.hellobuildtest.domain.models.user.DUserAddress
import com.alejandromejia.hellobuildtest.domain.models.user.DUserCompany
import com.alejandromejia.hellobuildtest.utils.EMPTY_STRING
import com.alejandromejia.hellobuildtest.utils.getResourceColor
import com.alejandromejia.hellobuildtest.utils.orZeroInt

object MapperFavorite {

    fun domainToEntity(domain: DUser) = FavoritesEntity(
        id = domain.id,
        name = domain.name,
        userName = domain.userName,
        email = domain.email,
        address = domain.address.street,
        phone = domain.phone,
        website = domain.website,
        city = domain.address.city
    )

    fun entityToDomain(entity: FavoritesEntity) = DUser(
        id = entity.id.orZeroInt(),
        name = entity.name.orEmpty(),
        userName = entity.userName.orEmpty(),
        email = entity.email.orEmpty(),
        address = DUserAddress(
            street = entity.address.orEmpty(),
            suite = EMPTY_STRING,
            city = entity.city.orEmpty(),
            zipcode = EMPTY_STRING
        ),
        phone = entity.phone.orEmpty(),
        website = entity.website.orEmpty(),
        company = DUserCompany(
            name = EMPTY_STRING,
            catchPhrase = EMPTY_STRING,
            bs = EMPTY_STRING
        ),
        iconColor = getResourceColor(entity.id.orZeroInt()),
        isFavorite = true
    )

    fun entityToDomainList(entities: List<FavoritesEntity>): List<DUser> {
        return entities.map { entity ->
            DUser(
                id = entity.id.orZeroInt(),
                name = entity.name.orEmpty(),
                userName = entity.userName.orEmpty(),
                email = entity.email.orEmpty(),
                address = DUserAddress(
                    street = entity.address.orEmpty(),
                    suite = EMPTY_STRING,
                    city = entity.city.orEmpty(),
                    zipcode = EMPTY_STRING
                ),
                phone = entity.phone.orEmpty(),
                website = entity.website.orEmpty(),
                company = DUserCompany(
                    name = EMPTY_STRING,
                    catchPhrase = EMPTY_STRING,
                    bs = EMPTY_STRING
                ),
                iconColor = getResourceColor(entity.id.orZeroInt()),
                isFavorite = true
            )
        }.distinctBy { it.id }
    }
}