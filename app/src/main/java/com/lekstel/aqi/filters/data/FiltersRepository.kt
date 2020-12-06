package com.lekstel.aqi.filters.data

import com.lekstel.aqi.filters.data.mapper.FilterRadiusDtoMapper
import com.lekstel.aqi.filters.data.source.IFiltersDataSource
import com.lekstel.aqi.filters.domain.model.FilterRadius
import com.lekstel.aqi.filters.domain.repository.IFiltersRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FiltersRepository @Inject constructor(
        private val localDataStore: IFiltersDataSource,
        private val mapper: FilterRadiusDtoMapper
) : IFiltersRepository {

    override fun save(list: List<FilterRadius>) {
        localDataStore.saveRadiusFilters(mapper.reverse(list))
    }

    override fun flow() = localDataStore.flowRadiusFilters().map { mapper.map(it) }
}