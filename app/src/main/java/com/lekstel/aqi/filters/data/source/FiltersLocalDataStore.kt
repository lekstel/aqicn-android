package com.lekstel.aqi.filters.data.source

import com.lekstel.aqi.filters.data.cache.IFiltersCache
import com.lekstel.aqi.filters.data.cache.mapper.FilterMinQualityDtoMapper
import com.lekstel.aqi.filters.data.cache.mapper.FilterRadiusEntityMapper
import com.lekstel.aqi.filters.data.model.FilterMinQualityDTO
import com.lekstel.aqi.filters.data.model.FilterRadiusDTO
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FiltersLocalDataStore @Inject constructor(
    private val cache: IFiltersCache,
    private val radiusMapper: FilterRadiusEntityMapper,
    private val minQualityMapper: FilterMinQualityDtoMapper
) : IFiltersDataSource {

    override fun saveFilterRadiusList(list: List<FilterRadiusDTO>) =
        cache.saveFilterRadiusList(radiusMapper.reverse(list))

    override fun flowFilterRadiusList() = cache.flowFilterRadiusList().map { radiusMapper.map(it) }

    override fun saveFilterMinQualityList(list: List<FilterMinQualityDTO>) =
        cache.saveFilterMinQualityList(minQualityMapper.reverse(list))

    override fun flowFilterMinQualityList() =
        cache.flowFilterMinQualityList().map { minQualityMapper.map(it) }
}