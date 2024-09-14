package com.alejandromejia.hellobuildtest.di

import android.content.Context
import androidx.room.Room
import com.alejandromejia.hellobuildtest.data.local.dao.FavoriteDao
import com.alejandromejia.hellobuildtest.data.local.dao.UsersDao
import com.alejandromejia.hellobuildtest.data.local.database.AppDatabase
import com.alejandromejia.hellobuildtest.data.local.repository.LocalFavoriteRepository
import com.alejandromejia.hellobuildtest.data.local.repository.LocalRecentSearchRepository
import com.alejandromejia.hellobuildtest.utils.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
data object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun providesUserDao(db: AppDatabase) = db.userDao()

    @Singleton
    @Provides
    fun providesFavoriteDao(db: AppDatabase) = db.favoriteDao()

    @Provides
    @Singleton
    fun providesRecentSearchRepository(
        userDao: UsersDao
    ): LocalRecentSearchRepository {
        return LocalRecentSearchRepository(userDao)
    }

    @Provides
    @Singleton
    fun providesFavoriteRepository(
        favoriteDao: FavoriteDao
    ): LocalFavoriteRepository {
        return LocalFavoriteRepository(favoriteDao)
    }
}