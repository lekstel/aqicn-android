package com.lekstel.aqi.filters.data.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lekstel.aqi.filters.data.cache.entity.FilterMinQualityEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class FilterMinQualityEntity(
        @PrimaryKey
        val id: Int,
        val minQuality: Int,
        val selected: Boolean
) {
    companion object {
        const val TABLE_NAME = "filter_min_quality"
    }
}