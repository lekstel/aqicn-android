package com.lekstel.aqi.filters.data

import com.lekstel.aqi.filters.data.mapper.FilterMinQualityDtoMapper
import com.lekstel.aqi.filters.data.mapper.FilterRadiusDtoMapper
import com.lekstel.aqi.filters.data.source.IFiltersDataSource
import com.lekstel.aqi.filters.domain.model.FilterMinQuality
import com.lekstel.aqi.filters.domain.model.FilterRadius
import com.lekstel.aqi.filters.domain.repository.IFiltersRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FiltersRepository @Inject constructor(
        private val localDataStore: IFiltersDataSource,
        private val radiusMapper: FilterRadiusDtoMapper,
        private val minQualityMapper: FilterMinQualityDtoMapper
) : IFiltersRepository {

    override fun saveFilterRadiusList(list: List<FilterRadius>)
            = localDataStore.saveFilterRadiusList(radiusMapper.reverse(list))

    override fun flowFilterRadiusList() = localDataStore.flowFilterRadiusList().map { radiusMapper.map(it) }

    override fun saveFilterMinQualityList(list: List<FilterMinQuality>)
            = localDataStore.saveFilterMinQualityList(minQualityMapper.reverse(list))

    override fun flowFilterMinQualityList() = localDataStore.flowFilterMinQualityList().map { minQualityMapper.map(it) }
}