package com.lekstel.aqi.stations.data.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lekstel.aqi.stations.data.cache.entity.StationOnMapEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class StationOnMapEntity(
    @PrimaryKey
    val uid: Int,
    val lat: Double,
    val lon: Double,
    val aqi: String,
    val name: String,
    val time: String
) {
    companion object {
        const val TABLE_NAME = "stations"
    }
}