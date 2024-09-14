package com.alejandromejia.hellobuildtest.data.remote.models

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.alejandromejia.hellobuildtest.domain.models.user.DUser
import com.alejandromejia.hellobuildtest.utils.orZeroInt

@Immutable
@Stable
data class User(
    val id: Int?,
    val name: String?,
    val userName: String?,
    val email: String?,
    val address: UserAddress,
    val phone: String?,
    val website: String?,
    val company: UserCompany
) {

    fun toDomainObject() = DUser(
        id = id.orZeroInt(),
        name = name.orEmpty(),
        userName = userName.orEmpty(),
        email = email.orEmpty(),
        address = address.toDomainObject(),
        phone = phone.orEmpty(),
        website = website.orEmpty(),
        company = company.toDomainObject(),
        iconColor = 0,
        isFavorite = false
    )

}
