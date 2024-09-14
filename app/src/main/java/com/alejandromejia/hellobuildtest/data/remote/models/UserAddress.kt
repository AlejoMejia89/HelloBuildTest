package com.alejandromejia.hellobuildtest.data.remote.models

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.alejandromejia.hellobuildtest.domain.models.user.DUserAddress

@Immutable
@Stable
data class UserAddress(
    val street: String?,
    val suite: String?,
    val city: String?,
    val zipcode: String?
) {

    fun toDomainObject() = DUserAddress(
        street = street.orEmpty(),
        suite = suite.orEmpty(),
        city = city.orEmpty(),
        zipcode = zipcode.orEmpty()
    )

}
