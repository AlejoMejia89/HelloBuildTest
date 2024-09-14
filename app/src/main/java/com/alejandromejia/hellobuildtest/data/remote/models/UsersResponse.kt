package com.alejandromejia.hellobuildtest.data.remote.models

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.alejandromejia.hellobuildtest.domain.models.user.DUsersResponse

@Immutable
@Stable
data class UsersResponse(
    val response: List<User>
) {

    fun toDomainObject() = DUsersResponse(
        response = response.map { it.toDomainObject() }
    )

}
