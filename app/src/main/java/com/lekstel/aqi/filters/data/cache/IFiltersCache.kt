package com.lekstel.aqi.filters.data.cache

import com.lekstel.aqi.filters.data.cache.entity.FilterMinQualityEntity
import com.lekstel.aqi.filters.data.cache.entity.FilterRadiusEntity
import kotlinx.coroutines.flow.Flow

interface IFiltersCache {

    fun saveFilterRadiusList(list: List<FilterRadiusEntity>)

    fun flowFilterRadiusList(): Flow<List<FilterRadiusEntity>>

    fun saveFilterMinQualityList(list: List<FilterMinQualityEntity>)

    fun flowFilterMinQualityList(): Flow<List<FilterMinQualityEntity>>
}