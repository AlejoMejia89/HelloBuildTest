package com.alejandromejia.hellobuildtest.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alejandromejia.hellobuildtest.data.local.models.favorite.FavoritesEntity

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(entity: FavoritesEntity)

    @Delete
    fun removeFavorite(entity: FavoritesEntity)

    @Query("SELECT * FROM FavoritesEntity ORDER BY name ASC")
    fun getFavorites(): List<FavoritesEntity>
}