package com.lekstel.aqi.stations.data.cache

import com.google.android.gms.maps.model.LatLng
import com.lekstel.aqi.stations.data.cache.entity.StationOnMapEntity
import kotlinx.coroutines.flow.Flow

interface IStationsCache {

    fun flowStations(latLng: LatLng, radius: Int, minQuality: Int): Flow<List<StationOnMapEntity>>

    suspend fun saveStations(stations: List<StationOnMapEntity>)
}