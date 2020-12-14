package com.lekstel.aqi.stations.domain.repository

import com.google.android.gms.maps.model.LatLng
import com.lekstel.aqi.stations.domain.model.StationDetails
import com.lekstel.aqi.stations.domain.model.StationOnMap
import kotlinx.coroutines.flow.Flow

interface IStationsRepository {

    suspend fun fetchStations(latLng: LatLng, radius: Int, minQuality: Int): List<StationOnMap>

    fun flowStations(latLng: LatLng, radius: Int, minQuality: Int): Flow<List<StationOnMap>>

    suspend fun saveStations(stations: List<StationOnMap>)

    suspend fun getStationDetails(id: Int): StationDetails?

    suspend fun saveStationsDetails(details: StationDetails)
}