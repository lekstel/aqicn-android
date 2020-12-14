package com.lekstel.aqi.stations.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lekstel.aqi.stations.data.cache.entity.StationDetailsEntity
import com.lekstel.aqi.stations.data.cache.entity.StationOnMapEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StationsDao {

    @Query("SELECT * FROM ${StationOnMapEntity.TABLE_NAME}")
    fun flowStations(): Flow<List<StationOnMapEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStations(list: List<StationOnMapEntity>)

    @Query("SELECT * FROM ${StationDetailsEntity.TABLE_NAME} WHERE id = :id")
    suspend fun getStationDetails(id: Int): StationDetailsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveStationsDetails(details: StationDetailsEntity)
}