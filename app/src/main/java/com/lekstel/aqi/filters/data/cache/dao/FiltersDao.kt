package com.lekstel.aqi.filters.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lekstel.aqi.filters.data.cache.entity.FilterMinQualityEntity
import com.lekstel.aqi.filters.data.cache.entity.FilterRadiusEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FiltersDao {

    @Query("SELECT * FROM ${FilterRadiusEntity.TABLE_NAME}")
    fun flowFilterRadiusList(): Flow<List<FilterRadiusEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilterRadiusList(list: List<FilterRadiusEntity>)

    @Query("SELECT * FROM ${FilterMinQualityEntity.TABLE_NAME}")
    fun flowFilterMinQualityList(): Flow<List<FilterMinQualityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilterMinQualityList(list: List<FilterMinQualityEntity>)
}