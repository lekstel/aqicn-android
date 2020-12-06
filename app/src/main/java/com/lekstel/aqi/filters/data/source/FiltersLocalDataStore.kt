package com.lekstel.aqi.filters.data.source

import com.lekstel.aqi.filters.data.cache.IFiltersCache
import com.lekstel.aqi.filters.data.cache.mapper.FilterRadiusEntityMapper
import com.lekstel.aqi.filters.data.model.FilterRadiusDTO
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FiltersLocalDataStore @Inject constructor(
        private val cache: IFiltersCache,
        private val mapper: FilterRadiusEntityMapper
) : IFiltersDataSource {

    override fun saveRadiusFilters(list: List<FilterRadiusDTO>) = cache.save(mapper.reverse(list))

    override fun flowRadiusFilters() = cache.flow().map { mapper.map(it) }
}