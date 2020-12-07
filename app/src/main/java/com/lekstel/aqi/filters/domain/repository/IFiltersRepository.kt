package com.lekstel.aqi.filters.domain.repository

import com.lekstel.aqi.filters.domain.model.FilterMinQuality
import com.lekstel.aqi.filters.domain.model.FilterRadius
import kotlinx.coroutines.flow.Flow

interface IFiltersRepository {

    fun saveFilterRadiusList(list: List<FilterRadius>)

    fun flowFilterRadiusList(): Flow<List<FilterRadius>>

    fun saveFilterMinQualityList(list: List<FilterMinQuality>)

    fun flowFilterMinQualityList(): Flow<List<FilterMinQuality>>
}