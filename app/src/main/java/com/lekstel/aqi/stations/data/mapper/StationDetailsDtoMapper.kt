package com.lekstel.aqi.stations.data.mapper

import com.lekstel.aqi.base.data.mapper.BaseMapper
import com.lekstel.aqi.stations.data.model.StationDetailsDTO
import com.lekstel.aqi.stations.domain.model.StationDetails
import javax.inject.Inject

class StationDetailsDtoMapper @Inject constructor() : BaseMapper<StationDetailsDTO, StationDetails>() {

    override fun map(from: StationDetailsDTO) = from.run {
        StationDetails(
                id = idx,
                name = city?.name ?: "",
                lat = city?.geo?.getOrNull(0),
                lon = city?.geo?.getOrNull(1),
                updateTime = time?.iso ?: "",
                pm25 = iaqi?.pm25?.v,
                pm10 = iaqi?.pm10?.v
        )
    }

    override fun reverse(to: StationDetails) = to.run {
        StationDetailsDTO(
                idx = id,
                city = StationDetailsDTO.CityDTO(listOf(lat, lon), name),
                time = StationDetailsDTO.TimeDTO(updateTime),
                iaqi = StationDetailsDTO.IaqiDTO(pm25 = StationDetailsDTO.IaqiDTO.Pm25DTO(pm25), pm10 = StationDetailsDTO.IaqiDTO.Pm10DTO(pm10))
        )
    }
}