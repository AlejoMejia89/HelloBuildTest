package com.alejandromejia.hellobuildtest.data.remote.api

import com.alejandromejia.hellobuildtest.data.remote.models.User
import com.alejandromejia.hellobuildtest.utils.GET_USERS
import retrofit2.Response
import retrofit2.http.GET

interface HelloBuildAppService {

    @GET(GET_USERS)
    suspend fun getUsers(): Response<List<User>>


}