package com.lekstel.aqi.stations.data.cache.mapper

import com.lekstel.aqi.base.data.mapper.BaseMapper
import com.lekstel.aqi.stations.data.cache.entity.StationOnMapEntity
import com.lekstel.aqi.stations.data.model.StationOnMapDTO
import javax.inject.Inject

class StationEntityMapper @Inject constructor() :
    BaseMapper<StationOnMapEntity, StationOnMapDTO>() {

    override fun map(from: StationOnMapEntity) = from.run {
        StationOnMapDTO(
            lat = lat,
            lon = lon,
            uid = uid,
            aqi = aqi,
            station = StationOnMapDTO.StationShortInfoDTO(
                name = name,
                time = time
            )
        )
    }

    override fun reverse(to: StationOnMapDTO) = to.run {
        StationOnMapEntity(
            lat = lat,
            lon = lon,
            uid = uid,
            aqi = aqi,
            name = station.name,
            time = station.time
        )
    }
}