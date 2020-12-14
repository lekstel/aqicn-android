package com.lekstel.aqi.stations.data.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lekstel.aqi.stations.data.cache.entity.StationDetailsEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class StationDetailsEntity(
        @PrimaryKey
        val id: Int,
        val lat: Double?,
        val lon: Double?,
        val name: String,
        val updateTime: String,
        val pm25: Double?,
        val pm10: Double?
) {
    companion object {
        const val TABLE_NAME = "station_details"
    }
}