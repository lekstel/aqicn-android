package com.lekstel.aqi.filters.data.cache

import com.lekstel.aqi.filters.data.cache.entity.FilterRadiusEntity
import kotlinx.coroutines.flow.Flow

interface IFiltersCache {

    fun save(list: List<FilterRadiusEntity>)

    fun flow(): Flow<List<FilterRadiusEntity>>
}