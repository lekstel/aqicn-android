package com.lekstel.aqi.filters.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lekstel.aqi.filters.data.cache.entity.FilterRadiusEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FiltersDao {

    @Query("SELECT * FROM ${FilterRadiusEntity.TABLE_NAME}")
    fun flow(): Flow<List<FilterRadiusEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<FilterRadiusEntity>)
}