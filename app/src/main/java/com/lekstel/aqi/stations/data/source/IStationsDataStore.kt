package com.lekstel.aqi.stations.data.source

import com.google.android.gms.maps.model.LatLng
import com.lekstel.aqi.stations.data.model.StationDetailsDTO
import com.lekstel.aqi.stations.data.model.StationOnMapDTO
import kotlinx.coroutines.flow.Flow

interface IStationsDataStore {

    suspend fun fetchStations(latLng: LatLng, radius: Int, minQuality: Int): List<StationOnMapDTO>

    fun flowStations(latLng: LatLng, radius: Int, minQuality: Int): Flow<List<StationOnMapDTO>>

    suspend fun saveStations(stations: List<StationOnMapDTO>)

    suspend fun getStationDetails(id: Int): StationDetailsDTO?

    suspend fun saveStationsDetails(details: StationDetailsDTO)
}