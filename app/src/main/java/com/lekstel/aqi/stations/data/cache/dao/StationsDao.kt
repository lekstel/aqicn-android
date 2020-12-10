package com.lekstel.aqi.stations.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lekstel.aqi.stations.data.cache.entity.StationOnMapEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StationsDao {

    @Query("SELECT * FROM ${StationOnMapEntity.TABLE_NAME}")
    fun flowStations(): Flow<List<StationOnMapEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStations(list: List<StationOnMapEntity>)
}