package com.alejandromejia.hellobuildtest.data.remote.models

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.alejandromejia.hellobuildtest.domain.models.user.DUserCompany

@Immutable
@Stable
data class UserCompany(
    val name: String?,
    val catchPhrase: String?,
    val bs: String?
) {

    fun toDomainObject() = DUserCompany(
        name = name.orEmpty(),
        catchPhrase = catchPhrase.orEmpty(),
        bs = bs.orEmpty()
    )
}
