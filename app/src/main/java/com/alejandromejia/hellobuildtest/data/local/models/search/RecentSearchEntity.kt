package com.alejandromejia.hellobuildtest.data.local.models.search

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RecentSearchEntity")
data class RecentSearchEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int? = null,
    @ColumnInfo(name = "searchText") var searchText: String? = null
)
