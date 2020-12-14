package com.lekstel.aqi.stations.data.source

import com.google.android.gms.maps.model.LatLng
import com.lekstel.aqi.stations.data.cache.IStationsCache
import com.lekstel.aqi.stations.data.cache.mapper.StationDetailsEntityMapper
import com.lekstel.aqi.stations.data.cache.mapper.StationEntityMapper
import com.lekstel.aqi.stations.data.model.StationDetailsDTO
import com.lekstel.aqi.stations.data.model.StationOnMapDTO
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StationsLocalDataStore @Inject constructor(
        private val cache: IStationsCache,
        private val mapper: StationEntityMapper,
        private val detailsMapper: StationDetailsEntityMapper
) : IStationsDataStore {

    override suspend fun fetchStations(latLng: LatLng, radius: Int, minQuality: Int) =
            error("not supported")

    override fun flowStations(latLng: LatLng, radius: Int, minQuality: Int) =
            cache.flowStations(latLng, radius, minQuality).map { mapper.map(it) }

    override suspend fun saveStations(stations: List<StationOnMapDTO>) =
            cache.saveStations(mapper.reverse(stations))

    override suspend fun getStationDetails(id: Int) =
            cache.getStationDetails(id)?.let { detailsMapper.map(it) }

    override suspend fun saveStationsDetails(details: StationDetailsDTO) =
            cache.saveStationsDetails(detailsMapper.reverse(details))
}