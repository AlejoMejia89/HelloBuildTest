package com.alejandromejia.hellobuildtest.data.remote.network

sealed class Failure {
    data object NetworkConnection : Failure()
    data object DataError : Failure()
    data object ServerError : Failure()

    data class FeatureFailure(
        val errorBody: String
    ) : Failure()
}