package com.lekstel.aqi.filters.data.source

import com.lekstel.aqi.filters.data.model.FilterMinQualityDTO
import com.lekstel.aqi.filters.data.model.FilterRadiusDTO
import kotlinx.coroutines.flow.Flow

interface IFiltersDataSource {

    fun saveFilterRadiusList(list: List<FilterRadiusDTO>)

    fun flowFilterRadiusList(): Flow<List<FilterRadiusDTO>>

    fun saveFilterMinQualityList(list: List<FilterMinQualityDTO>)

    fun flowFilterMinQualityList(): Flow<List<FilterMinQualityDTO>>
}