package com.alejandromejia.hellobuildtest.data.local.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alejandromejia.hellobuildtest.data.local.dao.FavoriteDao
import com.alejandromejia.hellobuildtest.data.local.dao.UsersDao
import com.alejandromejia.hellobuildtest.data.local.models.favorite.FavoritesEntity
import com.alejandromejia.hellobuildtest.data.local.models.search.RecentSearchEntity
import com.alejandromejia.hellobuildtest.utils.DB_NAME

@Database(
    entities = [FavoritesEntity::class, RecentSearchEntity::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun get(application: Application): AppDatabase {
            return Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                DB_NAME
            ).fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }
    }

    abstract fun userDao(): UsersDao

    abstract fun favoriteDao(): FavoriteDao


}