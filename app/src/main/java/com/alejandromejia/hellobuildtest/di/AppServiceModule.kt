package com.alejandromejia.hellobuildtest.di

import android.app.Application
import com.alejandromejia.hellobuildtest.data.remote.api.HelloBuildAppService
import com.alejandromejia.hellobuildtest.data.remote.repository.RemoteUsersRepository
import com.alejandromejia.hellobuildtest.domain.repository.IRemoteUsersRepository
import com.alejandromejia.hellobuildtest.utils.BASE_URL
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppServiceModule {

    @Singleton
    @Provides
    fun createRetrofitInstance(baseUrl: String, gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
            .readTimeout(35, TimeUnit.SECONDS)
        return httpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun providesGson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
        .create()

    @Provides
    @Singleton
    fun provideApiService(): HelloBuildAppService {
        val instance = createRetrofitInstance(
            baseUrl = BASE_URL,
            gson = providesGson(),
            okHttpClient = provideOkHttpClient()
        )
        return instance.create(HelloBuildAppService::class.java)
    }

    @Provides
    @Singleton
    fun providesRemoteUsersRepository(remoteUsersRepository: RemoteUsersRepository): IRemoteUsersRepository =
        remoteUsersRepository

}