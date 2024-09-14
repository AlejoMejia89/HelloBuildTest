package com.alejandromejia.hellobuildtest.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alejandromejia.hellobuildtest.data.local.models.search.RecentSearchEntity

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecentSearch(entity: RecentSearchEntity)

    @Delete
    fun removeRecentSearch(entity: RecentSearchEntity)

    @Query("SELECT DISTINCT * FROM RecentSearchEntity ORDER BY id DESC LIMIT 10")
    fun getRecentSearch(): List<RecentSearchEntity>

}