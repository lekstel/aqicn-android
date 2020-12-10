package com.lekstel.aqi.stations.data.mapper

import com.lekstel.aqi.base.data.mapper.BaseMapper
import com.lekstel.aqi.stations.data.model.StationOnMapDTO
import com.lekstel.aqi.stations.domain.model.StationOnMap
import javax.inject.Inject

class StationOnMapDtoMapper @Inject constructor() : BaseMapper<StationOnMapDTO, StationOnMap>() {

    override fun map(from: StationOnMapDTO) = from.run {
        StationOnMap(
            uid = uid,
            lat = lat,
            lon = lon,
            aqi = aqi,
            name = station.name,
            time = station.time
        )
    }

    override fun reverse(to: StationOnMap) = to.run {
        StationOnMapDTO(
            uid = uid,
            lat = lat,
            lon = lon,
            aqi = aqi,
            station = StationOnMapDTO.StationShortInfoDTO(name, time)
        )
    }
}