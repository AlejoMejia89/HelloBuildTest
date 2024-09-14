package com.alejandromejia.hellobuildtest.domain.usecases.user

import com.alejandromejia.hellobuildtest.data.remote.network.Either
import com.alejandromejia.hellobuildtest.data.remote.network.Failure
import com.alejandromejia.hellobuildtest.domain.models.user.DUsersResponse
import com.alejandromejia.hellobuildtest.domain.repository.IRemoteUsersRepository
import javax.inject.Inject

class UsersUseCase @Inject constructor(
    private val repository: IRemoteUsersRepository
) {
    suspend operator fun invoke(): Either<Failure, DUsersResponse> = repository.getUsers()
}