package com.alejandromejia.hellobuildtest.domain.repository

import com.alejandromejia.hellobuildtest.data.remote.network.Either
import com.alejandromejia.hellobuildtest.data.remote.network.Failure
import com.alejandromejia.hellobuildtest.domain.models.user.DUsersResponse

interface IRemoteUsersRepository {

    suspend fun getUsers(): Either<Failure, DUsersResponse>

}