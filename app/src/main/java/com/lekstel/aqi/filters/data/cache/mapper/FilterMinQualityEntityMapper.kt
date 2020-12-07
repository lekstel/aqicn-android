package com.lekstel.aqi.filters.data.cache.mapper

import com.lekstel.aqi.base.data.mapper.BaseMapper
import com.lekstel.aqi.filters.data.cache.entity.FilterMinQualityEntity
import com.lekstel.aqi.filters.data.model.FilterMinQualityDTO
import javax.inject.Inject

class FilterMinQualityDtoMapper @Inject constructor() : BaseMapper<FilterMinQualityEntity, FilterMinQualityDTO>() {

    override fun map(from: FilterMinQualityEntity) = from.run {
        FilterMinQualityDTO(
                id = id,
                minQuality = minQuality,
                selected = selected
        )
    }

    override fun reverse(to: FilterMinQualityDTO) = to.run {
        FilterMinQualityEntity(
                id = id,
                minQuality = minQuality,
                selected = selected
        )
    }
}