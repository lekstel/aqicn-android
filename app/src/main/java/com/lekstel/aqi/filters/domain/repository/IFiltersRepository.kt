package com.lekstel.aqi.filters.domain.repository

import com.lekstel.aqi.filters.domain.model.FilterRadius
import kotlinx.coroutines.flow.Flow

interface IFiltersRepository {

    fun save(list: List<FilterRadius>)

    fun flow(): Flow<List<FilterRadius>>
}