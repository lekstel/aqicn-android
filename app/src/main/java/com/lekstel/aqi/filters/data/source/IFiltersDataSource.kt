package com.lekstel.aqi.filters.data.source

import com.lekstel.aqi.filters.data.model.FilterRadiusDTO
import kotlinx.coroutines.flow.Flow

interface IFiltersDataSource {

    fun saveRadiusFilters(list: List<FilterRadiusDTO>)

    fun flowRadiusFilters(): Flow<List<FilterRadiusDTO>>
}