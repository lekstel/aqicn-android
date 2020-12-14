package com.lekstel.aqi.stations.data.cache.mapper

import com.lekstel.aqi.base.data.mapper.BaseMapper
import com.lekstel.aqi.stations.data.cache.entity.StationDetailsEntity
import com.lekstel.aqi.stations.data.model.StationDetailsDTO
import javax.inject.Inject

class StationDetailsEntityMapper @Inject constructor() : BaseMapper<StationDetailsEntity, StationDetailsDTO>() {

    override fun map(from: StationDetailsEntity) = from.run {
        StationDetailsDTO(
                idx = id,
                city = StationDetailsDTO.CityDTO(listOf(lat, lon), name),
                time = StationDetailsDTO.TimeDTO(updateTime),
                iaqi = StationDetailsDTO.IaqiDTO(pm25 = StationDetailsDTO.IaqiDTO.Pm25DTO(pm25), pm10 = StationDetailsDTO.IaqiDTO.Pm10DTO(pm10))
        )
    }

    override fun reverse(to: StationDetailsDTO) = to.run {
        StationDetailsEntity(
                id = idx,
                name = city?.name ?: "",
                lat = city?.geo?.getOrNull(0),
                lon = city?.geo?.getOrNull(1),
                updateTime = time?.iso ?: "",
                pm25 = iaqi?.pm25?.v,
                pm10 = iaqi?.pm10?.v
        )
    }
}