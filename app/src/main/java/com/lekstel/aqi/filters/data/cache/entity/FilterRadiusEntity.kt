package com.lekstel.aqi.filters.data.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lekstel.aqi.filters.data.cache.entity.FilterRadiusEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class FilterRadiusEntity(
        @PrimaryKey
        val id: Int,
        val radius: Int,
        val selected: Boolean
) {
    companion object {
        const val TABLE_NAME = "filter_radius"
    }
}