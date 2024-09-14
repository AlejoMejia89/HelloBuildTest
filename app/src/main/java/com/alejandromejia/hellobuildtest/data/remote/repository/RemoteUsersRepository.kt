package com.alejandromejia.hellobuildtest.data.remote.repository

import com.alejandromejia.hellobuildtest.data.remote.api.HelloBuildAppService
import com.alejandromejia.hellobuildtest.data.remote.models.UsersResponse
import com.alejandromejia.hellobuildtest.data.remote.network.Either
import com.alejandromejia.hellobuildtest.data.remote.network.Failure
import com.alejandromejia.hellobuildtest.data.remote.utils.tryCatch
import com.alejandromejia.hellobuildtest.domain.models.user.DUsersResponse
import com.alejandromejia.hellobuildtest.domain.repository.IRemoteUsersRepository
import com.alejandromejia.hellobuildtest.utils.UNAUTHORIZED
import org.json.JSONObject
import javax.inject.Inject

class RemoteUsersRepository @Inject constructor(
    private val appService: HelloBuildAppService
) : IRemoteUsersRepository {

    override suspend fun getUsers(): Either<Failure, DUsersResponse> {
        val res = appService.getUsers()

        return tryCatch {
            when {
                res.isSuccessful -> {
                    res.body()?.let {
                        Either.Right(UsersResponse(response = it).toDomainObject())
                    } ?: Either.Left(Failure.DataError)
                }

                res.code() == 401 && res.errorBody()?.equals(UNAUTHORIZED) == true -> {
                    val jsonObj = JSONObject(res.errorBody()!!.charStream().readText())
                    Either.Left(Failure.FeatureFailure(jsonObj.toString()))
                }

                else -> {
                    res.errorBody()?.let { errorBody ->
                        val jsonObj = JSONObject(errorBody.charStream().readText())
                        val message =
                            if (jsonObj.has("message")) jsonObj.getString("message") else jsonObj.toString()
                        Either.Left(Failure.FeatureFailure(message))
                    } ?: Either.Left(Failure.ServerError)
                }
            }
        }
    }


}