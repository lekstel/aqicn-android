package com.lekstel.aqi.main.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lekstel.aqi.filters.data.cache.dao.FiltersDao
import com.lekstel.aqi.filters.data.cache.entity.FilterMinQualityEntity
import com.lekstel.aqi.filters.data.cache.entity.FilterRadiusEntity

@Database(entities = [FilterRadiusEntity::class, FilterMinQualityEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filtersDao(): FiltersDao
}
