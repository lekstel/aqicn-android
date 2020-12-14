package com.lekstel.aqi.main.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lekstel.aqi.filters.data.cache.dao.FiltersDao
import com.lekstel.aqi.filters.data.cache.entity.FilterMinQualityEntity
import com.lekstel.aqi.filters.data.cache.entity.FilterRadiusEntity
import com.lekstel.aqi.stations.data.cache.dao.StationsDao
import com.lekstel.aqi.stations.data.cache.entity.StationDetailsEntity
import com.lekstel.aqi.stations.data.cache.entity.StationOnMapEntity

@Database(
    entities = [FilterRadiusEntity::class, FilterMinQualityEntity::class, StationOnMapEntity::class, StationDetailsEntity::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun filtersDao(): FiltersDao

    abstract fun stationsDao(): StationsDao

    companion object {
        val DB_NAME = "aqi_android"
    }
}
