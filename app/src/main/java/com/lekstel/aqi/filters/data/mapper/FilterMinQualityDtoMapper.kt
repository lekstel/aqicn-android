package com.lekstel.aqi.filters.data.mapper

import com.lekstel.aqi.base.data.mapper.BaseMapper
import com.lekstel.aqi.filters.data.model.FilterMinQualityDTO
import com.lekstel.aqi.filters.domain.model.FilterMinQuality
import javax.inject.Inject

class FilterMinQualityDtoMapper @Inject constructor() : BaseMapper<FilterMinQualityDTO, FilterMinQuality>() {

    override fun map(from: FilterMinQualityDTO) = from.run {
        FilterMinQuality(
                id = id,
                minQuality = minQuality,
                selected = selected
        )
    }

    override fun reverse(to: FilterMinQuality) = to.run {
        FilterMinQualityDTO(
                id = id,
                minQuality = minQuality,
                selected = selected
        )
    }
}