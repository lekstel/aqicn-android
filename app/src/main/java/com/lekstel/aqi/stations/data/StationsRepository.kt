package com.lekstel.aqi.stations.data

import com.google.android.gms.maps.model.LatLng
import com.lekstel.aqi.base.di.qualifier.LocalDataStore
import com.lekstel.aqi.base.di.qualifier.RemoteDataStore
import com.lekstel.aqi.stations.data.mapper.StationOnMapDtoMapper
import com.lekstel.aqi.stations.data.source.IStationsDataStore
import com.lekstel.aqi.stations.domain.model.StationOnMap
import com.lekstel.aqi.stations.domain.repository.IStationsRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StationsRepository @Inject constructor(
    @LocalDataStore
    private val localDataStore: IStationsDataStore,
    @RemoteDataStore
    private val remoteDataStore: IStationsDataStore,
    private val stationOnMapMapper: StationOnMapDtoMapper
) : IStationsRepository {

    override suspend fun fetchStations(latLng: LatLng, radius: Int, minQuality: Int) =
        remoteDataStore.fetchStations(latLng, radius, minQuality).map { stationOnMapMapper.map(it) }

    override fun flowStations(latLng: LatLng, radius: Int, minQuality: Int) =
        localDataStore.flowStations(latLng, radius, minQuality).map { stationOnMapMapper.map(it) }

    override suspend fun saveStations(stations: List<StationOnMap>) =
        localDataStore.saveStations(stationOnMapMapper.reverse(stations))
}