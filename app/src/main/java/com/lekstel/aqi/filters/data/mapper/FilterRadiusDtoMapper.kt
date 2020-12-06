package com.lekstel.aqi.filters.data.mapper

import com.lekstel.aqi.base.data.mapper.BaseMapper
import com.lekstel.aqi.filters.data.model.FilterRadiusDTO
import com.lekstel.aqi.filters.domain.model.FilterRadius
import javax.inject.Inject

class FilterRadiusDtoMapper @Inject constructor() : BaseMapper<FilterRadiusDTO, FilterRadius>() {

    override fun map(from: FilterRadiusDTO) = from.run {
        FilterRadius(
                id = id,
                radius = radius,
                selected = selected
        )
    }

    override fun reverse(to: FilterRadius) = to.run {
        FilterRadiusDTO(
                id = id,
                radius = radius,
                selected = selected
        )
    }
}