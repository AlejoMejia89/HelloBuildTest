package com.alejandromejia.hellobuildtest.data.remote.utils

import com.alejandromejia.hellobuildtest.data.remote.network.Either
import com.alejandromejia.hellobuildtest.data.remote.network.Failure

inline fun <T> tryCatch(block: () -> Either<Failure, T>): Either<Failure, T> {
    return try {
        block()
    } catch (e: Exception) {
        Either.Left(Failure.ServerError)
    }
}