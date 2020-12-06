package com.lekstel.aqi.filters.data.cache.mapper

import com.lekstel.aqi.base.data.mapper.BaseMapper
import com.lekstel.aqi.filters.data.cache.entity.FilterRadiusEntity
import com.lekstel.aqi.filters.data.model.FilterRadiusDTO
import javax.inject.Inject

class FilterRadiusEntityMapper @Inject constructor() : BaseMapper<FilterRadiusEntity, FilterRadiusDTO>() {

    override fun map(from: FilterRadiusEntity): FilterRadiusDTO = from.run {
        FilterRadiusDTO(
                id = id,
                radius = radius,
                selected = selected
        )
    }

    override fun reverse(to: FilterRadiusDTO): FilterRadiusEntity = to.run {
        FilterRadiusEntity(
                id = id,
                radius = radius,
                selected = selected
        )
    }
}